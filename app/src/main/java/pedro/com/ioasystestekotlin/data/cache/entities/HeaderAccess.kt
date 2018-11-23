package pedro.com.ioasystestekotlin.data.cache.entities

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "headers")
data class HeaderAccess(
        @PrimaryKey var uid: String,
        @ColumnInfo(name = "access-token") var access_token: String,
        @ColumnInfo var client: String
)

