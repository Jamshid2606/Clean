package com.jama.clean.data.addObject.repository

import com.jama.clean.data.addObject.remote.api.AddObjectService
import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.domain.addObject.AddObjectRepository
import com.jama.clean.domain.addObject.models.AddObjectData
import com.jama.clean.domain.addObject.models.Data
import com.jama.clean.domain.common.BaseResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class AddObjectRepositoryImpl @Inject constructor(
    private val addObjectService: AddObjectService
):AddObjectRepository{
    override suspend fun addObject(request: AddObjectRequest): Flow<BaseResult<AddObjectData>> {
        return flow {
            val response = addObjectService.addObject(request)
            if (response.isSuccessful){
                val data = response.body()
                if (data!=null){
                    emit(BaseResult.Success(AddObjectData(
                        id = data.id,
                        name = data.name,
                        createdAt = data.createdAt,
                    )))
                }
            }else{
                val code = response.code()
                emit(BaseResult.Error(code.toString()))
            }
        }
    }
}