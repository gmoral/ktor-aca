package data.model.dataSource

import data.model.User

interface UserDataSource {
    suspend fun getUserByEmail(email: String): User?
    suspend fun insertUser(user: User): Boolean
    suspend fun updateUser(user: User): Boolean

}