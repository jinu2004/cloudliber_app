package come.jinu.cloudlibre.roomdatabase

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application):AndroidViewModel(application){
	private val readAll:LiveData<List<RoomBookData>>
	private val repository: BookRepository

	init {
		val bookDataDoa = BookDatabase.getDatabase(application).bookDoa()
		repository = BookRepository(bookDataDoa)
		readAll = repository.getAllData
	}

	fun addNewItem(roomBookData: RoomBookData){
		viewModelScope.launch(Dispatchers.IO) {
			repository.addNewItem(roomBookData)
		}
	}

	fun getCategory(category:String):LiveData<List<RoomBookData>>{
			return repository.getCategory(category)
	}


}