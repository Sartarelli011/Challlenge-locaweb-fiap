package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.locawebchallenge.ui.theme.Typography
import br.com.fiap.locawebchallenge.R

@Composable
fun DefaultBtn(title: String, onClick: () -> Unit) {
    Button(
        onClick = onClick, Modifier.width(200.dp),
        colors = ButtonDefaults.buttonColors
            (
            containerColor = colorResource(
                id = R.color.secondary
            )
        )
    ) {
        Text(
            text = title,
            style = Typography.bodyLarge,
            textAlign = TextAlign.Center,
            color = colorResource(id = R.color.white)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultBtnPreview() {
    DefaultBtn(title = "Preview Button", onClick = {})
}