package com.example.githubuser.ui

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.githubuser.data.GithubUserRepository
import com.example.githubuser.di.Injection
import com.example.githubuser.ui.detail.DetailUserViewModel
import com.example.githubuser.ui.favorite.FavoriteUserViewModel

class ViewModelFactory private constructor (private val mGithubUserRepository: GithubUserRepository) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailUserViewModel::class.java)) {
            return DetailUserViewModel(mGithubUserRepository) as T
        } else if (modelClass.isAssignableFrom(FavoriteUserViewModel::class.java))  {
            return FavoriteUserViewModel(mGithubUserRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel Class: " + modelClass.name)
    }

    companion object{
        @Volatile
        private var instance: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }.also { instance = it }
        }
}