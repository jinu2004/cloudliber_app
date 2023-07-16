package come.jinu.cloudlibre

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.annotation.RequiresApi
import com.bumptech.glide.Glide
import com.google.gson.annotations.SerializedName
import come.jinu.cloudlibre.apiCloudeliber.FullDetailFromApi
import come.jinu.cloudlibre.databinding.ActivityAboutItemBinding

class AboutItem : AppCompatActivity() {
	private lateinit var binding: ActivityAboutItemBinding
	@RequiresApi(33)
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityAboutItemBinding.inflate(layoutInflater)
		setContentView(binding.root)
		val title = intent.getStringExtra(ItemPage.titles).toString()
		val author = intent.getStringExtra(ItemPage.auhtor).toString()
		val about = intent.getStringExtra(ItemPage.about).toString()
		val date = intent.getStringExtra(ItemPage.date).toString()
		val publisher = intent.getStringExtra(ItemPage.publish).toString()
		val img = intent.getStringExtra(ItemPage.coverpage).toString()
		val subgenre = intent.getStringExtra(ItemPage.subgenre).toString()
		val isbn = intent.getStringExtra(ItemPage.isbn).toString()


		binding.apply {
			heading.text = title
			authorData.text = author
			aboutData.text = about
			dateOfPublish.text = date
			publisherData.text = publisher
			genreData.text = subgenre
			isbNom.text = isbn
		}
		Glide.with(binding.root.context)
			.load(img)
			.into(binding.productImg)



	}
}