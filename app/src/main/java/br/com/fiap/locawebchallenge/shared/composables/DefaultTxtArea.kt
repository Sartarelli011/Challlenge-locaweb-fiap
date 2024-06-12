package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultTxtArea(
    title: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit,
    value: String,
    error: String = "",
    maxLines: Int = 6
) {
    Column {
        OutlinedTextField(
            value = value,
            maxLines = maxLines,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(R.color.primary),
                containerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.primary)
            ),
            shape = RoundedCornerShape(4.dp),
            placeholder = {
                Text(
                    text = title,
                    style = Typography.bodyLarge,
                    color = colorResource(R.color.primary)
                )
            },
            isError = value.isNotEmpty() && error != "",
            modifier = Modifier.width(340.dp).fillMaxHeight(0.6F),
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None
        )
        if (error != "") {
            Text(
                text = error, style = Typography.labelSmall,
                color = colorResource(id = R.color.secondary),
            )
        }
    }
}