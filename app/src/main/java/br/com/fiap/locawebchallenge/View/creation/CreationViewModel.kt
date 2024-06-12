package br.com.fiap.locawebchallenge.View.creation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

class CreationViewModel {
    private val _recipients = MutableLiveData<Array<String>>(emptyArray<String>())
    val recipients : LiveData<Array<String>> = _recipients

    fun setRecipients(value: Array<String>){
        _recipients.value = value
    }

    private val _recipient = MutableLiveData<String>("")
    val recipient : LiveData<String> = _recipient

    fun setRecipient(value: String){
        _recipient.value = value
    }

    private val _message = MutableLiveData<String>("")
    val message : LiveData<String> = _message

    fun setMessage(value: String){
        _message.value = value
    }

    private val _formError = MutableLiveData<String>("")
    val formError : LiveData<String> = _formError

    fun setFormError(value: String){
        _formError.value = value
    }
}