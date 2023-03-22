package com.example.githubuser.retrofit

import com.example.githubuser.datamodel.DetailUserResponse
import com.example.githubuser.datamodel.ItemsItem
import com.example.githubuser.datamodel.SearchResponse
import retrofit2.http.*
import retrofit2.Call

interface ApiService {

    @GET("search/users")
    fun getUsers(
        @Query("q") login: String): Call<SearchResponse>

    @GET("users/{username}")
    fun getDetailUser(
        @Path("username") username: String): Call<DetailUserResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}