package com.gm.data.media.model.requests

import kotlinx.serialization.Serializable

@Serializable
data class UpsertMediaRequest(
    val mediaRequest: MediaRequest,
    val email: String,

)