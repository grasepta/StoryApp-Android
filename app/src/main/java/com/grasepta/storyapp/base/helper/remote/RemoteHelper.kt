package com.grasepta.storyapp.base.helper.remote

import com.grasepta.storyapp.base.wrapper.RemoteResult
import retrofit2.Response

interface RemoteHelper {
    suspend fun <T> getRemoteResult(apiCall: Response<T>): RemoteResult<T>
}