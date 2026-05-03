package com.alaric.data.remote

import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("coverUrl") val coverUrl: String?,
    @SerializedName("prominentImageUrl")val prominentImageUrl : String? = null,
    @SerializedName("screenshots")val screenshots: List<String>? = null,
    @SerializedName("rating")val rating: Double? = null,
    @SerializedName("summary") val summary: String?,
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("platforms") val platforms: List<String>?,
    @SerializedName("releaseDate")val releaseDate: String? = null
)
