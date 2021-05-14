package APIs

import retrofit2.Call
import retrofit2.http.GET

interface APIWeatherOpen {

    @GET("data/2.5/weather?id=4956184&units=imperial&appid=ede400d213629e84c2076a755f051fe2")
    fun fetchContents(): Call<OpenWeatherResponse>
}