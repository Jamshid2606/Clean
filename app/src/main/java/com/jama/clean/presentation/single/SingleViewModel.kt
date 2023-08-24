package com.jama.clean.presentation.single

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import com.jama.clean.domain.singleObject.useCase.SingleObjectUseCase
import com.jama.clean.presentation.getObjectAll.GetObejectAllState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SingleViewModel @Inject constructor(
    private val singleObjectUseCase: SingleObjectUseCase
) :ViewModel(){
    private val _state = MutableStateFlow<GetSingleObjectState>(GetSingleObjectState.Init)
    val state get() = _state.asStateFlow()
    fun getSingleObject(id:Int){
        viewModelScope.launch {
            singleObjectUseCase.invoke(id)
                .onStart {
                    _state.value = GetSingleObjectState.IsLoading(true)
                }
                .catch {
                    Log.d("VIEWMODEL", it.message.toString())
                    hideLoading()
                }
                .collect{result->
                    when(result){
                        is BaseResult.Error->{
                            Log.d("VIEWMODEL", result.error)
                            _state.value = GetSingleObjectState.ErrorGetObejectAll(result.error)
                        }
                        is BaseResult.Success->{
                            _state.value = GetSingleObjectState.SuccessGetObejectAll(result.data)
                            Log.d("VIEWMODEL","SUCCESS")
                        }
                    }
                }

        }
    }
    private fun hideLoading(){
        _state.value = GetSingleObjectState.IsLoading(false)
    }
}
sealed class GetSingleObjectState{
    object Init: GetSingleObjectState()
    data class IsLoading(val isLoading:Boolean) : GetSingleObjectState()
    data class ShowToast(val message:String) : GetSingleObjectState()
    data class SuccessGetObejectAll(val data:SingleObjectTypeData) : GetSingleObjectState()
    data class ErrorGetObejectAll(val error:String) : GetSingleObjectState()
}