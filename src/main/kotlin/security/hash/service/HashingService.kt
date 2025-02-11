package security.hash.service

import security.hash.model.HashAndSalt

interface HashingService {

    fun generateHash(
        password: String,
        saltLenght: Int = 23
    ): HashAndSalt

    fun verifyHash(
        password: String,
        hash: String,
        salt: String
    ): Boolean
}