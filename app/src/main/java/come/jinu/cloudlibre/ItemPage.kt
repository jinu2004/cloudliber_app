package come.jinu.cloudlibre

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
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
		val title = intent.getStringExtra("title").toString()
		val genre = intent.getStringExtra("genre").toString()
		getContentToShow(title,genre)
	}



	private fun getContentToShow(title:String, genre:String){
		lifecycleScope.launchWhenCreated {
			val progression = ProgressDialog(this@ItemPage)
			progression.show()
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
			if (response.body()!=null) {
				val data = response.body()!!
				progression.dismiss()
				itemBinding.authorItemView.text = data.author
				itemBinding.headerTitleItem.text = data.title
				itemBinding.rateItem.text= data.rating
				itemBinding.publisher.text = data.publisher
				itemBinding.about.text = data.about

				Log.i("title",data.title)
				Log.i("author",data.author)
				Log.i("rate",data.rating)
				Log.i("about",data.about)
			}
			else Log.e("response","is not successful")

		}
	}


}