package com.hoker.interviewapp.domain.use_case

import com.hoker.interviewapp.domain.Api
import javax.inject.Inject
import com.hoker.interviewapp.domain.models.Character
import retrofit2.Retrofit

class GetCharactersUseCase @Inject constructor(
    private val retrofit: Retrofit
) {
    suspend fun getCharacters(): List<Character>? {
        val api = retrofit.create(Api::class.java)
        val response = api.getAllCharacters()
        return when(response.isSuccessful) {
            true -> {
                response.body()?.characters
            }

            false -> {
                null
            }
        }
    }
}