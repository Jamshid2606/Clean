package com.jama.clean.domain.getObjectAll.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.net.IDN

data class ObjectTypeData(
    @SerializedName("id")
    val id:String,
    @SerializedName("name")
    val name: String
) : Serializable
