package com.jama.clean.domain.common

sealed class BaseResult<out T:Any> {
    data class Success<T:Any>(val data:T) : BaseResult<T>()
    data class Error<U:Any>(val error:String) : BaseResult<U>()
}