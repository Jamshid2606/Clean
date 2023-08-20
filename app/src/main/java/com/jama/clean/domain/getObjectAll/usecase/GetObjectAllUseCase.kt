package com.jama.clean.domain.getObjectAll.usecase

import com.jama.clean.domain.getObjectAll.GetObjectAllRepository
import javax.inject.Inject

class GetObjectAllUseCase @Inject constructor(
    private val getObjectAllRepository: GetObjectAllRepository
) {
    suspend fun invoke() =
        getObjectAllRepository.getObjectAll()
}