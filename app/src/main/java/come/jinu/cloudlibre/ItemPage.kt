package come.jinu.cloudlibre

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import come.jinu.cloudlibre.apiCloudeliber.ApiInstance
import come.jinu.cloudlibre.apiCloudeliber.FullDetailFromApi
import come.jinu.cloudlibre.databinding.ActivityItemPageBinding
import retrofit2.HttpException
import java.io.IOException

class ItemPage : AppCompatActivity() {
	private lateinit var itemBinding:ActivityItemPageBinding
	companion object{
		const val titles = "title"
		const val auhtor = "auhtor"
		const val about = "about"
		const val date = "date"
		const val publish = "publish"
		const val isbn = "isbn"
		const val subgenre = "subgenre"
		const val coverpage = "coverpage"
	}
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_item_page)
		itemBinding = ActivityItemPageBinding.inflate(layoutInflater)
		setContentView(itemBinding.root)
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
		itemBinding.animation.visibility = View.VISIBLE
		val title = intent.getStringExtra("title").toString()
		val genre = intent.getStringExtra("genre").toString()
		getContentToShow(title,genre)

		itemBinding.toolbar2.setNavigationOnClickListener{
			finish()
		}

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
					when(data.rating.toFloat().toInt()){
						4-> itemBinding.star5.setColorFilter(R.color.grey_800)
						3-> itemBinding.apply {
							star4.setColorFilter(R.color.grey_800)
							star5.setColorFilter(R.color.grey_800)
						}
						2-> itemBinding.apply {
							star3.setColorFilter(R.color.grey_800)
							star4.setColorFilter(R.color.grey_800)
							star5.setColorFilter(R.color.grey_800)
						}
						1-> itemBinding.apply {
							star2.setColorFilter(R.color.grey_800)
							star3.setColorFilter(R.color.grey_800)
							star4.setColorFilter(R.color.grey_800)
							star5.setColorFilter(R.color.grey_800)
						}
						else->{
						}

					}
				} catch (nfe: NumberFormatException) {
					Log.e(data.rating,"its not allowed")
				}

				itemBinding.expandAbout.setOnClickListener {
					val intent = Intent(this@ItemPage,AboutItem::class.java)
					intent.putExtra(titles,data.title)
					intent.putExtra(auhtor,data.author)
					intent.putExtra(about,data.about)
					intent.putExtra(date,data.datepublished)
					intent.putExtra(publish,data.publisher)
					intent.putExtra(isbn,data.isbnno)
					intent.putExtra(subgenre,data.subgenre)
					intent.putExtra(coverpage,data.coverpage)
					startActivity(intent)
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