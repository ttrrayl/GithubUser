package com.example.githubuser

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.githubuser.datamodel.ItemsItem

class UserAdapter(private val listUser: List<ItemsItem>): RecyclerView.Adapter<UserAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = listUser[position]
        holder.tvUser.text = data.login
        Glide.with(holder.itemView)
            .load(data.avatarUrl)
            .into(holder.imageUser)
        holder.itemView.setOnClickListener{
            val intentDetail = Intent(holder.itemView.context, DetailUser::class.java)
            intentDetail.putExtra(DetailUser.EXTRA_DETAIL, data.login)
            holder.itemView.context.startActivity(intentDetail)
        }
    }

    override fun getItemCount() = listUser.size


    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val tvUser: TextView = view.findViewById(R.id.tv_username)
        val imageUser: ImageView = view.findViewById(R.id.imageUser)
    }

}