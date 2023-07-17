package come.jinu.cloudlibre.adaptersAndDataclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.R
import come.jinu.cloudlibre.apiCloudeliber.ApiClass

class CloudBookAdapter(private val cloudBooksData: List<CloudBooksData>):RecyclerView.Adapter<CloudBookAdapter.ViewHolder>() {

	inner class ViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
		val coverImage: ImageView = itemView.findViewById(R.id.cover_page_img)
		val title: TextView = itemView.findViewById(R.id.title_of_book_recycler)
		val stareRate: TextView = itemView.findViewById(R.id.stare_rate_recycler)
		val price: TextView =itemView.findViewById(R.id.price)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_of_books,parent,false)
		return  ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val data = cloudBooksData[position]
		holder.title.text = data.title
		holder.price.text = data.price.toString()
		holder.coverImage.setImageResource(data.coverPageUri)
		holder.stareRate.text = data.rate.toString()
	}

	override fun getItemCount(): Int {
		return cloudBooksData.size
	}
}