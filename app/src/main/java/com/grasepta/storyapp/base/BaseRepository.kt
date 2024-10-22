package com.grasepta.storyapp.base

import com.grasepta.storyapp.core.data.IOThread
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

abstract class BaseRepository {

//    @Inject
//    lateinit var cacheManager: CacheManager

    @Inject
    @IOThread
    lateinit var ioDispatcher: CoroutineDispatcher
}