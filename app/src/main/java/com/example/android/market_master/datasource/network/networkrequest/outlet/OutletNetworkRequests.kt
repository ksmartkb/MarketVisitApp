package com.example.android.market_master.datasource.network.networkrequest.outlet

import com.example.android.market_master.datasource.network.model.outlet.OutletNetworkEntity
import retrofit2.http.GET
import retrofit2.http.Query


interface OutletNetworkRequests {

    @GET("macros/s/AKfycbwHRcYPjC2W-ME3XgARSra01YxFOUhdJ5zfGF9-TDeOC5RG7UHoiggJctq5eBlc-g/exec")
    suspend fun getOutlets(
        @Query("action") action:String ): List<OutletNetworkEntity>

}