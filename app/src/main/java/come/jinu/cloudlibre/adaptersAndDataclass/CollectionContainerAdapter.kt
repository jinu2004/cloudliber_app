package come.jinu.cloudlibre.adaptersAndDataclass

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.R

class CollectionContainerAdapter(private val collectionContainer:List<CollectionContainer>):RecyclerView.Adapter<CollectionContainerAdapter.CollectionContainerViewHolder>(){
	inner class CollectionContainerViewHolder(itemView:View):RecyclerView.ViewHolder(itemView)
	{
		val title:TextView = itemView.findViewById(R.id.heading_of_collection_container)
		val booksRecyclerView:RecyclerView = itemView.findViewById(R.id.recyclerView_of_Child_items)
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CollectionContainerViewHolder
	{
		val view = LayoutInflater.from(parent.context).inflate(R.layout.collection_container,parent,false)
		return CollectionContainerViewHolder(view)
	}

	override fun onBindViewHolder(holder: CollectionContainerViewHolder, position: Int) {
		val data = collectionContainer[position]
		holder.title.text = data.Tittle
		holder.booksRecyclerView.setHasFixedSize(true)
		holder.booksRecyclerView.layoutManager = LinearLayoutManager(holder.itemView.context,RecyclerView.HORIZONTAL,false)
		holder.booksRecyclerView.adapter = ApiAdapter(data.BookList)
	}

	override fun getItemCount(): Int {
		return collectionContainer.size
	}
}