package come.jinu.cloudlibre.apiCloudeliber

import come.jinu.cloudlibre.BuildConfig
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query


interface CloudLiberApi {
	@Headers("API-KEY:${BuildConfig.API_KEY_1}")
	@GET("api/android/fetchHalfData/fiction")
	suspend fun getFictionData():Response<List<ApiClass>>
	@Headers("API-KEY:${BuildConfig.API_KEY_1}")
	@GET("/api/android/fetchHalfData/nonfiction")
	suspend fun getNonFictionData():Response<List<ApiClass>>
	@Headers("API-KEY:${BuildConfig.API_KEY_1}")
	@GET("/api/android/fullbookdetails?")
	suspend fun getFullDetail(
		@Query("title") title: String,
		@Query("genre") genre:String
	): Response<FullDetailFromApi>

}

