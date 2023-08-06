package come.jinu.cloudlibre.adaptersAndDataclass

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.databinding.CatRecyclerViewBinding

class HorizontalCatAdapter(private val cat:List<catogery>):RecyclerView.Adapter<HorizontalCatAdapter.ViewHolder>() {
	inner class ViewHolder(val viewBinding: CatRecyclerViewBinding):RecyclerView.ViewHolder(viewBinding.root)
	private var onClickListener: HorizontalCatAdapter.OnClickListener?=null


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view =  CatRecyclerViewBinding.inflate(LayoutInflater.from(parent.context))
		return ViewHolder(view)
	}

	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val datas = cat[position]
		if (position == 0){
			holder.viewBinding.stroke.setCardBackgroundColor(Color.parseColor("#FF725E"))
		}
		holder.viewBinding.title.text = datas.category
		holder.itemView.setOnClickListener {
			if (onClickListener != null) {
				onClickListener!!.onClick(position,datas)
			}
		}
	}

	override fun getItemCount(): Int {
		return cat.size
	}
	fun setOnClickListener(onClickListener: OnClickListener) {
		this.onClickListener = onClickListener
	}

	// onClickListener Interface
	interface OnClickListener {
		fun onClick(position: Int,data: catogery)
	}
}