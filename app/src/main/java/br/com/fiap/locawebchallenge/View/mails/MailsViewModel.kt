package br.com.fiap.locawebchallenge.View.mails

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.fiap.locawebchallenge.shared.models.Message

class MailsViewModel : ViewModel() {
    private val _messages = MutableLiveData<Array<Message>>(emptyArray())
    val messages : LiveData<Array<Message>> = _messages

    fun setMessages(value: Array<Message>){
        _messages.value = value
    }

    private val _showUnreadOnly = MutableLiveData<Boolean>(false)
    val showUnreadOnly : LiveData<Boolean> = _showUnreadOnly

    fun setShowUnreadOnly(value: Boolean){
        _showUnreadOnly.value = value
    }
}