package come.jinu.cloudlibre

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

		val list =ArrayList<CategoryList>()
		list.add(CategoryList("Mystery",R.drawable.book_svgrepo_com))
		list.add(CategoryList("Mystery",R.drawable.book_svgrepo_com))
		list.add(CategoryList("Mystery",R.drawable.book_svgrepo_com))
		binding.recyclerView.layoutManager = GridLayoutManager(this,2)
		binding.recyclerView.adapter = CategoryListAdapter(list)




	}
}