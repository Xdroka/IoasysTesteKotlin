package pedro.com.ioasystestekotlin.data.cache.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import pedro.com.ioasystestekotlin.data.cache.dao.HeaderAccessDao
import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess

@Database(entities = [HeaderAccess::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun headerAccessDao(): HeaderAccessDao
}