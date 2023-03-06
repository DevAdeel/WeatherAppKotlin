package com.kotlin.watherapp.models.weather

data class WeatherResponse (

    var base: String? = null,

    var main: Main? = null,

    var clouds: Clouds? = null,

    var dt: Int? = null,

    var sys: Sys? = null,

    var timezone: Int? = null,

    var id: Int? = null,

    var name: String? = null,

    var cod: Int? = null
)