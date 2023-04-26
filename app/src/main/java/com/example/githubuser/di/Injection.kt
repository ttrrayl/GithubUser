package com.example.githubuser.di

import android.content.Context
import com.example.githubuser.data.GithubUserRepository
import com.example.githubuser.retrofit.ApiConfig
import com.example.githubuser.room.GithubUserRoomDatabase
import com.example.githubuser.utils.AppExecutors

object Injection {
    fun provideRepository(context: Context): GithubUserRepository {
        val apiService = ApiConfig.getApiService()
        val database = GithubUserRoomDatabase.getInstance(context)
        val dao = database.githubUserDao()
        val appExecutors = AppExecutors()
        return GithubUserRepository.getInstance(apiService, dao, appExecutors)
    }
}