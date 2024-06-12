package br.com.fiap.locawebchallenge.View.calendar

import android.content.Context
import android.content.Intent
import android.util.Log
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
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.shared.composables.BackBtn
import br.com.fiap.locawebchallenge.shared.composables.DatePickerDefault
import br.com.fiap.locawebchallenge.shared.composables.DefaultBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultTxtField
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import br.com.fiap.locawebchallenge.shared.utils.stringToCalendar
import br.com.fiap.locawebchallenge.ui.theme.Typography

fun addCalendarEvent(context: Context, initialDate: String, finalDate: String, title: String) {
    val intent = Intent(Intent.ACTION_EDIT)
    val initialCalendar = stringToCalendar(initialDate)
    val finalCalendar = stringToCalendar(finalDate)
    initialCalendar?.let {
        intent.putExtra("beginTime", it.timeInMillis)
    }
    finalCalendar?.let {
        intent.putExtra("endTime", it.timeInMillis)
    }
    intent.type = "vnd.android.cursor.item/event"
    intent.putExtra("allDay", true)
    intent.putExtra("title", title)
    context.startActivity(intent)
}

@Composable
fun CalendarView(navController: NavController, viewModel: CalendarViewModel) {

    val title by viewModel.title.observeAsState(initial = "")
    val formError by viewModel.formError.observeAsState(initial = "")
    val initialDate by viewModel.initialDate.observeAsState(initial = "")
    val finalDate by viewModel.finalDate.observeAsState(initial = "")

    val context = LocalContext.current

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BackBtn(navController)
            TitleBanner(title = "Criação de evento")
            Spacer(modifier = Modifier.height(32.dp))
            DefaultTxtField(
                "Título do evento",
                keyboardType = KeyboardType.Email,
                onValueChange = { viewModel.setEvent(it) },
                value = title,
            )
            Spacer(modifier = Modifier.height(16.dp))
            DatePickerDefault( "Selecione a data inicial", setDate = {viewModel.setInitialDate(it)}, date = initialDate)
            Spacer(modifier = Modifier.height(16.dp))
            DatePickerDefault( "Selecione a data final", setDate = {viewModel.setFinalDate(it)}, date = finalDate)
            Spacer(modifier = Modifier.height(32.dp))
            DefaultBtn(title = "Criar evento") {
                try {
                    Log.i("TAVAZIO", title)
                    Log.i("TAVAZIO", initialDate)
                    Log.i("TAVAZIO", finalDate)
                    if(title != "" && initialDate != "" && finalDate != ""){
                        viewModel.setFormError("")
                        addCalendarEvent(context, initialDate, finalDate, title)
                    } else {
                        viewModel.setFormError("Campos obrigatórios")
                    }
                } catch (e: Exception) {
                    viewModel.setFormError(e.message!!)
                    Log.i("Error", e.message!!)
                }
            }
            Text(
                text = formError, style = Typography.labelSmall,
                color = colorResource(id = R.color.secondary),
                textAlign = TextAlign.Center
            )
        }
    }
}