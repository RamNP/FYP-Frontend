package com.ram.buspass.helper.resource.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    val id: Int = 0,
    @ColumnInfo(name ="role")
    val role: String? = null,
    @ColumnInfo(name ="address")
    val address: String? = null,
    @ColumnInfo(name ="photo_image")
    val photo_image: String? = null,
    @ColumnInfo(name ="password")
    val password: String? = null,
    @ColumnInfo(name ="email")
    val email: String? = null,
    @ColumnInfo(name ="username")
    val username: String? = null
)