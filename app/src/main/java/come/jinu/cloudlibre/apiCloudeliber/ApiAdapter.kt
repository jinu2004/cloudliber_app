package come.jinu.cloudlibre.apiCloudeliber

import android.annotation.SuppressLint
import android.app.Application
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import come.jinu.cloudlibre.R
import come.jinu.cloudlibre.databinding.RecyclerviewOfBooksBinding

class ApiAdapter:RecyclerView.Adapter<ApiAdapter.ApiClassViewHolder>() {

	inner class ApiClassViewHolder(val binding: RecyclerviewOfBooksBinding):RecyclerView.ViewHolder(binding.root)

	private var onClickListener:OnClickListener?=null

	private val diffCallable = object :DiffUtil.ItemCallback<ApiClass>(){
		override fun areItemsTheSame(oldItem: ApiClass, newItem: ApiClass): Boolean {
			return oldItem.title == newItem.title
		}


		override fun areContentsTheSame(oldItem: ApiClass, newItem: ApiClass): Boolean {
			return oldItem == newItem
		}

	}
	private val differ = AsyncListDiffer(this@ApiAdapter,diffCallable)
	var data:List<ApiClass>
		get() = differ.currentList
		set(value){differ.submitList(value)}


	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiClassViewHolder {
		val view = RecyclerviewOfBooksBinding.inflate(LayoutInflater.from(parent.context))
		return ApiClassViewHolder(view)
	}
	@SuppressLint("CheckResult")
	override fun onBindViewHolder(holder: ApiClassViewHolder, position: Int) {
		val datas = data[position]
		holder.binding.apply {
			titleOfBookRecycler.text = datas.title
			price.text = datas.price
			stareRateRecycler.text = datas.rating
		}
		holder.itemView.setOnClickListener {
			if (onClickListener != null) {
				onClickListener!!.onClick(position,datas)
			}
		}
		Glide.with(holder.binding.root.context)
			.load(datas.coverpage)
			.into(holder.binding.coverPageImg)
	}

	override fun getItemCount(): Int {

		return data.size
	}
	fun setOnClickListener(onClickListener: OnClickListener) {
		this.onClickListener = onClickListener
	}

	// onClickListener Interface
	interface OnClickListener {
		fun onClick(position: Int,data:ApiClass)
	}

}