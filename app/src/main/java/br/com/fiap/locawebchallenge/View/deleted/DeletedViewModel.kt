package br.com.fiap.locawebchallenge.View.deleted

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.fiap.locawebchallenge.shared.models.Message

class DeletedViewModel {
    private val _messages = MutableLiveData<Array<Message>>(emptyArray())
    val messages : LiveData<Array<Message>> = _messages

    fun setMessages(value: Array<Message>){
        _messages.value = value
    }
}