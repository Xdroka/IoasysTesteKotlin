package pedro.com.ioasystestekotlin.data.cache.room.repo

import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess

interface HeaderRoom {
    fun insertHeader(headerAccess: HeaderAccess): Boolean

    fun getHeader(uid: String): HeaderAccess?

}