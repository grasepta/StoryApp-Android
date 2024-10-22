package com.grasepta.storyapp.base

data class UIStateData<out T>(
        val data: T? = null,
        val loading: Boolean? = null,
        val message: String? = null,
        val specialMessage: String? = null,
        val forceLogin: String? = null,
        val status: String? = null
)