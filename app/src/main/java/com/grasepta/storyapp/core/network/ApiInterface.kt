package com.grasepta.storyapp.core.network

import com.grasepta.storyapp.core.EndPoint
import com.grasepta.storyapp.core.data.request.LoginReq
import com.grasepta.storyapp.core.data.request.RegisterUserReq
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.GetAllStoriesRes
import com.grasepta.storyapp.core.data.response.LoginRes
import com.grasepta.storyapp.core.data.response.RegisterUserRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Query

interface ApiInterface {

    @POST(EndPoint.REGISTER)
    suspend fun registerUser(@Body body: RegisterUserReq): Response<RegisterUserRes>

    @POST(EndPoint.LOGIN)
    suspend fun doLogin(@Body body: LoginReq): Response<LoginRes>

    @GET(EndPoint.STORIES)
    suspend fun getAllStories(
        @Query("page") page: Int? = null,
        @Query("size") size: Int? = null,
        @Query("location") location: Int? = null,
    ): Response<GetAllStoriesRes>

    @Multipart
    @POST(EndPoint.STORIES)
    suspend fun addNewStory(
        @Part("description") description: RequestBody,
        @Part photo: MultipartBody.Part
    ): Response<AddNewStoryRes>
}