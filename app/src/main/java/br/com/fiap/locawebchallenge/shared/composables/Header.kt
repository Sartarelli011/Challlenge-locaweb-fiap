package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
    isLogged: Boolean,
    screenIndex: Int = 0,
    navController: NavController? = null,
    id: Int = 0
) {
    Box {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                if (isLogged) {
                    Surface(
                        onClick = {
                            navController!!.navigate("home")
                        }
                    ) {
                        Image(
                            painter = painterResource(id = R.drawable.logo),
                            contentDescription = "Locamail Logo",
                            modifier = Modifier.size(48.dp)
                        )
                    }
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Locamail Logo",
                        modifier = Modifier.size(48.dp)
                    )
                    Text(text = "ZenMail",
                        style = Typography.titleLarge,
                        textAlign = TextAlign.Center,
                        color = colorResource(R.color.secondary)
                    )
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    if (isLogged) {
                        TextButton(
                            onClick = { navController!!.navigate("mails?id=$id") },
                            Modifier.height(32.dp)
                        ) {
                            Text(
                                text = "ENTRADA",
                                style = Typography.bodyLarge,
                                color = colorResource(id = if (screenIndex == 1) R.color.secondary else R.color.primary)
                            )
                        }
                        TextButton(
                            onClick = { navController!!.navigate("sent?id=$id") },
                            Modifier.height(32.dp)
                        ) {
                            Text(
                                text = "ENVIADOS",
                                style = Typography.bodyLarge,
                                color = colorResource(id = if (screenIndex == 2) R.color.secondary else R.color.primary)
                            )
                        }
                        TextButton(
                            onClick = { navController!!.navigate("deleted?id=$id") },
                            Modifier.height(32.dp)
                        ) {
                            Text(
                                text = "EXCLUÍDOS",
                                style = Typography.bodyLarge,
                                color = colorResource(id = if (screenIndex == 4) R.color.secondary else R.color.primary)
                            )
                        }
                    }
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(color = colorResource(R.color.gray))
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun HeaderPreview() {
    Header(isLogged = true, navController = rememberNavController())
}