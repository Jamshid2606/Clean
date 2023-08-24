package com.jama.clean.data.singleObject.repository

import com.jama.clean.data.singleObject.remote.api.SingleObjectService
import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.singleObject.SingleObjectRepository
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class SingleObjectRepositoryImpl @Inject constructor(private var getSingleObjectService: SingleObjectService) :
    SingleObjectRepository {
    override suspend fun getSingleObject(id: Int): Flow<BaseResult<SingleObjectTypeData>> {
        return flow {
            val response = getSingleObjectService.getObjectAll(objectId = id)
            if (response.isSuccessful){
                val data = response.body()
                if (data != null) {
                    emit(BaseResult.Success(SingleObjectTypeData(
                        name = data.name,
                        id = data.id
                    )))
                }else{
                    emit(BaseResult.Error("Data is Null"))
                }
            }else{
                val error = response.code()
                emit(BaseResult.Error(error.toString()))
            }
        }
    }
}