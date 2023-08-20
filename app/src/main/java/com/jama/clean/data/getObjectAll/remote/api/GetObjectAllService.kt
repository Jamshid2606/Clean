package com.jama.clean.data.getObjectAll.remote.api


import com.jama.clean.data.getObjectAll.remote.models.ObjectResponse
import retrofit2.Response
import retrofit2.http.GET

interface GetObjectAllService {
    @GET("objects" +
            "")
    suspend fun getObjectAll():Response<List<ObjectResponse>>
}
