package com.jama.clean.domain.getObjectAll


import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.getObjectAll.models.ObjectTypeData
import kotlinx.coroutines.flow.Flow


interface GetObjectAllRepository {
    suspend fun getObjectAll():
            Flow<BaseResult<List<ObjectTypeData>>>
}