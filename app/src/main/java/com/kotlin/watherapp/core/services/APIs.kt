package com.kotlin.watherapp.core.services

import com.kotlin.watherapp.models.weather.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface APIs {

    @GET("data/2.5/weather?")
    fun getWeatherData(@Query("q") cityName : String, @Query("APPID") appId : String) : Call<WeatherResponse>

}