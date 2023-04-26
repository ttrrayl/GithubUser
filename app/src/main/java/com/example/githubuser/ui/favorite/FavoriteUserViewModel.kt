package com.example.githubuser.ui.favorite

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.githubuser.data.GithubUserRepository
import com.example.githubuser.room.GithubUser

class FavoriteUserViewModel(private val mGithubUserRepository: GithubUserRepository) : ViewModel() {

    fun getAllFavorite(): LiveData<List<GithubUser>> = mGithubUserRepository.getAllFavorite()

}