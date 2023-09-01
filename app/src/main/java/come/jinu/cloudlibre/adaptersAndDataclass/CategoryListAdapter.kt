package come.jinu.cloudlibre.adaptersAndDataclass

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.databinding.CategorySelectionRecyclerItemBinding


class CategoryListAdapter(val data:List<CategoryList>):
	RecyclerView.Adapter<CategoryListAdapter.ViewHolder>() {
	inner class ViewHolder(val binding:CategorySelectionRecyclerItemBinding):RecyclerView.ViewHolder(binding.root)
	private var onClickListener:OnClickListener?=null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view = CategorySelectionRecyclerItemBinding.inflate(LayoutInflater.from(parent.context))
		return ViewHolder(view)
	}

	override fun getItemCount(): Int {
		return  data.size
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val dataItem = data[position]
		holder.binding.text.text = dataItem.title
		holder.binding.icon.setImageResource(dataItem.image)
		holder.itemView.setOnClickListener {
			if (onClickListener != null) {
				onClickListener!!.onClick(position)
			}
		}
	}
	fun setOnclickListener(onClickListener:OnClickListener){
		this.onClickListener = onClickListener
	}
	interface OnClickListener {
		fun onClick(position: Int)

	}

}