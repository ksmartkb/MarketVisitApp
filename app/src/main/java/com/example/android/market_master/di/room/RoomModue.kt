package com.example.android.market_master.di.room

import android.content.Context
import androidx.room.Room
import com.example.android.market_master.database.MarketMasterDatabase
import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object RoomModue   {
    @Singleton
    @Provides
    fun providesMarketMasterDB(@ApplicationContext context :Context): MarketMasterDatabase {
        return Room.databaseBuilder(

            context,
            MarketMasterDatabase::class.java,
            MarketMasterDatabase.DATABASE_NAME,

        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun providesOutletDAO(masterDatabase: MarketMasterDatabase): OutletDao {
        return masterDatabase.outletDao()
    }
}