package com.jama.clean.domain.addObject.useCase

import com.jama.clean.data.addObject.remote.models.request.AddObjectRequest
import com.jama.clean.domain.addObject.AddObjectRepository
import javax.inject.Inject

class AddObjectUseCase @Inject constructor(
    private val addObjectRepository: AddObjectRepository
){
    suspend fun invoke(request: AddObjectRequest) = addObjectRepository.addObject(request)
}