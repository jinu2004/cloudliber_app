package come.jinu.cloudlibre

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import come.jinu.cloudlibre.adaptersAndDataclass.ApiAdapter
import come.jinu.cloudlibre.apiCloudeliber.ApiClass
import come.jinu.cloudlibre.apiCloudeliber.ApiviewModel
import come.jinu.cloudlibre.databinding.ActivityExpandViewOfDataBinding


class ExpandData : AppCompatActivity() {
	private lateinit var apiViewModel: ApiviewModel
	private lateinit var binding: ActivityExpandViewOfDataBinding
	private var data = ArrayList<ApiClass>()



	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		binding = ActivityExpandViewOfDataBinding.inflate(layoutInflater)
		setContentView(binding.root)
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor = ContextCompat.getColor(this, R.color.black)
		apiViewModel = ViewModelProvider(this)[ApiviewModel::class.java]
		val category = intent.getStringExtra("category")

		binding.toolbar.title = category
		binding.toolbar.setNavigationOnClickListener {
			startActivity(Intent(this,MainActivity::class.java))
		}
		apiViewModel.getBookList().observe(this) {
				items ->data.addAll(items)
				val filteredList = data.filter { it.subgenre == category }
				val adapter = ApiAdapter(filteredList)
				binding.listview.layoutManager  = GridLayoutManager(this,3)
				binding.listview.adapter = adapter
		}

	}
}