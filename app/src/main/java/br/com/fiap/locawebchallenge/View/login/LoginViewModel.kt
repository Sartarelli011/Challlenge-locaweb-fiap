package br.com.fiap.locawebchallenge.View.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>("")
    val email : LiveData<String> = _email

    fun setEmail(value: String){
        _email.value = value
    }

    private val _password = MutableLiveData<String>("")
    val password : LiveData<String> = _password

    fun setPassword(value: String){
        _password.value = value
    }

    private val _error = MutableLiveData<String>("")
    val error : LiveData<String> = _error

    fun setError(value: String){
        _error.value = value
    }
}