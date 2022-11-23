package com.hoker.interviewapp.domain

import com.hoker.interviewapp.domain.models.CharacterList
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("character")
    suspend fun getAllCharacters(): Response<CharacterList>
}