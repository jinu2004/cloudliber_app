package come.jinu.cloudlibre

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import come.jinu.cloudlibre.apiCloudeliber.ApiInstance
import come.jinu.cloudlibre.databinding.ActivityItemPageBinding
import retrofit2.HttpException
import java.io.IOException

class ItemPage : AppCompatActivity() {
	private lateinit var itemBinding:ActivityItemPageBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_page)
		itemBinding = ActivityItemPageBinding.inflate(layoutInflater)
		setContentView(itemBinding.root)
		itemBinding.animation.visibility = View.VISIBLE
		val title = intent.getStringExtra("title").toString()
		val genre = intent.getStringExtra("genre").toString()
		getContentToShow(title,genre)






		Handler(Looper.getMainLooper()).postDelayed({
			getContentToShow(title, genre)
		},1000)
	}




	private fun getContentToShow(title:String, genre:String){
		lifecycleScope.launchWhenCreated {

			val response = try {
				ApiInstance.api.getFullDetail(title = title, genre = genre)
			}
			catch (e: IOException){
				Log.e("response","net work error may be")

				return@launchWhenCreated

			}
			catch (e: HttpException){
				Log.e("item","page is not available")
				return@launchWhenCreated
			}
			if (response.body()!=null && response.isSuccessful) {
				val data = response.body()!!
				itemBinding.animation.visibility = View.GONE
				itemBinding.authorItemView.text = data.author
				itemBinding.headerTitleItem.text = data.title
				itemBinding.rateItem.text = data.rating
				itemBinding.starRateTextBig.text = data.rating
				itemBinding.publisher.text = data.publisher
				itemBinding.about.text = data.about
				Glide.with(itemBinding.root.context)
					.load(data.coverpage)
					.into(itemBinding.imageCover)
				try {
					when(data.rating.toInt()){
						1-> itemBinding.star1.setColorFilter(R.color.yellow)
						2-> itemBinding.apply {
							star1.setColorFilter(R.color.yellow)
							star2.setColorFilter(R.color.yellow)
						}
						3-> itemBinding.apply {
							star1.setColorFilter(R.color.yellow)
							star2.setColorFilter(R.color.yellow)
							star3.setColorFilter(R.color.yellow)
						}
						4-> itemBinding.apply {
							star1.setColorFilter(R.color.yellow)
							star2.setColorFilter(R.color.yellow)
							star3.setColorFilter(R.color.yellow)
							star4.setColorFilter(R.color.yellow)
						}
						5-> itemBinding.apply {
							star1.setColorFilter(R.color.yellow)
							star2.setColorFilter(R.color.yellow)
							star3.setColorFilter(R.color.yellow)
							star4.setColorFilter(R.color.yellow)
							star5.setColorFilter(R.color.yellow)
						}
						else->{
						}

					}
				} catch (nfe: NumberFormatException) {
					Log.e(data.rating,"its not allowed")
				}



				Log.i("title",data.title)
				Log.i("author",data.author)
				Log.i("rate",data.rating)
				Log.i("about",data.about)
			}
			else Log.e("response","is not successful")

		}
	}


}