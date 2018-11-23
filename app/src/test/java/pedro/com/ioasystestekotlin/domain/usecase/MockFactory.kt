package pedro.com.ioasystestekotlin.domain.usecase

import pedro.com.ioasystestekotlin.data.ResultRequest
import pedro.com.ioasystestekotlin.domain.usecase.Random.Companion.CLIENT
import pedro.com.ioasystestekotlin.domain.usecase.Random.Companion.STRING
import pedro.com.ioasystestekotlin.domain.usecase.Random.Companion.TOKEN
import pedro.com.ioasystestekotlin.domain.usecase.Random.Companion.UID

class Random {
    companion object {
        const val STRING = "RANDOM"
        const val TOKEN = "access-token"
        const val UID = "uid"
        const val CLIENT = "client"
    }
}

fun mockListRandom() = listOf(STRING)

inline fun <reified T : Any> mockResultRequest(data: T? = null, e: Throwable? = null) =
        if (data != null) {
            ResultRequest.success(data)
        } else {
            ResultRequest.error(e as Throwable)
        }

fun mockHeaderMap() = mapOf(
        TOKEN to STRING,
        UID to STRING,
        CLIENT to STRING
)