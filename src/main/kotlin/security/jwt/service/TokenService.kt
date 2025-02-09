package security.jwt.service

import security.jwt.model.TokenClaim
import security.jwt.model.TokenConfig

interface TokenService {
    fun generate(
        tokenConfig: TokenConfig,
        vararg tokenClaims: TokenClaim
    ): String
}