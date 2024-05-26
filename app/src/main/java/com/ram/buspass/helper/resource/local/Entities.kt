package com.ram.buspass.helper.resource.local
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class Entities(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name ="id")
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val name: String? = null,
    @ColumnInfo(name ="email")
    val email: String? = null,
    @ColumnInfo(name ="password")
    val password: String? = null
)