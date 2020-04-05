package com.example.android.market_master.di.outlet

import com.example.android.market_master.datasource.network.networkrequest.outlet.OutletNetworkRequests
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OutletServiceModule {

    private val logger : HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private  val okHttp:OkHttpClient.Builder = OkHttpClient.Builder().addInterceptor(logger)
    @Singleton
    @Provides

    fun providesGsonBuilder(): Gson {
        return GsonBuilder()
            .setLenient()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }
    @Singleton
    @Provides
    fun providesServices(gson:Gson): Retrofit.Builder{
        return  Retrofit.Builder()
            .baseUrl("https://script.google.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttp.build())
        //.addConverterFactory(ScalarsConverterFactory.create())

    }

    @Singleton
    @Provides
    fun providesOutletService(retrofit: Retrofit.Builder): OutletNetworkRequests {
        return retrofit
            .build()
            .create(OutletNetworkRequests::class.java)
    }
}

