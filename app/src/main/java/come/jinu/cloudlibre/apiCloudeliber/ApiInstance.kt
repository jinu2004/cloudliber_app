package come.jinu.cloudlibre.apiCloudeliber

import come.jinu.cloudlibre.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiInstance {
	val api:CloudLiberApi by lazy {
		    Retrofit.Builder()
			.baseUrl(BuildConfig.WEB_LINK)
			.addConverterFactory(GsonConverterFactory.create())
			.build()
			.create(CloudLiberApi::class.java)
	}
}