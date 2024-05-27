package com.ram.buspass.helper.resource.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy


@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUserDetails(userEntity: List<UserEntity>)



}






