package com.gm.plugins

import com.gm.data.media.dataSource.MediaDataSource
import com.gm.route.authenticate
import com.gm.route.login
import com.gm.route.register
import com.gm.data.user.dataSource.UserDataSource
import com.gm.route.deleteMediaFromUser
import com.gm.route.getBookmarkedMediaList
import com.gm.route.getLikeMediaList
import com.gm.route.getMediaById
import com.gm.route.upsertMediaToUser
import io.ktor.server.application.*
import io.ktor.server.routing.*
import security.hash.service.HashingService
import security.jwt.model.TokenConfig
import security.jwt.service.TokenService

fun Application.configureRouting(
    mediaDataSource: MediaDataSource,
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

        getLikeMediaList(mediaDataSource, userDataSource)
        getBookmarkedMediaList(mediaDataSource, userDataSource)
        getMediaById(mediaDataSource, userDataSource)
        deleteMediaFromUser(mediaDataSource, userDataSource)
        upsertMediaToUser(mediaDataSource, userDataSource)
    }
}
