package com.grasepta.storyapp.base

import com.grasepta.storyapp.base.helper.remote.RemoteHelper
import com.grasepta.storyapp.base.helper.remote.RemoteHelperImpl
import com.grasepta.storyapp.core.network.ApiInterface
import javax.inject.Inject

abstract class BaseRemote: RemoteHelper by RemoteHelperImpl() {

    @Inject
    lateinit var api: ApiInterface

    val errorAuth = "40100"

}