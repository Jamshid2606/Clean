package com.jama.clean.presentation.single

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jama.clean.BaseFragment
import com.jama.clean.databinding.FragmentSingleBinding
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import com.jama.clean.presentation.getObjectAll.GetObejectAllState
import com.jama.clean.presentation.utils.gone
import com.jama.clean.presentation.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SingleFragment : BaseFragment<FragmentSingleBinding>(FragmentSingleBinding::inflate) {
    private val viewModel : SingleViewModel by viewModels()
    override fun onViewCreate() {
        observer()
        binding.getUser.setOnClickListener {
            val id = binding.idEt.text.toString()
            viewModel.getSingleObject(id = id.toInt())
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
    private fun handleStateChange(state: GetSingleObjectState){
        when(state){
            is GetSingleObjectState.SuccessGetObejectAll->{handleSuccessLogin(state.data)}
            is GetSingleObjectState.ErrorGetObejectAll->{handleErrorLogin(state.error)}
            is GetSingleObjectState.IsLoading->{handleLoading(state.isLoading)}
            is GetSingleObjectState.ShowToast->{showToast(state.message)}
            is GetSingleObjectState.Init->Unit
        }
    }

    private fun handleErrorLogin(error:String){
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        handleLoading(false)
    }
    private fun handleSuccessLogin(data: SingleObjectTypeData){
        binding.resultText.text = "ID:" + data.id + "\n" + "NAME:" + data.name
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