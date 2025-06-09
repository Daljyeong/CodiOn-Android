package com.konkuk.codion.ui.onboarding.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.codion.data.dto.base.CodiOnApiFailureException
import com.konkuk.codion.data.repository.AuthRepository
import com.konkuk.codion.ui.onboarding.state.LoginState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()

    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Default)
    val loginState = _loginState.asStateFlow()

    private val _error: MutableStateFlow<String?> = MutableStateFlow(null)
    val error = _error.asStateFlow()

    fun updateEmail(email: String) {
        _email.value = email
    }

    fun updatePassword(password: String) {
        _password.value = password
    }

    fun signInWithEmail() {
        Log.d(
            "LoginViewModel",
            "signInWithEmail() called with email=${email.value}, pwd=${password.value}"
        )
        viewModelScope.launch {
            _loginState.value = LoginState.Loading

            authRepository.emailLogin(
                email = email.value,
                password = password.value
            ).fold(
                onSuccess = { loginResponse ->
                    Log.d("LoginViewModel", "로그인 성공: $loginResponse")

                    _loginState.value = LoginState.Success
                },
                onFailure = { error ->
                    Log.e("LoginViewModel", "로그인 실패: ${error.message}")
                    _loginState.value = LoginState.Error
                    if (error is CodiOnApiFailureException) {
                        _error.value = error.message
                    }
                    delay(1000)
                    _loginState.value = LoginState.Default
                }
            )
        }
    }

}