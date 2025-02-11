package com.gm.data.auth

import kotlinx.serialization.Serializable

@Serializable
data class AuthRespond(
    val name: String,
    val token: String
)