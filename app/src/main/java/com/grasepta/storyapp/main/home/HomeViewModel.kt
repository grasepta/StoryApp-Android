package com.grasepta.storyapp.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grasepta.storyapp.base.UIStateData
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.response.ListStory
import com.grasepta.storyapp.useCase.auth.AuthUseCase
import com.grasepta.storyapp.useCase.story.StoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val useCase : StoryUseCase,
    private val authUseCase: AuthUseCase
): ViewModel() {

    private var _stories = MutableLiveData<UIStateData<List<ListStory>>>()
    val stories : LiveData<UIStateData<List<ListStory>>> = _stories

    fun getAllStories(page: Int?, size: Int?, location: Int? ) = viewModelScope.launch {
        useCase.getAllStories(page, size, location)
            .onStart { _stories.value = UIStateData(loading = true) }
            .collect { data ->
                when(data) {
                    is ConsumeResultDomain.Success -> _stories.value = UIStateData(data = data.data, loading = false)
                    is ConsumeResultDomain.Error -> _stories.value = UIStateData(message = data.message, loading = false)
                    is ConsumeResultDomain.ErrorAuth -> _stories.value = UIStateData(specialMessage = data.message, loading = false)
                }
            }
    }

    fun doLogOut() = authUseCase.doLogOut()

}