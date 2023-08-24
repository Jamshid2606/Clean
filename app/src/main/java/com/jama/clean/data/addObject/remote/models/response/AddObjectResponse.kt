package com.jama.clean.data.addObject.remote.models.response

data class AddObjectResponse(
    val createdAt: String,
    val `data`: Data,
    val id: String,
    val name: String
)