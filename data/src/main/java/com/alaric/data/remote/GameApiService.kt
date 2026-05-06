package com.alaric.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApiService {
    @POST("recommendations")
    suspend fun getRecommendations(@Body request: RecommendationRequest): List<GameDto>
}