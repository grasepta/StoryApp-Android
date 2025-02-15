package com.grasepta.storyapp.core.data.repository.auth

import com.grasepta.storyapp.base.BaseRepository
import com.grasepta.storyapp.base.helper.local.PreferenceDataStoreHelper
import com.grasepta.storyapp.base.helper.local.PreferenceDataStoreHelperImpl.Companion.USER_SESSION
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.base.wrapper.ConsumeResultRemote
import com.grasepta.storyapp.core.data.datasource.auth.AuthDataSource
import com.grasepta.storyapp.core.data.response.LoginRes
import com.grasepta.storyapp.core.data.response.RegisterUserRes
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val remote: AuthDataSource,
    private val prefDataStore: PreferenceDataStoreHelper
) : AuthRepository, BaseRepository() {
    override fun registerUser(
        username: String,
        email: String,
        password: String
    ): Flow<ConsumeResultDomain<RegisterUserRes>> {
        return flow {
            when (val result =
                remote.registerUser(username = username, email = email, password = password)) {
                is ConsumeResultRemote.Success -> emit(ConsumeResultDomain.Success(result.data))
                is ConsumeResultRemote.Error -> emit(ConsumeResultDomain.Error(message = result.message.orEmpty()))
                is ConsumeResultRemote.ErrorAuth -> emit(ConsumeResultDomain.ErrorAuth(result.message.orEmpty()))
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }

    override fun doLogin(email: String, password: String): Flow<ConsumeResultDomain<LoginRes>> {
        return flow {
            when (val result = remote.doLogin(email = email, password = password)) {
                is ConsumeResultRemote.Success -> {
                    prefDataStore.savePreference(
                        USER_SESSION,
                        result.data.loginResult?.token.orEmpty()
                    )
                    emit(ConsumeResultDomain.Success(result.data))
                }

                is ConsumeResultRemote.Error -> emit(ConsumeResultDomain.Error(message = result.message.orEmpty()))
                is ConsumeResultRemote.ErrorAuth -> emit(ConsumeResultDomain.ErrorAuth(result.message.orEmpty()))
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }

    override fun isLogin(): Boolean {
        return prefDataStore.getPreference(USER_SESSION, "").isNotEmpty()
    }

    override fun doLogOut() {
        prefDataStore.clearAllData()
    }
}