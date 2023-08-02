package com.rsupport.core.di

import com.rsupport.core.network.api.IWeatherApi
import com.rsupport.core.network.api.WeatherApi
import com.rsupport.core.repository.IWeatherRepository
import com.rsupport.core.repository.WeatherRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoreModule {

    @Binds
    abstract fun bindWeatherApi(weatherApi: WeatherApi): IWeatherApi

    @Binds
    abstract fun bindWeatherRepository(weatherRepository: WeatherRepository): IWeatherRepository
}