package come.jinu.cloudlibre

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.GridLayoutManager
import come.jinu.cloudlibre.adaptersAndDataclass.CategoryList
import come.jinu.cloudlibre.adaptersAndDataclass.CategoryListAdapter
import come.jinu.cloudlibre.databinding.ActivityFullcatogoriesBinding

class FullCategories : AppCompatActivity() {
	private lateinit var binding:ActivityFullcatogoriesBinding
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityFullcatogoriesBinding.inflate(layoutInflater)
		setContentView(binding.root)
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor = ContextCompat.getColor(this, R.color.black)


		val list =ArrayList<CategoryList>()
		list.add(CategoryList("Science",R.drawable.icons8_test_tube_64))
		list.add(CategoryList("History",R.drawable.icons8_history_64))
		list.add(CategoryList("Romance",R.drawable.icons8_romance_64))
		list.add(CategoryList("Thriller",R.drawable.icons8_thriller_64))
		list.add(CategoryList("Horror",R.drawable.icons8_horror_64))
		list.add(CategoryList("Fantasy",R.drawable.icons8_fantasy_64))
		list.add(CategoryList("Cooking",R.drawable.icons8_cooking_book_64))
		list.add(CategoryList("Biography and memoir",R.drawable.icons8_biography_64))
		list.add(CategoryList("Fantasy",R.drawable.icons8_fantasy_64))
		list.add(CategoryList("Health",R.drawable.icons8_health_64))
		list.add(CategoryList("essay",R.drawable.icons8_essay_65))
		list.add(CategoryList("Technology",R.drawable.icons8_ai_64))
		list.add(CategoryList("psychology",R.drawable.icons8_psychology_64))
		list.add(CategoryList("Philosophy",R.drawable.icons8_philosopher_64))
		list.add(CategoryList("Travel",R.drawable.icons8_map_64))
		list.add(CategoryList("Motivation",R.drawable.icons8_motivation_daily_quotes_50))
		list.add(CategoryList("Business",R.drawable.icons8_increase_64))
		list.add(CategoryList("parenting",R.drawable.icons8_parenting_48))
		list.add(CategoryList("Contemporary",R.drawable.icons8_modern_art_64))
		list.add(CategoryList("Young Adult",R.drawable.icons8_18_plus_64))
		binding.recyclerView.layoutManager = GridLayoutManager(this,1)
		val adapter = CategoryListAdapter(list)
		binding.recyclerView.adapter = adapter
		adapter.setOnclickListener(object :CategoryListAdapter.OnClickListener{
			override fun onClick(position: Int) {
				TODO("Not yet implemented")
			}
		})


		binding.toolbar2.setNavigationOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }




	}
}