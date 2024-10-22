package com.grasepta.storyapp.useCase

import com.grasepta.storyapp.useCase.auth.AuthUseCase
import com.grasepta.storyapp.useCase.auth.AuthUseCaseImpl
import com.grasepta.storyapp.useCase.story.StoryUseCase
import com.grasepta.storyapp.useCase.story.StoryUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideUseCase {

    @Binds
    fun provideRegisterUseCase(impl: AuthUseCaseImpl): AuthUseCase

    @Binds
    fun provideStoryUseCase(impl: StoryUseCaseImpl): StoryUseCase

}