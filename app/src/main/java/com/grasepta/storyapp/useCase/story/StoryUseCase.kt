package com.grasepta.storyapp.useCase.story

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.request.AddNewStoryReq
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.ListStory
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface StoryUseCase {

    fun getAllStories(): LiveData<PagingData<ListStory>>

    fun getAllStoriesWithLocation(): LiveData<PagingData<ListStory>>

    fun addNewStory(description: RequestBody, image: MultipartBody.Part): Flow<ConsumeResultDomain<AddNewStoryRes>>
}
