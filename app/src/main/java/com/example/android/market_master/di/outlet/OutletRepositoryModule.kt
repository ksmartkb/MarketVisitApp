package com.example.android.market_master.di.outlet

import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletCacheMapper
import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletDao
import com.example.android.market_master.datasource.network.model.outlet.OutletNetworkMapper
import com.example.android.market_master.datasource.network.networkrequest.outlet.OutletNetworkRequests
import com.example.android.market_master.repository.OutletRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object OutletRepositoryModule {

    @Singleton
    @Provides
    fun providesOutletRepositoryModule(
        outletDao: OutletDao,
        outletNetworkRequests: OutletNetworkRequests,
        cacheMapper: OutletCacheMapper,
        outletNetworkMapper: OutletNetworkMapper
     ): OutletRepository {
        return OutletRepository(outletDao,outletNetworkRequests,cacheMapper,outletNetworkMapper)
    }


}