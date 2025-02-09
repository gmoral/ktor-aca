package com.gm.com.aca.plugins

import com.aca.plugins.configureMonitoring
import com.aca.plugins.configureRouting
import com.aca.plugins.configureSecurity
import com.aca.plugins.configureSerialization
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val mongoPassword = "aca-password"
    val databaseName = "aca-database"
    val connectionString = "mongodb+srv://aca-user:$mongoPassword@cluster-aca.jykim.mongodb.net/$databaseName?retryWrites=true&w=majority&appName=Cluster-aca"

    val mongoClient = MongoClient.create(connectionString)
    val database = mongoClient.getDatabase(databaseName)


    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
