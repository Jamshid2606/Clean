package com.jama.clean.data.getObjectAll.repository

import android.util.Log
import com.jama.clean.data.getObjectAll.remote.api.GetObjectAllService
import com.jama.clean.data.getObjectAll.remote.models.ObjectResponse
import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.getObjectAll.GetObjectAllRepository
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetObjectAllRepositoryImpl @Inject constructor(
    private val getObjectAllService: GetObjectAllService
) : GetObjectAllRepository{
    override suspend fun getObjectAll(): Flow<BaseResult<List<ObjectTypeData>>> {
        return flow {
            val response = getObjectAllService.getObjectAll()
            if (response.isSuccessful){
                val data = response.body()

                val list: List<ObjectTypeData> = data?.map {
                    ObjectTypeData(
                        id = it.id,
                        name = it.name
                    )
                }!!
                emit(BaseResult.Success(list))
            }else{
                val error = response.code()
                emit(BaseResult.Error(error.toString()))
            }
        }
    }
}