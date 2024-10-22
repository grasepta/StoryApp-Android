package com.grasepta.storyapp.useCase.story

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.repository.story.StoryRepository
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.ListStory
import kotlinx.coroutines.flow.Flow
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryUseCaseImpl @Inject constructor(private val repo: StoryRepository) : StoryUseCase {
    override fun getAllStories(): LiveData<PagingData<ListStory>> {
        return repo.getAllStories()
    }

    override fun getAllStoriesWithLocation(): LiveData<PagingData<ListStory>> {
        return repo.getAllStoriesWithLocation()
    }

    override fun addNewStory(
        description: RequestBody,
        image: MultipartBody.Part
    ): Flow<ConsumeResultDomain<AddNewStoryRes>> {
        return repo.addNewStory(description = description, image = image)
    }
}
