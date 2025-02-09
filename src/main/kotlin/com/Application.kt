package com.gm.com.aca.plugins

import com.aca.data.model.User
import com.aca.data.model.dataSource.MongoUserDataSource
import com.aca.plugins.configureMonitoring
import com.aca.plugins.configureRouting
import com.aca.plugins.configureSecurity
import com.aca.plugins.configureSerialization
import com.mongodb.kotlin.client.coroutine.MongoClient
import io.ktor.server.application.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    val mongoPassword = "aca-password"
    val databaseName = "aca-database"
    val connectionString = "mongodb+srv://aca-user:$mongoPassword@cluster-aca.jykim.mongodb.net/$databaseName?retryWrites=true&w=majority&appName=Cluster-aca"

    val mongoClient = MongoClient.create(connectionString)
    val database = mongoClient.getDatabase(databaseName)

    val userDataSource = MongoUserDataSource(database)

    GlobalScope.launch {

        val dummyMediaList = ArrayList<String>()
        dummyMediaList.add("1")
        dummyMediaList.add("2")
        dummyMediaList.add("3")

        val user = User(
            name = "test name",
            email = "test@gmail.com",
            hash = "test hash",
            salt = "test salt",
            mediaList = dummyMediaList
        )

        userDataSource.insertUser(user)

    }

    configureMonitoring()
    configureSerialization()
    configureSecurity()
    configureRouting()
}
