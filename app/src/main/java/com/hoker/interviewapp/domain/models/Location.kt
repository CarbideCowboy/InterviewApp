package com.hoker.interviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("name")
    val name: String
)
