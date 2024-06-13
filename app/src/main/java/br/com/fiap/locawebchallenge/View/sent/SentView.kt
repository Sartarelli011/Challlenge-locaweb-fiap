package br.com.fiap.locawebchallenge.View.sent

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.fiap.locawebchallenge.R
import br.com.fiap.locawebchallenge.shared.composables.Header
import br.com.fiap.locawebchallenge.shared.composables.MailCard
import br.com.fiap.locawebchallenge.shared.composables.TitleBanner
import br.com.fiap.locawebchallenge.shared.repository.MessageRepository
import br.com.fiap.locawebchallenge.shared.repository.UserRepository
import br.com.fiap.locawebchallenge.ui.theme.Typography

@Composable
fun SentView(navController: NavController, viewModel: SentViewModel, id: Int) {

    val context = LocalContext.current
    val userRepo = UserRepository(context)
    val user = userRepo.getUserById(id)
    val messages = viewModel.messages.observeAsState()

    val messagesRepo = MessageRepository(context)

    try {
        viewModel.setMessages(messagesRepo.getMessagesSent(user.email))
    } catch (e: Exception) {
        Log.i("Error", e.message!!)
    }
    Box {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Header(isLogged = true, screenIndex = 2, navController, id)
            Spacer(modifier = Modifier.height(16.dp))
            TitleBanner(title = "Emails enviados", horizontal = Alignment.CenterHorizontally)
            if (messages.value != null && messages.value!!.isNotEmpty()) {
                messages.value!!.forEach {
                    MailCard(
                        sender = it.sender,
                        message = it.message,
                        date = it.date,
                        wasRead = it.wasRead,
                        id = it.id,
                        navController = navController,
                        user = user,
                        recipient = it.recipient
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(100.dp))
                Text(
                    text = "Sem emails enviados",
                    style = Typography.bodyLarge,
                    textAlign = TextAlign.Center,
                    color = colorResource(R.color.secondary)
                )
            }
        }
        FloatingActionButton(
            onClick = {
                navController.navigate("creation?id=$id")
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp),

            ) {
            Icon(
                painter = painterResource(id = R.drawable.pencil_icon),
                contentDescription = "Pencil icon",
                tint = colorResource(id = R.color.secondary)
            )
        }

    }
}