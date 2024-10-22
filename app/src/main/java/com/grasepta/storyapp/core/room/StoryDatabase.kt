package com.grasepta.storyapp.core.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.grasepta.storyapp.core.data.response.ListStory
import com.grasepta.storyapp.core.data.response.RemoteKeys

@Database(
    entities = [ListStory::class, RemoteKeys::class],
    version = 2,
    exportSchema = false
)
abstract class StoryDatabase : RoomDatabase() {

    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao

}
