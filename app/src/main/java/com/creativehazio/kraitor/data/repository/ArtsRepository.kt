package com.creativehazio.kraitor.data.repository

import com.creativehazio.kraitor.data.datasource.network.openaiapi.OpenApiService
import com.creativehazio.kraitor.data.model.Art
import com.creativehazio.kraitor.data.model.ArtResponse
import retrofit2.Response

class ArtsRepository (
    private val openApiService: OpenApiService
    ) {

    suspend fun getGeneratedArts(art: Art): Response<ArtResponse> {
        return openApiService.generateArts(art)
    }
}