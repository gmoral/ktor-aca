package com.gm.data.auth.respond

import kotlinx.serialization.Serializable

@Serializable
data class AuthRespond(
    val name: String,
    val token: String
)