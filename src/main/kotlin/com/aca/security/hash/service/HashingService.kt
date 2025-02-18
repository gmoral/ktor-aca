package com.aca.security.hash.service

import com.aca.security.hash.model.Hash

interface HashingService {

    fun generateHash(
        password: String,
        saltLenght: Int = 23
    ): Hash

    fun verifyHash(
        password: String,
        hash: String,
        salt: String
    ): Boolean
}