package com.jama.clean.domain.addObject

import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.domain.addObject.models.AddObjectData
import com.jama.clean.domain.common.BaseResult
import com.jama.clean.domain.singleObject.models.SingleObjectTypeData
import kotlinx.coroutines.flow.Flow

interface AddObjectRepository {
    suspend fun addObject(request: AddObjectRequest):
            Flow<BaseResult<AddObjectData>>
}