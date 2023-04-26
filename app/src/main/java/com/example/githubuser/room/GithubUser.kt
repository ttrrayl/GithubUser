package com.example.githubuser.room

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import retrofit2.http.Field

@Entity(tableName = "favorite")
@Parcelize
class GithubUser (
    @field:PrimaryKey
    @field:ColumnInfo(name = "username")
    var username: String = "",

    @field:ColumnInfo (name = "avatarUrl")
    var avatarUrl: String? = null
): Parcelable