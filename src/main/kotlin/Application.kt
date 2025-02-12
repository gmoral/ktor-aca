package com.gm

import com.gm.data.media.dataSource.MongoMediaDataSource
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.gm.data.user.dataSource.MongoUserDataSource
import com.gm.plugins.configureMonitoring
import com.gm.plugins.configureRouting
import com.gm.plugins.configureSecurity
import com.gm.plugins.configureSerialization
import io.ktor.server.application.*
import security.hash.service.SHA256HashService
import security.jwt.model.TokenConfig
import security.jwt.service.JWTTokenService

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val mongoPassword = "aca-password"
    val databaseName = "aca-database"

    val connectionString = "mongodb+srv://aca-user:$mongoPassword@cluster-aca.jykim.mongodb.net/$databaseName?retryWrites=true&w=majority&appName=Cluster-aca"

    val mongoClient = MongoClient.create(connectionString)
    val database = mongoClient.getDatabase(databaseName)

    val mediaDataSource = MongoMediaDataSource(database)
    val userDataSource = MongoUserDataSource(database)

//    GlobalScope.launch {
//
//        val dummyMediaList = ArrayList<String>()
//        dummyMediaList.add("1")
//        dummyMediaList.add("2")
//        dummyMediaList.add("3")
//
//        val user = User(
//            name = "test name",
//            email = "test@gmail.com",
//            hash = "test hash",
//            salt = "test salt",
//            mediaList = dummyMediaList
//        )
//
//        userDataSource.insertUser(user)
//
//    }

    val tokenService = JWTTokenService()
    val tokenConfig = TokenConfig(
        issuer = environment.config.property("jwt.issuer").toString(),
        audience = environment.config.property("jwt.audience").toString(),
        expireDate = 30L * 1000L * 60L * 60L * 24L,
        secret = "Pepe"
    )

    val hashingService = SHA256HashService()

    configureSecurity(tokenConfig)
    configureMonitoring()
    configureSerialization()
    configureRouting(
        mediaDataSource,
        hashingService,
        userDataSource,
        tokenService,
        tokenConfig
    )
}
