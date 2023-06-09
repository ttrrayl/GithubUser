package com.example.githubuser.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.githubuser.datamodel.DetailUserResponse
import com.example.githubuser.datamodel.ItemsItem
import com.example.githubuser.data.GithubUserRepository
import com.example.githubuser.retrofit.ApiConfig
import com.example.githubuser.room.GithubUser
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailUserViewModel(private val mGithubUserRepository: GithubUserRepository) : ViewModel() {

    private val _detailUser= MutableLiveData<DetailUserResponse>()
    val detailUser: LiveData<DetailUserResponse> = _detailUser

    private val _followers = MutableLiveData<List<ItemsItem>>()
    val followers: LiveData<List<ItemsItem>> = _followers

    private val _following = MutableLiveData<List<ItemsItem>>()
    val following: LiveData<List<ItemsItem>> = _following

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _isFavorite = MutableLiveData<Boolean>()
    val isFavorite: LiveData<Boolean> = _isFavorite

    companion object{
        private const val TAG = "DetailUserViewModel"
    }

    fun getDetail(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object : Callback<DetailUserResponse> {
            override fun onResponse(
                call: Call<DetailUserResponse>,
                response: Response<DetailUserResponse>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responBody = response.body()
                    if(responBody != null) {
                        _detailUser.value = response.body()
                    }
                } else{
                    Log.e(TAG,"onFAILURE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowers(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowers(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responBody = response.body()
                    if(responBody != null) {
                        _followers.value = response.body()
                    }
                } else{
                    Log.e(TAG,"onFAILURE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getFollowing(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getFollowing(username)
        client.enqueue(object : Callback<List<ItemsItem>> {
            override fun onResponse(
                call: Call<List<ItemsItem>>,
                response: Response<List<ItemsItem>>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val responBody = response.body()
                    if(responBody != null) {
                        _following.value = response.body()
                    }
                } else{
                    Log.e(TAG,"onFAILURE: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<List<ItemsItem>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


    fun insert(favorite: GithubUser){
        mGithubUserRepository.insert(favorite)
    }

    fun delete(favorite: GithubUser){
        mGithubUserRepository.delete(favorite)

    }



    fun getUserFavorite(username: String) = mGithubUserRepository.getUserFavorite(username)






}

