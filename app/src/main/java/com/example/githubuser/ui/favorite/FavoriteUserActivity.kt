package com.example.githubuser.ui.favorite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.R
import com.example.githubuser.adapter.UserAdapter
import com.example.githubuser.adapter.UserFavoriteAdapter
import com.example.githubuser.databinding.ActivityFavoriteUserBinding
import com.example.githubuser.ui.ViewModelFactory
import com.example.githubuser.ui.detail.DetailUserViewModel

class FavoriteUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteUserBinding
    private lateinit var viewModel: FavoriteUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@FavoriteUserActivity)
        viewModel = ViewModelProvider(this, factory)[FavoriteUserViewModel::class.java]

        val layoutManager = LinearLayoutManager(this)
        binding.rvFavuser.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvFavuser.addItemDecoration(itemDecoration)

        viewModel.getAllFavorite().observe(this){ user ->
            val listUser = UserFavoriteAdapter(user)
            binding.rvFavuser.adapter = listUser
        }
    }
}