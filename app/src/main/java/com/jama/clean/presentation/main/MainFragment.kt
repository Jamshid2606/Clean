package com.jama.clean.presentation.main

import com.jama.clean.BaseFragment
import com.jama.clean.R
import com.jama.clean.databinding.FragmentMainBinding

class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate){
    override fun onViewCreate() {
        binding.getobjects.setOnClickListener {
            navController.navigate(R.id.action_mainFragment_to_getobjectsFragment)
        }
    }
}