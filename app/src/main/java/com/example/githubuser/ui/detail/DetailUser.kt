package com.example.githubuser.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.githubuser.R
import com.example.githubuser.adapter.SectionPageAdapter
import com.example.githubuser.databinding.ActivityDetailUserBinding
import com.example.githubuser.datamodel.DetailUserResponse
import com.example.githubuser.room.GithubUser
import com.example.githubuser.ui.ViewModelFactory
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class DetailUser : AppCompatActivity() {

    private lateinit var binding: ActivityDetailUserBinding
    private lateinit var viewModel: DetailUserViewModel

    companion object{
        const val EXTRA_DETAIL = "extra_detail"
        const val AVATAR_DETAIL = "avatar_detail"
        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.tab_text1,
            R.string.tab_text2
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory: ViewModelFactory = ViewModelFactory.getInstance(this@DetailUser)
        viewModel = ViewModelProvider(this, factory)[DetailUserViewModel::class.java]

        viewModel.detailUser.observe(this) { detail_User ->
            setDetailUser(detail_User)
        }
        viewModel.isLoading.observe(this,{
            showLoading(it)
        })

        val username = intent.getStringExtra(EXTRA_DETAIL)
        val avatar = intent.getStringExtra(AVATAR_DETAIL)
        username?.let { viewModel.getDetail(it) }


        val sectionsPagerAdapter = SectionPageAdapter(this)
        sectionsPagerAdapter.username = username.toString()
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tab_foll)
        TabLayoutMediator(tabs, viewPager){
            tab,position -> tab.text = resources.getString(TAB_TITLES[position])
        }.attach()


        username?.let{ viewModel.getUserFavorite(it) }?.observe(this){
            if (it != null){
                binding.butFab.setImageResource(R.drawable.ic_favorite)
                binding.butFab.setOnClickListener{
                    val mUser = GithubUser(username.toString(),avatar.toString())
                    viewModel.delete(mUser)
                }
            } else{
                binding.butFab.setImageResource(R.drawable.ic_favorite_border)
                binding.butFab.setOnClickListener{
                    val mUser = GithubUser(username.toString(),avatar.toString())
                    viewModel.insert(mUser)
                }
            }
        }

        supportActionBar?.elevation = 0f
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarProfil.visibility =
            if (isLoading) View.VISIBLE else View.GONE
    }

    private fun setDetailUser(detail_User: DetailUserResponse) {
        binding.tvNama.text = detail_User.name
        binding.tvUsername.text = detail_User.login
        binding.tvFollowers.text = "Followers "+ detail_User.followers.toString()
        binding.tvFollowing.text = "Following " + detail_User.following.toString()
        Glide.with(this@DetailUser)
            .load(detail_User.avatarUrl)
            .into(binding.imageProfil)

    }


}