package com.example.android.market_master.datasource.cache.dao.outlet.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "outlets")
class OutletCacheEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "outlet_id")
    var outlet_id: String,

    @ColumnInfo(name = "outletName")
    var outletName: String,

    @ColumnInfo(name = "outletClass")
    var outletClass: String,

    @ColumnInfo(name = "area")
    var area: String,

    @ColumnInfo(name = "latitude")
    var latitude: String,

    @ColumnInfo(name = "longitude")
    var longitude: String,

    @ColumnInfo(name = "beats")
    var beats: String,

    @ColumnInfo(name = "status")
    var status: String,

    @ColumnInfo(name = "approved")
    var approved: String
)

