package com.jama.clean.domain.singleObject.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class SingleObjectTypeData(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String
) : Serializable
