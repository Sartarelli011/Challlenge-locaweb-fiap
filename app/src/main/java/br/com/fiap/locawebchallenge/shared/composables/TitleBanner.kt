package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.ui.theme.Typography

@Composable
fun TitleBanner(title: String, subTitle: String = "") {
    Box() {
        Column(
            modifier = Modifier
                .fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(Modifier.height(8.dp))
            if (subTitle != "") {
                Text(
                    text = subTitle,
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.white)
                )
            }
            Text(
                text = title,
                style = Typography.titleLarge,
                textAlign = TextAlign.Center,
                color = colorResource(R.color.secondary)
            )
            Spacer(Modifier.height(8.dp))
        }
    }
}