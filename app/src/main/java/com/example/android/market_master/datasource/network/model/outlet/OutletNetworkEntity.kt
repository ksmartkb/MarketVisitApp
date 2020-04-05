package com.example.android.market_master.datasource.network.model.outlet

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class OutletNetworkEntity(




    @SerializedName("outlet_id")
    @Expose
    var outlet_id:String,
        @SerializedName("outlet_name")
    @Expose
    var outletName:String,
    @SerializedName("outlet_class")
    @Expose
    var outletClass:String,
    @SerializedName("status")
    @Expose
    var status:String,
    @SerializedName("beats")
    @Expose
    var beats:String,
    @SerializedName("longitude")
    @Expose
    var longitude:String,
    @SerializedName("latitude")
    @Expose
    var latitude:String,
    @SerializedName("area")
    @Expose
    var area:String,
 @SerializedName("approvaed")
    @Expose
    var approved:String

)