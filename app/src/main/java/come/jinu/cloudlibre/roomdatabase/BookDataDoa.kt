package come.jinu.cloudlibre.roomdatabase

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface BookDataDoa {
@Query("SELECT * FROM bookData")
fun getAll():LiveData<List<RoomBookData>>
@Query("SELECT * FROM BookData WHERE category LIKE :category LIMIT 10")
fun getCategory(category:String): LiveData<List<RoomBookData>>
@Insert(onConflict = OnConflictStrategy.IGNORE)
suspend fun addNewBook(bookData: RoomBookData)

}