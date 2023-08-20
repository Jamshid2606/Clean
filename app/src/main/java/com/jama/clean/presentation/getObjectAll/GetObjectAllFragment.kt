package com.jama.clean.presentation.getObjectAll

import android.util.Log
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.jama.clean.BaseFragment
import com.jama.clean.databinding.FragmentGetobjectallBinding
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import com.jama.clean.presentation.utils.gone
import com.jama.clean.presentation.utils.visible
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class GetObjectAllFragment : BaseFragment<FragmentGetobjectallBinding>(FragmentGetobjectallBinding::inflate) {
    private val viewModel : RegisterViewModel by viewModels()
    private lateinit var adapter: GetObjectAllAdapter
    override fun onViewCreate() {
        adapter = GetObjectAllAdapter()
        binding.list.adapter = adapter
        observer()
        viewModel.getObjectAll()

    }

    private fun observer() {
        viewModel.state
            .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
            .onEach {
                handleStateChange(it)
            }
            .launchIn(lifecycleScope)
    }
    private fun handleStateChange(state: GetObejectAllState){
        when(state){
            is GetObejectAllState.SuccessGetObejectAll->{handleSuccessLogin(state.data)}
            is GetObejectAllState.ErrorGetObejectAll->{handleErrorLogin(state.error)}
            is GetObejectAllState.IsLoading->{handleLoading(state.isLoading)}
            is GetObejectAllState.ShowToast->{showToast(state.message)}
            is GetObejectAllState.Init->Unit
        }
    }
    private fun handleErrorLogin(error:String){
        Toast.makeText(requireContext(), error, Toast.LENGTH_SHORT).show()
        handleLoading(false)
    }
    private fun handleSuccessLogin(list: List<ObjectTypeData>){
        Log.d("GETOBJECTALLFRAGMENT", list.toString())
        adapter.setData(list)
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