package com.grasepta.storyapp.story.addstory

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.grasepta.storyapp.base.UIStateData
import com.grasepta.storyapp.base.wrapper.ConsumeResultDomain
import com.grasepta.storyapp.core.data.response.AddNewStoryRes
import com.grasepta.storyapp.useCase.story.StoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody
import javax.inject.Inject

@HiltViewModel
class AddStoryViewModel @Inject constructor(private val useCase: StoryUseCase) : ViewModel() {

    private var _addStory = MutableLiveData<UIStateData<AddNewStoryRes>>()
    val addStotry : LiveData<UIStateData<AddNewStoryRes>> = _addStory

    fun addNewStory(image: MultipartBody.Part, description: RequestBody) = viewModelScope.launch {
        useCase.addNewStory(description = description, image = image)
            .onStart { _addStory.value = UIStateData(loading = true) }
            .collect { data ->
                when(data) {
                    is ConsumeResultDomain.Success -> _addStory.value = UIStateData(data = data.data, loading = false)
                    is ConsumeResultDomain.Error -> _addStory.value = UIStateData(message = data.message, loading = false)
                    is ConsumeResultDomain.ErrorAuth -> _addStory.value = UIStateData(specialMessage = data.message, loading = false)
                }
            }
    }

}