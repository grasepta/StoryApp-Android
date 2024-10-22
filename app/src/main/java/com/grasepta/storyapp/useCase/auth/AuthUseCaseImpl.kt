package com.grasepta.storyapp.useCase.auth

import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.repository.auth.AuthRepository
import com.grasepta.storyapp.core.data.response.LoginRes
import com.grasepta.storyapp.core.data.response.RegisterUserRes
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthUseCaseImpl @Inject constructor(private val repo: AuthRepository) : AuthUseCase {
    override fun registerUser(
        username: String,
        email: String,
        password: String
    ): Flow<ConsumeResultDomain<RegisterUserRes>> {
        return repo.registerUser(username, email, password)
    }

    override fun doLogin(email: String, password: String): Flow<ConsumeResultDomain<LoginRes>> {
        return repo.doLogin(email, password)
    }

    override fun isLogin(): Boolean {
        return repo.isLogin()
    }

    override fun doLogOut() {
        repo.doLogOut()
    }
}