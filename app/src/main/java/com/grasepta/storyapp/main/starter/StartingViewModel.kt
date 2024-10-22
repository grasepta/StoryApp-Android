package com.grasepta.storyapp.main.starter

import androidx.lifecycle.ViewModel
import com.grasepta.storyapp.useCase.auth.AuthUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartingViewModel @Inject constructor(private val useCase: AuthUseCase) : ViewModel() {

    fun isLogin(): Boolean = useCase.isLogin()

}