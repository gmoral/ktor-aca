package security.hash.model

data class HashAndSalt(
    val hash: String,
    val salt: String
)