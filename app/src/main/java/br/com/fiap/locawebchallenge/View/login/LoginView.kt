package br.com.fiap.locawebchallenge.View.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.shared.composables.BackBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultTxtField
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import br.com.fiap.locawebchallenge.shared.repository.UserRepository
import br.com.fiap.locawebchallenge.shared.utils.encrypter
import br.com.fiap.locawebchallenge.ui.theme.Typography

@Composable
fun LoginView(navController: NavController, viewModel: LoginViewModel) {

    val email by viewModel.email.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val error by viewModel.error.observeAsState(initial = "")

    val context = LocalContext.current
    val repo = UserRepository(context)

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BackBtn(navController)
            TitleBanner(title = "Entrar com conta", horizontal = Alignment.CenterHorizontally)
            Spacer(modifier = Modifier.height(32.dp))
            DefaultTxtField(
                "Email",
                keyboardType = KeyboardType.Email,
                onValueChange = { viewModel.setEmail(it) },
                value = email,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtField(
                "Senha",
                keyboardType = KeyboardType.Password,
                onValueChange = { viewModel.setPassword(it) },
                value = password,
            )
            Spacer(modifier = Modifier.height(32.dp))
            DefaultBtn(title = "Entrar", onClick = {
                if(email != "" && password != ""){
                    try {
                        val passwordEncrypted = encrypter(password)
                        val user = repo.getUser(email, passwordEncrypted);
                        if(user == null){
                            viewModel.setError("Usuário não encontrado")
                        } else {
                            navController.navigate("mails?id=${user.id}")
                        }
                    } catch (e: Exception) {
                        if(e.message != null){
                            viewModel.setError(e.message!!)
                        }
                    }
                }
            })
            Text(
                text = error, style = Typography.labelSmall,
                color = colorResource(id = R.color.secondary),
                textAlign = TextAlign.Center
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun LoginViewPreview() {
    val navController = rememberNavController()
    val viewModel: LoginViewModel = viewModel()
    LoginView(navController = navController, viewModel = viewModel)
}