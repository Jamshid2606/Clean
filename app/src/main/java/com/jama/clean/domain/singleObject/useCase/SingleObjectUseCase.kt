package com.jama.clean.domain.singleObject.useCase

import android.provider.ContactsContract.CommonDataKinds.Im
import com.jama.clean.domain.singleObject.SingleObjectRepository
import javax.inject.Inject

class SingleObjectUseCase @Inject constructor(
    private val getSingleObjectRepository: SingleObjectRepository
){
    suspend fun invoke(id :Int) = getSingleObjectRepository.getSingleObject(id = id)
}