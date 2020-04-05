package com.example.android.market_master.repository

import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletDao
import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.utils.DataState
import com.example.android.market_master.datasource.cache.dao.outlet.model.OutletCacheMapper
import com.example.android.market_master.datasource.network.model.outlet.OutletNetworkMapper
import com.example.android.market_master.datasource.network.networkrequest.outlet.OutletNetworkRequests
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class OutletRepository(
    private val outletDao: OutletDao,
    private val outletNetworkRequests: OutletNetworkRequests,
    private val cacheMapper: OutletCacheMapper,
    private val outletNetworkMapper: OutletNetworkMapper

) {
    suspend fun getOutlets(): Flow<DataState<List<Outlet>>> = flow {
        emit(DataState.Loading)
        delay(1000)
        try {
            val outletNetworkRequest = outletNetworkRequests.getOutlets("getOutlets")
            val outlets = outletNetworkMapper.mapFromEntityList(outletNetworkRequest)



            for (outlet in outlets){
                outletDao.insert(cacheMapper.mapToEntity(outlet))
            }
            val cachedOutlets = outletDao.get()
            emit(DataState.Success(cacheMapper.mapFromEntityList(cachedOutlets)))

        }catch (e:Exception){
           // Log.e("TEEEEEE",e.toString())

            emit(DataState.Error(e))
        }
    }
}