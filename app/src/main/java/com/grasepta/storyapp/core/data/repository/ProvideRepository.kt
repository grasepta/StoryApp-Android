package com.grasepta.storyapp.core.data.repository

import com.grasepta.storyapp.core.data.repository.auth.AuthRepository
import com.grasepta.storyapp.core.data.repository.auth.AuthRepositoryImpl
import com.grasepta.storyapp.core.data.repository.story.StoryRepository
import com.grasepta.storyapp.core.data.repository.story.StoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideRepository {

    @Binds
    fun provideRegisterRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    fun provideStoryRepository(impl: StoryRepositoryImpl): StoryRepository

}