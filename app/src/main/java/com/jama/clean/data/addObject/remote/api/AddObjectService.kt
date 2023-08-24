package com.jama.clean.data.addObject.remote.api

import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.data.addObject.remote.models.response.AddObjectResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AddObjectService {
    @POST("objects")
    suspend fun addObject(@Body addObjectRequest: AddObjectRequest):Response<AddObjectResponse>
}