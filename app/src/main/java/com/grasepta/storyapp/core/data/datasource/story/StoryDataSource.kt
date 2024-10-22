package com.grasepta.storyapp.core.data.datasource.story

import com.grasepta.storyapp.base.wrapper.ConsumeResultRemote
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.GetAllStoriesRes
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StoryDataSource {

    suspend fun getAllStories(
        page: Int?,
        size: Int?,
        location: Int?
    ): ConsumeResultRemote<GetAllStoriesRes>

    suspend fun addNewStory(
        description: RequestBody,
        image: MultipartBody.Part
    ): ConsumeResultRemote<AddNewStoryRes>

}