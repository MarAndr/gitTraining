package com.example.traincompose

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


class KindsViewModel @Inject constructor(val repo: Repo) : ViewModel() {

    private val _loginState = MutableStateFlow<LoginEvents>(LoginEvents.EMPTY)
    val loginState: StateFlow<LoginEvents> = _loginState

    private val _data = MutableStateFlow("default data")
    val data: StateFlow<String> = _data

    fun getData(){
        viewModelScope.launch {
            _data.value = repo.fakeRequest()
        }
    }


    fun login(login: String){
        _loginState.value = LoginEvents.LOADER
        when {
            login.isBlank() -> {
                _loginState.value = LoginEvents.EMPTY_FIELD
            }
            login == "marin" -> {
                _loginState.value = LoginEvents.SUCCESS
            }
            else -> {
                _loginState.value = LoginEvents.WRONG_LOGIN
            }
        }
    }

    fun pass(pass: String){
        _loginState.value = LoginEvents.LOADER
        when {
            pass.isBlank() -> {
                _loginState.value = LoginEvents.EMPTY_FIELD
            }
            pass == "123" -> {
                _loginState.value = LoginEvents.SUCCESS
            }
            else -> {
                _loginState.value = LoginEvents.WRONG_PASS
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
    }
}

sealed class LoginEvents{
    object EMPTY: LoginEvents()
    object SUCCESS: LoginEvents()
    object EMPTY_FIELD: LoginEvents()
    object WRONG_PASS: LoginEvents()
    object WRONG_LOGIN: LoginEvents()
    object LOADER: LoginEvents()
}