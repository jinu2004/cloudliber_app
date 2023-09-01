@file:Suppress("NAME_SHADOWING")

package come.jinu.cloudlibre

import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.AnimationTypes
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import come.jinu.cloudlibre.adaptersAndDataclass.ApiAdapter
import come.jinu.cloudlibre.adaptersAndDataclass.HorizontalCatAdapter
import come.jinu.cloudlibre.adaptersAndDataclass.catogery
import come.jinu.cloudlibre.apiCloudeliber.ApiClass
import come.jinu.cloudlibre.apiCloudeliber.ApiviewModel
import come.jinu.cloudlibre.databinding.ActivityMainBinding
import come.jinu.cloudlibre.roomdatabase.RoomViewModel
import java.util.*


class MainActivity : AppCompatActivity() {
	private lateinit var auth: FirebaseAuth
	private lateinit var roomViewModel: RoomViewModel
	private lateinit var binding: ActivityMainBinding
	private lateinit var apiViewModel: ApiviewModel
	override fun onCreate(savedInstanceState: Bundle?) {
		installSplashScreen()
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)
		binding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		auth = Firebase.auth
		window.statusBarColor = ContextCompat.getColor(this, R.color.black)
		window.navigationBarColor = ContextCompat.getColor(this, R.color.black)

		roomViewModel = ViewModelProvider(this)[RoomViewModel::class.java]
		apiViewModel = ViewModelProvider(this)[ApiviewModel::class.java]

		val imageList = ArrayList<SlideModel>()
		imageList.add(
			SlideModel(
				"https://cdn.pixabay.com/photo/2016/02/16/21/07/books-1204029_1280.jpg", ""
			)
		)
		imageList.add(
			SlideModel(
				"https://cdn.pixabay.com/photo/2016/09/10/17/18/book-1659717_640.jpg", ""
			)
		)
		imageList.add(
			SlideModel(
				"https://cdn.pixabay.com/photo/2019/05/14/21/50/storytelling-4203628_640.jpg", ""
			)
		)
		imageList.add(
			SlideModel(
				"https://cdn.pixabay.com/photo/2016/08/24/16/20/books-1617327_640.jpg", ""
			)
		)
		binding.ads.setImageList(imageList, scaleType = ScaleTypes.FIT)
		binding.ads.setSlideAnimation(AnimationTypes.DEPTH_SLIDE)//depth slide // GAtE//
		binding.ads.startSliding(3000)
		binding.username.text = auth.currentUser?.displayName

		val category = ArrayList<catogery>()
		category.add(catogery("Mystery"))
		category.add(catogery("Adventure"))
		category.add(catogery("Thriller"))
		category.add(catogery("Action"))
		val adapter = HorizontalCatAdapter(category)
		binding.categoryRecycler.layoutManager =
			LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
		binding.categoryRecycler.adapter = adapter
		adapter.setOnClickListener(object : HorizontalCatAdapter.OnClickListener {
			@RequiresApi(Build.VERSION_CODES.Q)
			override fun onClick(position: Int) {
				val intent = Intent(this@MainActivity, ExpandData::class.java)
				intent.putExtra("category",category[position].category)
				startActivity(intent)
			}
		})


		apiViewModel.getBookList().observe(this@MainActivity) { datas ->
			val adapter = ApiAdapter(datas)
			binding.card1Recycler.layoutManager =
				LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
			binding.card1Recycler.adapter = adapter
			adapter.setOnClickListener(object : ApiAdapter.OnClickListener {
				override fun onClick(position: Int, data: ApiClass) {
					val intent = Intent(this@MainActivity, ItemPage::class.java)
					intent.putExtra("title", data.title)
					intent.putExtra("genre", "fiction")
					startActivity(intent)
					Log.e("msg", data.subgenre)
					Log.e("msg", data.title)
				}

			})

		}

		apiViewModel.getBookListN().observe(this@MainActivity) { datas ->
			val adapter = ApiAdapter(datas)
			binding.card2Recycler.layoutManager =
				LinearLayoutManager(this@MainActivity, RecyclerView.HORIZONTAL, false)
			binding.card2Recycler.adapter = adapter
			adapter.setOnClickListener(object : ApiAdapter.OnClickListener {
				override fun onClick(position: Int, data: ApiClass) {
					val intent = Intent(this@MainActivity, ItemPage::class.java)
					intent.putExtra("title", data.title)
					intent.putExtra("genre", "nonfiction")
					startActivity(intent)
					Log.e("msg", data.subgenre)
					Log.e("msg", data.title)
				}

			})

		}


		if (auth.currentUser?.photoUrl == null) {
			binding.profilePicName.text = auth.currentUser?.displayName?.first().toString()
			val rnd = Random()
			val color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256))
			binding.profilePic.setBackgroundColor(color)
		}




		binding.textView4.setOnClickListener {
			startActivity(Intent(this,FullCategories::class.java))
		}


	}





	//////////////////////  onCreate function ends here  ///////////////////


	public override fun onStart() {
		super.onStart()
		val currentUser = auth.currentUser
		if (currentUser == null) {
			startActivity(Intent(this, SigninActivity::class.java))
		}
	}

	@Suppress("DEPRECATION")
	@Deprecated("Deprecated in Java")
	override fun onBackPressed() {
		super.onBackPressed()
		finishAffinity()
	}

}