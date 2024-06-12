package br.com.fiap.locawebchallenge.View.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.shared.composables.DefaultBtn
import br.com.fiap.locawebchallenge.shared.composables.Header
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import br.com.fiap.locawebchallenge.ui.theme.Typography

@Composable
fun HomeView(navController: NavController) {
    Box {
        Column {
            Header(isLogged = false)
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                TitleBanner(
                    title = "Bem-vindo ao ZenMail",
                )
                Spacer(Modifier.height(24.dp))
                DefaultBtn(
                    title = "Entrar",
                    onClick = { navController.navigate("login") },

                    )
                TextButton(
                    onClick = { navController.navigate("register") },
                    Modifier.height(32.dp)
                ) {
                    Text(
                        text = "Criar conta",
                        style = Typography.labelSmall,
                        textAlign = TextAlign.Center,
                        color = colorResource(id = R.color.secondary)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeViewsPreview() {
    HomeView(rememberNavController())
}