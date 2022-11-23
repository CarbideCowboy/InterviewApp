package com.hoker.interviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class Character(
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("species")
    val species: String,
    @SerializedName("image")
    val imageUrl: String,
    @SerializedName("location")
    val location: Location
)