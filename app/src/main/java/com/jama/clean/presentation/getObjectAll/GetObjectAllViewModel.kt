package com.jama.clean.presentation.getObjectAll

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import com.jama.clean.domain.getObjectAll.usecase.GetObjectAllUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val getObjectAllUseCase: GetObjectAllUseCase
) :ViewModel() {

    private val _state = MutableStateFlow<GetObejectAllState>(GetObejectAllState.Init)
    val state get() = _state.asStateFlow()
    fun getObjectAll(){
        viewModelScope.launch {
            getObjectAllUseCase.invoke()
                .onStart {
                    _state.value = GetObejectAllState.IsLoading(true)
                }
                .catch {
                    Log.d("VIEWMODEL", it.message.toString())
                    hideLoading()
                }
                .collect{result->
                    when(result){
                        is BaseResult.Error->{
                            _state.value = GetObejectAllState.ErrorGetObejectAll(result.error)
                        }
                        is BaseResult.Success->{
                            _state.value = GetObejectAllState.SuccessGetObejectAll(result.data)
                        }
                    }
                }
        }
    }
    private fun hideLoading(){
        _state.value = GetObejectAllState.IsLoading(false)
    }
}
sealed class GetObejectAllState{
    object Init: GetObejectAllState()
    data class IsLoading(val isLoading:Boolean) : GetObejectAllState()
    data class ShowToast(val message:String) : GetObejectAllState()
    data class SuccessGetObejectAll(val data:List<ObjectTypeData>) : GetObejectAllState()
    data class ErrorGetObejectAll(val error:String) : GetObejectAllState()
}