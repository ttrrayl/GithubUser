package com.example.githubuser.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [GithubUser::class], version = 1)
abstract class GithubUserRoomDatabase: RoomDatabase() {
    abstract fun githubUserDao(): GithubUserDao
    
    companion object{
        @Volatile
        private var INSTANCE: GithubUserRoomDatabase? = null

        fun getInstance(context: Context): GithubUserRoomDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    GithubUserRoomDatabase::class.java, "User_database"
                ).build()
            }
    }
}