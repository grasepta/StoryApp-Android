package com.grasepta.storyapp.core.data.datasource

import com.grasepta.storyapp.core.data.datasource.auth.AuthDataSource
import com.grasepta.storyapp.core.data.datasource.auth.AuthDataSourceImpl
import com.grasepta.storyapp.core.data.datasource.story.StoryDataSource
import com.grasepta.storyapp.core.data.datasource.story.StoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvideDataSource {

    @Binds
    fun provideRegisterDataSource(impl: AuthDataSourceImpl): AuthDataSource

    @Binds
    fun provideStoryDataSource(impl: StoryDataSourceImpl): StoryDataSource

}