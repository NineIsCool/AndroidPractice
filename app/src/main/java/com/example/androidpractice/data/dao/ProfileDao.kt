package com.example.androidpractice.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.androidpractice.data.entity.ProfileDbEntity

@Dao
interface ProfileDao {
    @Query("SELECT * FROM ProfileDbEntity LIMIT 1")
    suspend fun getUser(): ProfileDbEntity?

    @Insert
    suspend fun insert(driverDbEntity: ProfileDbEntity)
}