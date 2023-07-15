package come.jinu.cloudlibre.roomdatabase

import androidx.lifecycle.LiveData

class BookRepository(private val bookDataDoa: BookDataDoa) {

	val getAllData:LiveData<List<RoomBookData>> = bookDataDoa.getAll()

	 fun getCategory(category: String): LiveData<List<RoomBookData>> {
		return bookDataDoa.getCategory(category)
	}

	suspend fun addNewItem(roomBookData: RoomBookData){
		bookDataDoa.addNewBook(roomBookData)
	}

}