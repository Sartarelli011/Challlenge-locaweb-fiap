package br.com.fiap.locawebchallenge.View.register

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RegisterViewModel : ViewModel() {
    private val _email = MutableLiveData<String>("")
    val email : LiveData<String> = _email

    fun setEmail(value: String){
        _email.value = value
        emailValidator()
    }

    fun emailValidator() : String{
        if(_email.value != "" && !Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$").matches(_email.value!!)){
            return "Email inválido";
        }

        return "";
    }

    private val _emailValidation = MutableLiveData<String>("")
    val emailValidation : LiveData<String> = _emailValidation

    fun setEmailValidation(value: String){
        _emailValidation.value = value
        emailValidationValidator()
    }

    fun emailValidationValidator() : String{
        if( _emailValidation.value != "" && !Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\$").matches(_emailValidation.value!!)){
            return "Email inválido";
        }
        if((_emailValidation.value != _email.value)){
            return "Os campos de email devem ser iguais"
        }

        return "";
    }

    private val _name = MutableLiveData<String>("")
    val name : LiveData<String> = _name

    fun setName(value: String){
        _name.value = value
    }

    private val _password = MutableLiveData<String>("")
    val password : LiveData<String> = _password

    fun setPassword(value: String){
        _password.value = value
    }

    private val _passwordValidation = MutableLiveData<String>("")
    val passwordValidation : LiveData<String> = _passwordValidation

    fun setPasswordValidation(value: String){
        _passwordValidation.value = value
    }

    fun passwordValidationValidator() : String{
        if((_password.value != _passwordValidation.value)){
            return "Os campos de senha devem ser iguais"
        }

        return "";
    }

    private val _formError = MutableLiveData<String>("")
    val formError : LiveData<String> = _formError

    fun setFormError(value: String){
        _formError.value = value
    }
}