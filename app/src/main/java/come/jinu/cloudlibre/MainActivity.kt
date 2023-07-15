package come.jinu.cloudlibre

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import come.jinu.cloudlibre.apiCloudeliber.*
import come.jinu.cloudlibre.databinding.ActivityMainBinding
import come.jinu.cloudlibre.roomdatabase.RoomBookData
import come.jinu.cloudlibre.roomdatabase.RoomBookRecyclerAdapter
import come.jinu.cloudlibre.roomdatabase.RoomViewModel
import retrofit2.HttpException
import java.io.IOException

class MainActivity : AppCompatActivity() {
	private lateinit var auth: FirebaseAuth
	private lateinit var roomViewModel: RoomViewModel
	private lateinit var binding: ActivityMainBinding

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)
		auth = Firebase.auth

		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

		roomViewModel = ViewModelProvider(this)[RoomViewModel::class.java]
		cards(binding.card1Heading, binding.card1Recycler, "New release", "new")
		cards(binding.card2Heading, binding.card2Recycler, "History", "his")

		insertData("new")
		insertData("his")
		insertData("fav")
		insertData("for")


		val imageList = ArrayList<SlideModel>()
		imageList.add(SlideModel("https://cdn.pixabay.com/photo/2023/06/22/15/17/cat-8081701_1280.jpg",
			""))
		imageList.add(SlideModel("https://cdn.pixabay.com/photo/2023/06/21/19/21/seiser-alm-8080073_1280.jpg",
			""))
		imageList.add(SlideModel("https://cdn.pixabay.com/photo/2018/08/14/13/23/ocean-3605547_1280.jpg",
			""))
		imageList.add(SlideModel("https://cdn.pixabay.com/photo/2014/12/16/22/25/woman-570883_1280.jpg",
			""))
		binding.ads.setImageList(imageList, scaleType = ScaleTypes.FIT)
		binding.ads.setSlideAnimation(AnimationTypes.ZOOM_OUT)
		binding.ads.startSliding(3000)



	}






	//////////////////////////////////  onCreate function ends here  //////////






	public override fun onStart() {
		super.onStart()
		val currentUser = auth.currentUser
		if (currentUser == null) {
			startActivity(Intent(this,SigninActivity::class.java))
		}
	}


	@SuppressLint("SetTextI18n")
	override fun onResume() {
		super.onResume()
		apiCards(binding.card3Heading,binding.card3Recycler)
		apiCardsNon(binding.card4Heading,binding.card4Recycler)
	}

	@Suppress("DEPRECATION")
	@SuppressLint("SetTextI18n")
	private fun cards(heading: TextView,recyclerView: RecyclerView,tittle: String,category:String)
	{
		heading.text=tittle
		recyclerView.layoutManager = LinearLayoutManager(this,RecyclerView.HORIZONTAL,false)
		roomViewModel.getCategory(category).observe(this) { data ->
			val adapter = RoomBookRecyclerAdapter(data)
			recyclerView.adapter = adapter

			adapter.setOnItemClickListener(object: RoomBookRecyclerAdapter.OnItemClickListener{
				override fun onItemClick(position: Int) {
					val intent = Intent(this@MainActivity,ItemPage::class.java)
					intent.putExtra("position",position)
					startActivity(intent)
				}

			})

		}


	}

	private fun insertData(category: String){
		val newItem = RoomBookData(null,"Life","jinu",
			"","",100,R.drawable.bookshop_1,"",2.3,category)
		roomViewModel.addNewItem(newItem)

	}



	private fun apiCards(heading: TextView,recyclerView: RecyclerView){
		lifecycleScope.launchWhenCreated {
			val response = try {
				ApiInstance.api.getFictionData()
			}
			catch (e:IOException){
				Log.e("response","net work error may be")
				return@launchWhenCreated
			}
			catch (e:HttpException){
				Log.e("response","page is not available")
				return@launchWhenCreated
			}
			if (response.isSuccessful && response.body() != null) {
				val adapter = ApiAdapter()
				adapter.data = response.body()!!
				recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
				recyclerView.adapter = adapter
				adapter.setOnClickListener(object :ApiAdapter.OnClickListener{
					override fun onClick(position: Int,data:ApiClass) {
						val intent = Intent(this@MainActivity,ItemPage::class.java)
						intent.putExtra("title", data.title)
						intent.putExtra("genre", "fiction")
						startActivity(intent)
						Log.e("msg","${data.subgenre}")
						Log.e("msg","${data.title}")
					}
				})
				heading.text = response.body()!![0].subgenre
			}
			else Log.e("msg","false")

		}
	}
	private fun apiCardsNon(heading: TextView,recyclerView: RecyclerView){
		lifecycleScope.launchWhenCreated {
			val response = try {
				ApiInstance.api.getNonFictionData()
			}
			catch (e:IOException){
				Log.e("response","net work error may be")
				return@launchWhenCreated
			}
			catch (e:HttpException){
				Log.e("response","page is not available")
				return@launchWhenCreated
			}
			if (response.isSuccessful && response.body() != null) {
				val adapter = ApiAdapter()
				adapter.data = response.body()!!
				recyclerView.layoutManager = LinearLayoutManager(this@MainActivity,RecyclerView.HORIZONTAL,false)
				recyclerView.adapter = adapter
				adapter.setOnClickListener(object :ApiAdapter.OnClickListener{
					override fun onClick(position: Int,data:ApiClass) {
						val intent = Intent(this@MainActivity,ItemPage::class.java)
						intent.putExtra("title", data.title)
						intent.putExtra("genre", "nonfiction")
						startActivity(intent)
						Log.e("msg","${data.subgenre}")
						Log.e("msg","${data.title}")
					}

				})
				heading.text = response.body()!![0].subgenre
			}
			else Log.e("msg","false")

		}
	}


	}