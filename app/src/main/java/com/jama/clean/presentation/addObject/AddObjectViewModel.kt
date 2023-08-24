package com.jama.clean.presentation.addObject

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.domain.addObject.models.AddObjectData
import com.jama.clean.domain.addObject.useCase.AddObjectUseCase
import com.jama.clean.domain.common.BaseResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddObjectViewModel @Inject constructor(
    private val addObjectUseCase: AddObjectUseCase
): ViewModel() {
    private val _state = MutableStateFlow<AddObjectState>(AddObjectState.Init)
    val state get() = _state.asStateFlow()
    fun addObject(request: AddObjectRequest){
        viewModelScope.launch {
            addObjectUseCase.invoke(request)
                .onStart { _state.value = AddObjectState.IsLoading(true) }
                .catch { Log.d("VIEWMODEL", it.message.toString())
                hideLoading()}
                .collect{result->
                    when(result){
                        is BaseResult.Success->{
                            _state.value = AddObjectState.SuccessObjectAll(result.data)
                            Log.d("VIEWMODEL","SUCCESS")
                        }
                        is BaseResult.Error->{
                            Log.d("VIEWMODEL",result.error)
                            _state.value = AddObjectState.ErrorObjectAll(result.error)
                        }
                    }
                }
        }
    }
    private fun hideLoading(){
        _state.value = AddObjectState.IsLoading(false)
    }
}
sealed class AddObjectState{
    object Init: AddObjectState()
    data class IsLoading(val isLoading:Boolean) : AddObjectState()
    data class ShowToast(val message:String) : AddObjectState()
    data class SuccessObjectAll(val data: AddObjectData) : AddObjectState()
    data class ErrorObjectAll(val error:String) : AddObjectState()
}