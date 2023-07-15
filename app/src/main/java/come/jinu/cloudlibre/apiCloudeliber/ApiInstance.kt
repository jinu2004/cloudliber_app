package come.jinu.cloudlibre.apiCloudeliber

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
	val api:CloudLiberApi by lazy {
		Retrofit.Builder()
			.baseUrl("https://clwebapp.onrender.com/")
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(CloudLiberApi::class.java)

	}
}