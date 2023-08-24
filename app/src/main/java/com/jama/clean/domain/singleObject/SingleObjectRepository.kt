package com.jama.clean.domain.singleObject

import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import kotlinx.coroutines.flow.Flow


interface SingleObjectRepository {
    suspend fun getSingleObject(id: Int):
            Flow<BaseResult<SingleObjectTypeData>>
}