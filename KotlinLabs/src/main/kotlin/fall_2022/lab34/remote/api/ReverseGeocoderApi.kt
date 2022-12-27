package lab34.remote.api

import kotlinx.coroutines.Deferred
import lab34.remote.models.ReversedCountry
import retrofit2.http.GET
import retrofit2.http.Query

interface ReverseGeocoderApi {

    @GET("reverse")
    fun getCountryNameByCoordinates(
        @Query("lat") latitude: String,
        @Query("lon") longitude: String,
        @Query("format") dataFormat: String
    ): Deferred<ReversedCountry>
}