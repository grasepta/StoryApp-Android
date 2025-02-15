package com.grasepta.storyapp.core.data.datasource.story

import com.grasepta.storyapp.base.BaseRemote
import com.grasepta.storyapp.base.wrapper.ConsumeResultRemote
import com.grasepta.storyapp.base.wrapper.RemoteResult
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.GetAllStoriesRes
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryDataSourceImpl @Inject constructor() : StoryDataSource, BaseRemote() {

    override suspend fun getAllStories(
        page: Int?,
        size: Int?,
        location: Int?
    ): ConsumeResultRemote<GetAllStoriesRes> {
        return when (val remoteResult = getRemoteResult(
            apiCall = api.getAllStories(
                page = page,
                size = size,
                location = location
            )
        )) {
            is RemoteResult.Success -> {
                ConsumeResultRemote.Success(data = remoteResult.data)
            }

            is RemoteResult.Error -> ConsumeResultRemote.Error(
                code = remoteResult.code,
                message = remoteResult.data.message
            )
        }
    }

    override suspend fun addNewStory(description: RequestBody, image: MultipartBody.Part): ConsumeResultRemote<AddNewStoryRes> {
        return when (val remoteResult = getRemoteResult(
            apiCall = api.addNewStory(
                description = description,
                photo = image
            ))) {
            is RemoteResult.Success -> {
                ConsumeResultRemote.Success(data = remoteResult.data)
            }

            is RemoteResult.Error -> {
                ConsumeResultRemote.Error(
                    code = remoteResult.code,
                    message = remoteResult.data.message
                )
            }
        }
    }
}