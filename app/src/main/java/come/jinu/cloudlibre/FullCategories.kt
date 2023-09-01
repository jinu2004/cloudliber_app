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
		list.add(CategoryList("Mystery",R.drawable.icons8_mystery_64,"fiction"))
		list.add(CategoryList("Science Fiction",R.drawable.icons8_science_fiction_64,"fiction"))
		list.add(CategoryList("Historical Fiction",R.drawable.icons8_history_fiction_64,"fiction"))
		list.add(CategoryList("Adventure",R.drawable.icons8_adventures_64,"fiction"))
		list.add(CategoryList("Humor",R.drawable.icons8_humor_64,"fiction"))
		list.add(CategoryList("Memoir",R.drawable.icons8_memoir_64,"non-fiction"))
		list.add(CategoryList("Personal Finance",R.drawable.icons8_money_yours_48,"non-fiction"))
		list.add(CategoryList("True Crime",R.drawable.icons8_evidence_48,"non-fiction"))
		list.add(CategoryList("Science",R.drawable.icons8_test_tube_64,"non-fiction"))
		list.add(CategoryList("History",R.drawable.icons8_history_64,"non-fiction"))
		list.add(CategoryList("Romance",R.drawable.icons8_romance_64,"fiction"))
		list.add(CategoryList("Thriller",R.drawable.icons8_thriller_64,"fiction"))
		list.add(CategoryList("Horror",R.drawable.icons8_horror_64,"fiction"))
		list.add(CategoryList("Fantasy",R.drawable.icons8_fantasy_64,"fiction"))
		list.add(CategoryList("Cooking",R.drawable.icons8_cooking_book_64,"non-fiction"))
		list.add(CategoryList("Biography",R.drawable.icons8_biography_64,"non-fiction"))
		list.add(CategoryList("Fantasy",R.drawable.icons8_fantasy_64,"non-fiction"))
		list.add(CategoryList("Health and wellness",R.drawable.icons8_health_64,"non-fiction"))
		list.add(CategoryList("Essay",R.drawable.icons8_essay_65,"non-fiction"))
		list.add(CategoryList("Technology",R.drawable.icons8_ai_64,"non-fiction"))
		list.add(CategoryList("psychology",R.drawable.icons8_psychology_64,"non-fiction"))
		list.add(CategoryList("Philosophy",R.drawable.icons8_philosopher_64,"non-fiction"))
		list.add(CategoryList("Travel",R.drawable.icons8_map_64,"non-fiction"))
		list.add(CategoryList("Motivational",R.drawable.icons8_motivation_daily_quotes_50,"non-fiction"))
		list.add(CategoryList("Business",R.drawable.icons8_increase_64,"non-fiction"))
		list.add(CategoryList("parenting",R.drawable.icons8_parenting_48,"non-fiction"))
		list.add(CategoryList("Contemporary",R.drawable.icons8_modern_art_64,"fiction"))
		list.add(CategoryList("Young Adult",R.drawable.icons8_18_plus_64,"fiction"))

		binding.recyclerView.layoutManager = GridLayoutManager(this,1)
		val adapter = CategoryListAdapter(list)
		binding.recyclerView.adapter = adapter
		adapter.setOnclickListener(object :CategoryListAdapter.OnClickListener{
			override fun onClick(position: Int) {
				if (list[position].genre == "fiction"){
					val intent = Intent(this@FullCategories,ExpandData::class.java)
					intent.putExtra("category",list[position].title)
					intent.putExtra("fiction_nonFiction",true)
					startActivity(intent)
				}

				else if(list[position].genre == "non-fiction"){
					val intent = Intent(this@FullCategories,ExpandData::class.java)
					intent.putExtra("category",list[position].title)
					intent.putExtra("fiction_nonFiction",false)
					startActivity(intent)
				}

			}
		})


		binding.toolbar2.setNavigationOnClickListener { startActivity(Intent(this,MainActivity::class.java)) }

	}
}