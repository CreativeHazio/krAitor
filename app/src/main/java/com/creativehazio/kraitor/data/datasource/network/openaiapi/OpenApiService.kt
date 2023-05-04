package com.creativehazio.kraitor.data.datasource.network.openaiapi

import com.creativehazio.kraitor.data.model.Art
import com.creativehazio.kraitor.data.model.ArtResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface OpenApiService {

    @POST("images/generations")
    suspend fun generateArts(
        @Body art: Art
    ) : Response<ArtResponse>
}