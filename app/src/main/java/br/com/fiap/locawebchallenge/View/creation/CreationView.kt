package br.com.fiap.locawebchallenge.View.creation

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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.shared.composables.BackBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultBtn
import br.com.fiap.locawebchallenge.shared.composables.DefaultTxtArea
import br.com.fiap.locawebchallenge.shared.composables.InputSelec
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import br.com.fiap.locawebchallenge.shared.models.Message
import br.com.fiap.locawebchallenge.shared.repository.MessageRepository
import br.com.fiap.locawebchallenge.shared.repository.UserRepository
import br.com.fiap.locawebchallenge.ui.theme.Typography
import java.util.Calendar

@Composable
fun CreationView(navController: NavController, viewModel: CreationViewModel, id: Int) {

    val recipients = viewModel.recipients.observeAsState(initial = emptyArray())
    val recipient = viewModel.recipient.observeAsState(initial = "")
    val message = viewModel.message.observeAsState(initial = "")
    val formError by viewModel.formError.observeAsState(initial = "")


    val context = LocalContext.current
    val userRepo = UserRepository(context)
    val messagesRepo = MessageRepository(context)
    val user = userRepo.getUserById(id)

    try {
        val users = userRepo.getAllUsers()
        var recipientsToFill = emptyArray<String>()
        users.forEach {
            recipientsToFill += it.email
        }
        viewModel.setRecipients(recipientsToFill)
    } catch (e: Exception) {
        Log.i("Error", e.message!!)
    }

    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            BackBtn(navController)
            TitleBanner(title = "Enviar email", horizontal = Alignment.CenterHorizontally)
            Spacer(modifier = Modifier.height(32.dp))
            InputSelec(recipient.value, { viewModel.setRecipient(it) }, recipients.value)
            Spacer(modifier = Modifier.height(16.dp))
            DefaultTxtArea(
                title = "Mensagem",
                keyboardType = KeyboardType.Text,
                onValueChange = { viewModel.setMessage(it) },
                value = message.value
            )
            Spacer(modifier = Modifier.height(32.dp))
            DefaultBtn(title = "Enviar") {
                if (recipient.value == "" || message.value == "") {
                    viewModel.setFormError("Campos obrigatórios")
                } else {
                    val spamRegex =
                        "\\b(offer|discount|buy now|limited time only|click here|prize|win|free|grátis|promoção|compre agora|ganhe|aproveite já)\\b".toRegex(
                            RegexOption.IGNORE_CASE
                        )
                    try {
                        var status = ""
                        if (spamRegex.containsMatchIn(message.value)) {
                            status = "SPAM"
                        } else {
                            status = "MAIL"
                        }
                        messagesRepo.sendMessage(
                            message = Message(
                                recipient = recipient.value,
                                sender = user.email,
                                date = Calendar.getInstance().timeInMillis,
                                message = message.value,
                                wasRead = false,
                                status = status
                            )
                        )
                        navController.navigate("mails?id=${user.id}")
                        viewModel.setFormError("")
                    } catch (e: Exception) {
                        viewModel.setFormError(e.message!!)
                    }
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

