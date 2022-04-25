package com.moja.mojaku.core.data.source.remote.response


import com.google.gson.annotations.SerializedName

data class MangaResponsesAuthor(
    @SerializedName("mal_id")
    val malId: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("url")
    val url: String
)