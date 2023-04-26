package com.example.githubuser.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.room.GithubUser
import com.example.githubuser.ui.detail.DetailUser

class UserFavoriteAdapter(private val listUserFavorite: List<GithubUser>): RecyclerView.Adapter<UserFavoriteAdapter.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tv_username)
        val imageUser: ImageView = view.findViewById(R.id.imageUser)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
       return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val user = listUserFavorite[position]
        holder.tvUser.text = user.username
        Glide.with(holder.itemView)
            .load(user.avatarUrl)
            .into(holder.imageUser)
        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailUser::class.java)
            intentDetail.putExtra(DetailUser.EXTRA_DETAIL, user.username)
            intentDetail.putExtra(DetailUser.AVATAR_DETAIL, user.avatarUrl)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount() = listUserFavorite.size
}