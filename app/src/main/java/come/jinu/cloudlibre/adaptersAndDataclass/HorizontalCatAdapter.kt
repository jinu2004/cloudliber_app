package come.jinu.cloudlibre.adaptersAndDataclass

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import come.jinu.cloudlibre.databinding.HorizontalCategorySelectorItemBinding

class HorizontalCatAdapter(private val cat:List<catogery>):RecyclerView.Adapter<HorizontalCatAdapter.ViewHolder>() {
	inner class ViewHolder(val viewBinding: HorizontalCategorySelectorItemBinding):RecyclerView.ViewHolder(viewBinding.root)
	private var onClickListener: OnClickListener?=null


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
		val view =  HorizontalCategorySelectorItemBinding.inflate(LayoutInflater.from(parent.context))
		return ViewHolder(view)
	}

	@SuppressLint("ClickableViewAccessibility", "NotifyDataSetChanged", "ResourceAsColor")
	override fun onBindViewHolder(holder: ViewHolder, position: Int) {
		val datas = cat[position]
		var currentItem = 0
		holder.viewBinding.text.text = datas.category
		holder.viewBinding.stroke.setOnClickListener {
			if (onClickListener != null) {
				onClickListener!!.onClick(position)
				notifyDataSetChanged()
			}

			currentItem = position
			Log.d("fuck",currentItem.toString())
		}
		if (currentItem == position) holder.viewBinding.stroke.setCardBackgroundColor(0xFF725E)
		else holder.viewBinding.stroke.setCardBackgroundColor(0x2D2C2C)




	}

	override fun getItemCount(): Int {
		return cat.size
	}
	fun setOnClickListener(onClickListener: OnClickListener) {
		this.onClickListener = onClickListener
	}

	// onClickListener Interface
	interface OnClickListener {
		fun onClick(position: Int)
	}
}