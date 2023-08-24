package com.jama.clean.data.singleObject.remote.api

import com.jama.clean.data.singleObject.remote.models.SingleObjectResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SingleObjectService {
    @GET("objects/{id}")
    suspend fun getObjectAll(@Path("id") objectId: Int): Response<SingleObjectResponse>
}