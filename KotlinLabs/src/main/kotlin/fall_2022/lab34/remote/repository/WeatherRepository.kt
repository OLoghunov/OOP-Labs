package lab34.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import lab34.remote.api.ReverseGeocoderApi
import lab34.remote.api.WeatherApi
import lab34.remote.models.CurrentWeather
import lab34.remote.models.ReversedCountry

class WeatherRepository(
    private val weatherApi: WeatherApi,
    private val reverseGeocoderApi: ReverseGeocoderApi
) {

    suspend fun getCurrentWeather(apiKey: String, queryCountry: String, isAqiNeeded: String): CurrentWeather {
        return withContext(Dispatchers.IO) {
            weatherApi.getCurrentWeatherAsync(apiKey, queryCountry, isAqiNeeded)
        }.await()
    }

    suspend fun getCountryNameByCoordinates(latitude: String, longitude: String, format: String): ReversedCountry {
        return withContext(Dispatchers.IO) {
            reverseGeocoderApi.getCountryNameByCoordinates(latitude, longitude, format)
        }.await()
    }
}