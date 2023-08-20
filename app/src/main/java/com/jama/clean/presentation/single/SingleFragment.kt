package com.jama.clean.presentation.single

import androidx.fragment.app.viewModels
import com.jama.clean.BaseFragment
import com.jama.clean.databinding.FragmentSingleBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SingleFragment : BaseFragment<FragmentSingleBinding>(FragmentSingleBinding::inflate) {
    val viewModel : LoginViewModel by viewModels()
    override fun onViewCreate() {

    }
}