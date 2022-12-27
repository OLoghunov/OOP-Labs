package lab34

import lab34.bot.WeatherBot
import lab34.remote.RetrofitClient
import lab34.remote.RetrofitType
import lab34.remote.repository.WeatherRepository

fun main() {
	val weatherRetrofitClient = RetrofitClient.getRetrofit(RetrofitType.WEATHER)
	val reverseGeocoderRetrofitClient = RetrofitClient.getRetrofit(RetrofitType.REVERSE_GEOCODER)

	val bot = WeatherBot(
		WeatherRepository(
			weatherApi = RetrofitClient.getWeatherApi(weatherRetrofitClient),
			reverseGeocoderApi = RetrofitClient.getReverseGeocoderApi(reverseGeocoderRetrofitClient)
		)
	).createBot()

	bot.startPolling()
}
