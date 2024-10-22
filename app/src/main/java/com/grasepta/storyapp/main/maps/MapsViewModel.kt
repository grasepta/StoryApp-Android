package com.grasepta.storyapp.main.maps

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.grasepta.storyapp.useCase.story.StoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MapsViewModel @Inject constructor(
    private val useCase : StoryUseCase,
    ): ViewModel() {

    fun getAllStories() = useCase.getAllStoriesWithLocation().cachedIn(viewModelScope)
}