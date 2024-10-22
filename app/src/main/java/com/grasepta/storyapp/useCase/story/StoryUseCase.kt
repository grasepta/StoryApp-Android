package com.grasepta.storyapp.useCase.story

import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.ListStory
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StoryUseCase {

    fun getAllStories(
        page: Int?,
        size: Int?,
        location: Int?
    ): Flow<ConsumeResultDomain<List<ListStory>>>

    fun addNewStory(description: RequestBody, image: MultipartBody.Part): Flow<ConsumeResultDomain<AddNewStoryRes>>
}