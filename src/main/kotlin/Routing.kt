package com.gm

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.gm.route.authenticate
import com.gm.route.login
import com.gm.route.register
import data.model.dataSource.UserDataSource
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.event.*
import security.hash.service.HashingService
import security.jwt.model.TokenConfig
import security.jwt.service.TokenService

fun Application.configureRouting(
    hashingService: HashingService,
    userDataSource: UserDataSource,
    tokenService: TokenService,
    tokenConfig: TokenConfig
) {
    routing {

        register(hashingService,
            userDataSource
        )

        login(hashingService,
            userDataSource,
            tokenService,
            tokenConfig
        )

        authenticate()
    }
}
