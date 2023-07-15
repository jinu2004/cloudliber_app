package come.jinu.cloudlibre.roomdatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.R

class RoomBookRecyclerAdapter(private var bookData:List<RoomBookData>):RecyclerView.Adapter<RoomBookRecyclerAdapter.BookRecyclerViewHolder>() {

	private lateinit var clickListener: OnItemClickListener

	inner class BookRecyclerViewHolder(itemView: View,listener:OnItemClickListener):RecyclerView.ViewHolder(itemView)
	{
		val coverImage:ImageView = itemView.findViewById(R.id.cover_page_img)
		val title:TextView = itemView.findViewById(R.id.title_of_book_recycler)
		val stareRate:TextView = itemView.findViewById(R.id.stare_rate_recycler)
		val price:TextView =itemView.findViewById(R.id.price)

		init {
			itemView.setOnClickListener {
				listener.onItemClick(adapterPosition)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookRecyclerViewHolder {
		val view = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_of_books,parent,false)
		return BookRecyclerViewHolder(view,clickListener)
	}

	override fun onBindViewHolder(holder: BookRecyclerViewHolder, position: Int) {
		val data = bookData[position]
		holder.title.text = data.title
		holder.price.text = data.price.toString()
		holder.coverImage.setImageResource(data.coverPageUri!!)
		holder.stareRate.text = data.rate.toString()

	}

	override fun getItemCount(): Int {
		return bookData.size
	}
	interface OnItemClickListener{
		fun onItemClick(position: Int)
	}
	fun setOnItemClickListener(listener: OnItemClickListener){
		clickListener = listener
	}
}