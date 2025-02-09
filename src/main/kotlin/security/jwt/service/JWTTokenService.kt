package security.jwt.service

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import security.jwt.model.TokenClaim
import security.jwt.model.TokenConfig
import java.util.Date

class JWTTokenService: TokenService {

    override fun generate(tokenConfig: TokenConfig,
                          vararg tokenClaims: TokenClaim
    ): String {

        val token = JWT.create()
            .withIssuer(tokenConfig.issuer)
            .withAudience(tokenConfig.audience)
            .withExpiresAt(Date(System.currentTimeMillis() + tokenConfig.expireDate))

        tokenClaims.forEach { tokenClaim ->
            token.withClaim(
                tokenClaim.name,
                tokenClaim.value
            )
        }

        return token.sign(Algorithm.HMAC256(tokenConfig.secret))
    }
}
