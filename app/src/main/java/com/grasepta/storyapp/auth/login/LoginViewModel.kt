package com.grasepta.storyapp.auth.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grasepta.storyapp.base.UIConstant
import com.grasepta.storyapp.base.UIStateData
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.response.LoginRes
import com.grasepta.storyapp.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val useCase: AuthUseCase): ViewModel() {
    
    private var _login = MutableLiveData<UIStateData<LoginRes>>()
    val login: LiveData<UIStateData<LoginRes>> = _login
    
    fun doLogin(email: String, password: String) = viewModelScope.launch {
        if (email.isNotEmpty() && password.isNotEmpty()) {
            useCase.doLogin(email, password)
                .onStart { _login.value = UIStateData(loading = true) }
                .collect { data ->
                    when (data) {
                        is ConsumeResultDomain.Success -> _login.value =
                            UIStateData(data = data.data, loading = false)

                        is ConsumeResultDomain.Error -> _login.value =
                            UIStateData(message = data.message, loading = false)

                        else -> _login.value = UIStateData(message = UIConstant.GENERAL_ERROR, loading = false)
                    }
                }
        } else _login.value = UIStateData(message = UIConstant.EMPTY_ERROR, loading = false)
    }
    
}