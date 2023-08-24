package com.jama.clean.presentation.addObject

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jama.clean.BaseFragment
import com.jama.clean.R
import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.databinding.FragmentAddObjectBinding
import com.jama.clean.domain.addObject.models.AddObjectData
import com.jama.clean.domain.addObject.models.Data
import com.jama.clean.presentation.utils.gone
import com.jama.clean.presentation.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class AddObjectFragment : BaseFragment<FragmentAddObjectBinding>(FragmentAddObjectBinding::inflate) {
    private val viewModel : AddObjectViewModel by viewModels()
    override fun onViewCreate() {
        observer()
        binding.add.setOnClickListener {
            val name = binding.etName.text.toString()
            val cpuModel = binding.cpuModel.text.toString()
            val cost = binding.etCost.text.toString()
            val year = binding.etYear.text.toString()
            val hard = binding.hard.text.toString()
            val data = Data(
                CPUModel = cpuModel,
                HardDiskSize = hard,
                price = cost.toDouble(),
                year = year
            )
            viewModel.addObject(AddObjectRequest(
                name = name,
                data = data
            ))
        }
    }
    private fun observer() {
        viewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                handleStateChange(it)
            }
            .launchIn(lifecycleScope)
    }
    private fun handleStateChange(state: AddObjectState){
        when(state){
            is AddObjectState.SuccessObjectAll->{handleSuccessLogin(state.data)}
            is AddObjectState.ErrorObjectAll->{handleErrorLogin(state.error)}
            is AddObjectState.IsLoading->{handleLoading(state.isLoading)}
            is AddObjectState.ShowToast->{showToast(state.message)}
            is AddObjectState.Init->Unit
        }
    }
    private fun handleErrorLogin(error:String){
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        handleLoading(false)
    }
    private fun handleSuccessLogin(data: AddObjectData){
        showToast("Success")
        navController.navigate(R.id.action_addObjectFragment_to_getobjectsFragment)
        handleLoading(false)
    }
    private fun showToast(message:String){
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
        handleLoading(false)
    }
    private fun handleLoading(loading:Boolean){
        if (loading){
            binding.progress.visible()
        }else{
            binding.progress.gone()
        }
    }
}