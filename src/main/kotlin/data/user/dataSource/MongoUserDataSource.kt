package com.gm.data.user.dataSource


import com.mongodb.client.model.Filters.eq
import com.mongodb.client.model.ReplaceOptions
import com.mongodb.kotlin.client.coroutine.MongoDatabase
import com.gm.data.user.model.User
import kotlinx.coroutines.flow.firstOrNull

class MongoUserDataSource(
    database: MongoDatabase
): UserDataSource {

    private val userCollection = database.getCollection<User>("users")

    override suspend fun getUserByEmail(email: String): User? {
        return userCollection.find(
            eq(User::email.name, email)
        ).firstOrNull()
    }

    override suspend fun insertUser(user: User): Boolean {
        return userCollection.insertOne(user).wasAcknowledged()
    }

    override suspend fun updateUser(user: User): Boolean {
        val query =  eq(User::email.name, user.email)
        val options = ReplaceOptions().upsert(true)

        return userCollection.replaceOne(query, user, options).wasAcknowledged()
    }
}