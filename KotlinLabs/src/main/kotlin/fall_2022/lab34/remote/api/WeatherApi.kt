package lab34.remote.api

import kotlinx.coroutines.Deferred
import lab34.remote.models.CurrentWeather
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("current.json")
    fun getCurrentWeatherAsync(
        @Query("key") apiKey: String,
        @Query("q") queryCountry: String,
        @Query("aqi") aqi: String
    ): Deferred<CurrentWeather>
}