package com.aca.security.jwt.service

import com.aca.security.jwt.model.TokenClaim
import com.aca.security.jwt.model.TokenConfig

interface TokenService {
    fun generate(
        tokenConfig: TokenConfig,
        vararg tokenClaims: TokenClaim
    ): String
}