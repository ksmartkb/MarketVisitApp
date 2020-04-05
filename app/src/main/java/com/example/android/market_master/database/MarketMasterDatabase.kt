package com.example.android.market_master.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletDao
import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletCacheEntity

@Database(entities = [OutletCacheEntity::class ], version = 1)
abstract  class MarketMasterDatabase:RoomDatabase() {
    abstract fun outletDao(): OutletDao

    companion object{
        val DATABASE_NAME: String = "market_master_db"
    }
}