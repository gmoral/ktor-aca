package com.gm.data.media.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class MediaByIdRequest(
    val mediaId: Int,
    val email: String
)