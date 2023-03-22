package com.example.githubuser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubuser.databinding.FragmentFollowBinding
import com.example.githubuser.datamodel.ItemsItem

class FollowFragment : Fragment() {
    companion object{
        const val ARG_POSITION = "position"
        const val ARG_USERNAME = "username"
    }

    private lateinit var binding: FragmentFollowBinding
    private lateinit var viewModel: DetailUserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFollowBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this,ViewModelProvider.NewInstanceFactory()).get(DetailUserViewModel::class.java)


        viewModel.isLoading.observe(viewLifecycleOwner){
                showLoading(it)
        }

        var position = 0
        var username = arguments?.getString(ARG_USERNAME)
        arguments?.let {
            position = it.getInt(ARG_POSITION)
            username = it.getString(ARG_USERNAME)
        }
        if(position == 1){
            showLoading(true)
            username?.let { viewModel.getFollowers(it) }
            viewModel.followers.observe(viewLifecycleOwner, {
                    setFollowers(it)
                showLoading(false)
            })


        } else{
            showLoading(true)
            username?.let { viewModel.getFollowing(it) }
            viewModel.following.observe(viewLifecycleOwner,{
                setFollowing(it)
                showLoading(false)
            })

        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progressBarFoll.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

    private fun setFollowing(following: List<ItemsItem>) {
        binding.apply {
            binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
            val listFollowing = UserAdapter(following)
            binding.rvFollow.adapter = listFollowing
        }

    }

    private fun setFollowers(followers: List<ItemsItem>) {
        binding.apply {
            binding.rvFollow.layoutManager = LinearLayoutManager(requireActivity())
            val listFollowers = UserAdapter(followers)
            binding.rvFollow.adapter = listFollowers
        }

    }


}