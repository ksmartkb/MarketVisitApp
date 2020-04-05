package com.example.android.market_master.datasource.cache.dao.outlet.model

import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.utils.EntityMapper
import javax.inject.Inject

class OutletCacheMapper
@Inject
constructor(): EntityMapper<OutletCacheEntity, Outlet>
{
    override fun mapFromEntity(entity: OutletCacheEntity): Outlet {
        return Outlet(
            outlet_id = entity.outlet_id,
            outletName = entity.outletName,
            outletClass = entity.outletClass,
            latitude = entity.latitude,
            longitude = entity.longitude,
            approved = entity.approved,
            status = entity.status,
            beats = entity.beats,
            area = entity.area
        )
    }

    override fun mapToEntity(domainModel: Outlet): OutletCacheEntity {
        return OutletCacheEntity(
            outlet_id = domainModel.outlet_id,
            outletName = domainModel.outletName,
            outletClass = domainModel.outletClass,
            latitude = domainModel.latitude,
            longitude = domainModel.longitude,
            approved = domainModel.approved,
            status = domainModel.status,
            beats = domainModel.beats,
            area = domainModel.area
        )
    }
    fun mapFromEntityList(entities:List<OutletCacheEntity>):    List<Outlet>{
        return entities.map{mapFromEntity(it)}
    }
}