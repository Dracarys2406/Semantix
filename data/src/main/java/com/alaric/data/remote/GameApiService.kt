package com.alaric.data.remote

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface GameApiService {
    // For your simple MVP fetch test
    @GET("/games")
    suspend fun getTestGames(): List<GameDto>

    // For your eventual Gemini integration
    data class RecommendationRequest(val prompt: String)

    @POST("/recommend")
    suspend fun getRecommendations(@Body request: RecommendationRequest): List<GameDto>
}