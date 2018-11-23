package pedro.com.ioasystestekotlin.data.cache.dao

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import pedro.com.ioasystestekotlin.data.cache.entities.HeaderAccess

@Dao
interface HeaderAccessDao {

    @Query("SELECT * FROM headers Where uid == (:uid)")
    fun getHeader(uid: String): HeaderAccess

    @Insert(onConflict = REPLACE)
    fun insertAll(vararg headers: HeaderAccess)

}