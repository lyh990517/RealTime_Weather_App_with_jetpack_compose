package com.rsupport.network.di

import com.rsupport.network.retrofit.WeatherDataRequester
import com.rsupport.network_contract.IWeatherDataRequester
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RequesterModule {
    @Binds
    abstract fun bindWeatherDataRequester(weatherDataRequester: WeatherDataRequester): IWeatherDataRequester
}