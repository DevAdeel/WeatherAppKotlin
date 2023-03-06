package com.kotlin.watherapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kotlin.watherapp.core.RetroResponse
import com.kotlin.watherapp.core.services.RetroService
import com.kotlin.watherapp.models.weather.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private const val TAG = "MainActivityViewModel"
class MainActivityViewModel : ViewModel() {

    val coroutineScope = CoroutineScope(Job() + Dispatchers.Main)

    private val _weatherResponse: MutableLiveData<RetroResponse<WeatherResponse>> =
        MutableLiveData()

    val weatherResponse: LiveData<RetroResponse<WeatherResponse>>
        get() = _weatherResponse

    fun getWeatherData(cityName: String) {
        _weatherResponse.value = RetroResponse.Loading
        RetroService.retrofitService.getWeatherData(cityName,"eb85ef4dabd846892e3ca9efbfe79b13")
            .enqueue(object : Callback<WeatherResponse>{
                override fun onResponse(
                    call: Call<WeatherResponse>,
                    response: Response<WeatherResponse>
                ) {
                    if(response.isSuccessful && response.body() != null){
                        _weatherResponse.value = RetroResponse.Success(value = response.body()!!)
                    }else{
                        Log.e(TAG, "onResponse: ${response.message()}")
                        _weatherResponse.value = RetroResponse.Failure(message = "No information found. Check city name", throwable = Exception(response.errorBody()?.string()))
                    }
                }

                override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                    Log.e(TAG, "onFailure: ", t)
                    _weatherResponse.value = RetroResponse.Failure(message = "No information found. Check internet connection", throwable = t)
                }
            })
    }

}