package br.com.fiap.locawebchallenge.shared.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.ui.theme.Typography

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InputSelec(value: String, setValue: (String) -> Unit, values: Array<String>){
    var isExpanded = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = {
            isExpanded.value = it
        }) {

        OutlinedTextField(
            value = value,
            onValueChange = {},
            readOnly = true,
            colors = TextFieldDefaults.textFieldColors(
                textColor = colorResource(R.color.primary),
                containerColor = colorResource(R.color.white),
                focusedIndicatorColor = colorResource(R.color.primary)
            ),
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            shape = RoundedCornerShape(4.dp),
            placeholder = {
                Text(text = "Email do destinatário", style = Typography.bodyLarge,
                    color = colorResource(R.color.primary)
                )
            },
            modifier = Modifier
                .width(340.dp)
                .menuAnchor(),
        )
        ExposedDropdownMenu(
            modifier = Modifier.background(color = colorResource(id = R.color.white)),
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false }) {
            values.forEach {
                DropdownMenuItem(
                    text = {
                        Text(text = it, style = Typography.bodyLarge,
                            textAlign = TextAlign.Center,
                            color = colorResource(id = R.color.primary)
                        )
                    }, onClick = {
                        setValue(it)
                        isExpanded.value = false
                    })
            }
        }
    }
}