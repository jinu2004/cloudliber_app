package come.jinu.cloudlibre.adaptersAndDataclass

import android.annotation.SuppressLint
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import come.jinu.cloudlibre.apiCloudeliber.ApiClass
import come.jinu.cloudlibre.databinding.RecyclerviewOfBooksBinding

class ApiAdapter(private val data:List<ApiClass>):RecyclerView.Adapter<ApiAdapter.ApiClassViewHolder>() {

	inner class ApiClassViewHolder(val binding: RecyclerviewOfBooksBinding):RecyclerView.ViewHolder(binding.root)

	private var onClickListener: OnClickListener?=null

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ApiClassViewHolder {
		val view = RecyclerviewOfBooksBinding.inflate(LayoutInflater.from(parent.context))
		return ApiClassViewHolder(view)
	}
	@SuppressLint("CheckResult")
	override fun onBindViewHolder(holder: ApiClassViewHolder, position: Int) {
		val datas = data[position]
		holder.binding.apply {
			titleOfBookRecycler.maxLines = 20
			titleOfBookRecycler.ellipsize = TextUtils.TruncateAt.END
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
		fun onClick(position: Int,data: ApiClass)
	}

}