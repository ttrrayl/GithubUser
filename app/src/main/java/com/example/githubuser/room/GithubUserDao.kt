package com.example.githubuser.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface GithubUserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(favorited: GithubUser)

    @Delete
    fun delete(favorited: GithubUser)

    @Query("SELECT * from favorite ORDER BY username ASC")
    fun getAllFavorite(): LiveData<List<GithubUser>>

    @Query("SELECT * FROM favorite WHERE username = :username")
    fun getUserFavorite(username: String): LiveData<GithubUser>
}