package com.grasepta.storyapp.base.helper.local.di

import com.grasepta.storyapp.base.helper.local.PreferenceDataStoreHelper
import com.grasepta.storyapp.base.helper.local.PreferenceDataStoreHelperImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ProvidePreferenceDataStoreHelper {

    @Binds
    fun providePreferenceDataStoreHelper(impl: PreferenceDataStoreHelperImpl): PreferenceDataStoreHelper
}