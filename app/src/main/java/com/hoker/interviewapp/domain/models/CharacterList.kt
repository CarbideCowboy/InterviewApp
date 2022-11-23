package com.hoker.interviewapp.domain.models

import com.google.gson.annotations.SerializedName

data class CharacterList (
    @SerializedName("results")
    val characters: List<Character>
)