package br.com.fiap.locawebchallenge.View.mail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.locawebchallenge.shared.models.Message

class MailViewModel {
    private val _message = MutableLiveData<Message>()
    val message: LiveData<Message> = _message

    fun setMessage(value: Message){
        _message.value = value
    }
}