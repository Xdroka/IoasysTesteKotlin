package pedro.com.ioasystestekotlin.data.cache.room.repo

import pedro.com.ioasystestekotlin.application.MyApplication
import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess

class HeaderRoomImpl : HeaderRoom {

    override fun insertHeader(headerAccess: HeaderAccess): Boolean {
        if (MyApplication.database == null) return false

        MyApplication.database?.apply {
            headerAccessDao().insertAll(headerAccess)
        }
        Result
        return true
    }

    override fun getHeader(uid: String): HeaderAccess? = MyApplication.database?.headerAccessDao()?.getHeader(uid)

}