package come.jinu.cloudlibre.apiCloudeliber

import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.util.*
import kotlin.concurrent.scheduleAtFixedRate

class ApiviewModel:ViewModel() {

	private val api = ApiInstance.api
	
	fun getBookList():LiveData<List<ApiClass>>{
		val _booksList = MutableLiveData<List<ApiClass>>()
		Timer().scheduleAtFixedRate(0,30000) {
			viewModelScope.launch {
				try {
					val response = api.getFictionData()
					if (response.isSuccessful)
						_booksList.value = response.body()
				} catch (e: Exception) {
					Log.e("errorFromApi", e.stackTrace.toString())
					return@launch
				} catch (e: HttpException) {
					Log.e("errorFromApi", e.stackTrace.toString())
					return@launch
				}

			}
		}
		return _booksList
	}
	fun getFBList(title:String,genre:String):LiveData<FullDetailFromApi>{
		val _booksList = MutableLiveData<FullDetailFromApi>()
		viewModelScope.launch {
			try {
				val response = api.getFullDetail(title,genre)
				if (response.isSuccessful)
					_booksList.value = response.body()
			}
			catch (e: Exception) {
				Log.e("errorFromApi", e.stackTrace.toString())
				return@launch
			}
			catch (e:HttpException){
				Log.e("errorFromApi", e.stackTrace.toString())
				return@launch
			}

		}
		return _booksList
	}

}