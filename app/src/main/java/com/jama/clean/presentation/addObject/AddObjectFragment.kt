package com.jama.clean.presentation.addObject

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jama.clean.BaseFragment
import com.jama.clean.R
import com.jama.clean.databinding.FragmentAddObjectBinding
import com.jama.clean.domain.addObject.models.AddObjectData
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import com.jama.clean.presentation.single.GetSingleObjectState
import com.jama.clean.presentation.single.SingleViewModel
import com.jama.clean.presentation.utils.gone
import com.jama.clean.presentation.utils.visible
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class AddObjectFragment : BaseFragment<FragmentAddObjectBinding>(FragmentAddObjectBinding::inflate) {
    private val viewModel : AddObjectViewModel by viewModels()
    override fun onViewCreate() {
        observer()
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
//        binding.resultText.text = "ID:" + data.id + "\n" + "NAME:" + data.name
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