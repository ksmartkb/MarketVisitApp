package com.example.android.market_master.datasource.cache.dao.outlet.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OutletDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(outletEntity: OutletCacheEntity): Long

    @Query("SELECT * FROM outlets")
    suspend fun get(): List<OutletCacheEntity>

}