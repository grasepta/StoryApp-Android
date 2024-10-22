package com.grasepta.storyapp.core.data.repository.di

import com.grasepta.storyapp.core.data.mediator.StoryRemoteMediator
import com.grasepta.storyapp.core.network.ApiInterface
import com.grasepta.storyapp.core.room.StoryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StoryMediatorModule {

    @Singleton
    @Provides
    fun provideStoryRemoteMediator(
        storyDatabase: StoryDatabase,
        apiService: ApiInterface
    ): StoryRemoteMediator {
        return StoryRemoteMediator(storyDatabase, apiService)
    }
}
