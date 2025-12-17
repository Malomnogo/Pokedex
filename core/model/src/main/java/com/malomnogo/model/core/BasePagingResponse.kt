package com.malomnogo.model.core

import kotlinx.serialization.SerialName

data class BasePagingResponse<T>(
    @SerialName("count")
    val count: Int,
    @SerialName("next")
    val next: String?,
    @SerialName("previous")
    val previous: String?,
    @SerialName("results")
    val results: List<T>
)
