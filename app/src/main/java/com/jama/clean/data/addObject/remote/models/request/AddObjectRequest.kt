package com.jama.clean.data.addObject.remote.models.request

import com.jama.clean.domain.addObject.models.Data

data class AddObjectRequest(
    val data: Data,
    val name: String
)