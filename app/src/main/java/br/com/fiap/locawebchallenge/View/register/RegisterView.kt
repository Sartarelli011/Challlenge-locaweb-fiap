package br.com.fiap.locawebchallenge.View.register

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavController
import br.com.fiap.locawebchallenge.shared.composables.BackBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultTxtField
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.View.login.LoginView
import br.com.fiap.locawebchallenge.View.login.LoginViewModel
import br.com.fiap.locawebchallenge.shared.composables.DefaultBtn
import br.com.fiap.locawebchallenge.shared.models.User
import br.com.fiap.locawebchallenge.shared.repository.UserRepository
import br.com.fiap.locawebchallenge.shared.utils.encrypter
import br.com.fiap.locawebchallenge.ui.theme.Typography

@Composable
fun RegisterView(navController: NavController, viewModel: RegisterViewModel) {

    val email by viewModel.email.observeAsState(initial = "")
    val emailValidation by viewModel.emailValidation.observeAsState(initial = "")
    val name by viewModel.name.observeAsState(initial = "")
    val password by viewModel.password.observeAsState(initial = "")
    val passwordValidation by viewModel.passwordValidation.observeAsState(initial = "")
    val formError by viewModel.formError.observeAsState(initial = "")

    val context = LocalContext.current
    val repo = UserRepository(context)

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BackBtn(navController)
            TitleBanner(title = "Registro de conta", horizontal = Alignment.CenterHorizontally)
            Spacer(modifier = Modifier.height(32.dp))
            DefaultTxtField(
                "Email",
                keyboardType = KeyboardType.Email,
                onValueChange = { viewModel.setEmail(it) },
                value = email,
                error = viewModel.emailValidator()
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtField(
                "Email confirmação",
                keyboardType = KeyboardType.Email,
                onValueChange = { viewModel.setEmailValidation(it) },
                value = emailValidation,
                error = viewModel.emailValidationValidator()
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtField(
                "Nome",
                keyboardType = KeyboardType.Text,
                onValueChange = { viewModel.setName(it) },
                value = name
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtField(
                "Senha",
                keyboardType = KeyboardType.Password,
                onValueChange = { viewModel.setPassword(it) },
                value = password
            )
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtField(
                "Senha confirmação",
                keyboardType = KeyboardType.Password,
                onValueChange = { viewModel.setPasswordValidation(it) },
                value = passwordValidation,
                error = viewModel.passwordValidationValidator()
            )
            Spacer(modifier = Modifier.height(32.dp))
            DefaultBtn(title = "Cadastrar", onClick = {
                if (email == "" || emailValidation == "" || name == "" || password == "" || passwordValidation == "") {
                    viewModel.setFormError("Os campos são obrigatórios")
                } else if (viewModel.emailValidator() != "" || viewModel.emailValidationValidator() != "" || viewModel.passwordValidationValidator() != "") {
                    viewModel.setFormError("")
                } else {
                    try {
                        repo.createUser(
                            User(
                                email = email,
                                name = name,
                                password = encrypter(password)
                            )
                        )
                        navController.navigate("home")
                        viewModel.setFormError("")
                    } catch (e: Exception) {
                        viewModel.setFormError("Erro ao criar usuário: ${e.message}")
                    }
                }
            })
            Text(
                text = formError, style = Typography.labelSmall,
                color = colorResource(id = R.color.secondary),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterViewPreview() {
    val navController = rememberNavController()
    val viewModel: RegisterViewModel = viewModel()
    RegisterView(navController = navController, viewModel = viewModel)
}