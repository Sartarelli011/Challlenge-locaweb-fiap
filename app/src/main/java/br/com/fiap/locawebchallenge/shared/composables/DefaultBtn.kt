package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
    Box(
        modifier = Modifier
            .width(200.dp)
            .border(
                width = 2.dp,
                color = colorResource(id = R.color.primary),
                shape = RoundedCornerShape(8.dp)
            )
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.secondary)
            ),
            shape = RoundedCornerShape(8.dp) // Match the border shape
        ) {
            Text(
                text = title,
                style = Typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultBtnPreview() {
    DefaultBtn(title = "Preview Button", onClick = {})
}