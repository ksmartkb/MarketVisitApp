package com.example.android.market_master.datasource.network.model.outlet

import com.example.android.market_master.domain.Outlet
import com.example.android.market_master.utils.EntityMapper
import javax.inject.Inject

class OutletNetworkMapper
@Inject
constructor(): EntityMapper<OutletNetworkEntity, Outlet> {
    override fun mapFromEntity(entity: OutletNetworkEntity): Outlet {
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

    override fun mapToEntity(domainModel: Outlet): OutletNetworkEntity {
        return OutletNetworkEntity  (
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
    fun mapFromEntityList(entites:List<OutletNetworkEntity>):List<Outlet>{
        return entites.map { mapFromEntity(it) }
    }
}

