package com.moja.mojaku.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MangaResponses(
    @SerializedName("data")
    val `data`: List<MangaResponsesData>
)