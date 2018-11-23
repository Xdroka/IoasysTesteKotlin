package pedro.com.ioasystestekotlin.data.cache

import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess

fun HeaderAccess.headerMapper(): Map<String, String> = mapOf(
        "access-token" to access_token,
        "client" to client,
        "uid" to uid
)

fun Map<String, String>.convertToHeader(): HeaderAccess =
        HeaderAccess(
                access_token = this["access-token"] ?: "",
                uid = this["uid"] ?: "",
                client = this["client"] ?: ""
        ).apply {
                println(this.toString())
        }
