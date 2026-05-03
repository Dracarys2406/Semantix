package com.alaric.data.remote

import com.google.gson.annotations.SerializedName

data class GameDto(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("summary") val summary: String?,
    @SerializedName("coverUrl") val coverUrl: String?,
    @SerializedName("platforms") val platforms: List<String>?,
    @SerializedName("genres") val genres: List<String>?
)
