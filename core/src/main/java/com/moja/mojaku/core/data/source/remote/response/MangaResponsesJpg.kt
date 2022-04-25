package com.moja.mojaku.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class MangaResponsesJpg(
    @SerializedName("large_image_url")
    val largeImageUrl: String
)