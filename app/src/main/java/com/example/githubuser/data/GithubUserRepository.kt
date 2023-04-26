package com.example.githubuser.data

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.example.githubuser.datamodel.DetailUserResponse
import com.example.githubuser.datamodel.ItemsItem
import com.example.githubuser.retrofit.ApiConfig
import com.example.githubuser.retrofit.ApiService
import com.example.githubuser.room.GithubUser
import com.example.githubuser.room.GithubUserDao
import com.example.githubuser.room.GithubUserRoomDatabase
import com.example.githubuser.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class GithubUserRepository private constructor(
    private val apiService: ApiService,
    private val mGithubUserDao: GithubUserDao,
    private val appExecutors: AppExecutors
) {
    private val result = MediatorLiveData<Result<List<GithubUser>>>()



//    private val _isFavorite = MutableLiveData<Boolean>()
//    val isFavorite: LiveData<Boolean> = _isFavorite



//    init {
//        val db = GithubUserRoomDatabase.getDatabase(application)
//        mGithubUserDao = db.githubUserDao()
//    }
//
    fun getAllFavorite(): LiveData<List<GithubUser>> = mGithubUserDao.getAllFavorite()

    fun insert(favorited: GithubUser){
        appExecutors.diskIO.execute{ mGithubUserDao.insert(favorited)}
//       mGithubUserDao.insert(favorite)
    }

    fun delete(favorited: GithubUser){
        appExecutors.diskIO.execute{  mGithubUserDao.delete(favorited)}

    }

    fun getUserFavorite(username: String) = mGithubUserDao.getUserFavorite(username)

//    fun isUserFavorite(username: String): Boolean{
//        return mGithubUserDao.isUserFavorite(username)
//
//    }

    companion object {
        private const val TAG = "GithubUserRepository"

        @Volatile
        private var instance: GithubUserRepository? = null
        fun getInstance(
            apiService: ApiService,
            GithubUserDao: GithubUserDao,
            appExecutors: AppExecutors
        ): GithubUserRepository =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepository(apiService, GithubUserDao, appExecutors)
            }.also { instance = it }
    }

}