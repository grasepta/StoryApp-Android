package com.grasepta.storyapp.core.data.repository.story

import android.content.Context
import com.grasepta.storyapp.base.BaseRepository
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.base.wrapper.ConsumeResultRemote
import com.grasepta.storyapp.core.data.datasource.story.StoryDataSource
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.core.data.response.ListStory
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

class StoryRepositoryImpl @Inject constructor(
    private val story: StoryDataSource,
    @ApplicationContext private val context: Context
) : StoryRepository, BaseRepository() {
    override fun getAllStories(
        page: Int?,
        size: Int?,
        location: Int?
    ): Flow<ConsumeResultDomain<List<ListStory>>> {
        return flow {
            when (val result =
                story.getAllStories(page = page, size = size,  location = location)) {
                is ConsumeResultRemote.Success -> {
                    val listStory = result.data.listStory ?: emptyList() // Handle nullability
                    emit(ConsumeResultDomain.Success(listStory))                }
                is ConsumeResultRemote.Error -> emit(ConsumeResultDomain.Error(message = result.message.orEmpty()))
                is ConsumeResultRemote.ErrorAuth -> emit(ConsumeResultDomain.ErrorAuth(result.message.orEmpty()))
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }

    override fun addNewStory(description: RequestBody, image: MultipartBody.Part): Flow<ConsumeResultDomain<AddNewStoryRes>> {
        return flow {
            when (val result =
                story.addNewStory(description = description, image = image)) {
                is ConsumeResultRemote.Success -> { emit(ConsumeResultDomain.Success(result.data))                }
                is ConsumeResultRemote.Error -> emit(ConsumeResultDomain.Error(message = result.message.orEmpty()))
                is ConsumeResultRemote.ErrorAuth -> emit(ConsumeResultDomain.ErrorAuth(result.message.orEmpty()))
            }
        }.catch {
            emit(ConsumeResultDomain.Error(message = it.localizedMessage.orEmpty()))
        }.flowOn(ioDispatcher)
    }
}