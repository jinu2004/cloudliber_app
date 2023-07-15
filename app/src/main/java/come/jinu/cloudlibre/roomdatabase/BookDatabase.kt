package come.jinu.cloudlibre.roomdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [RoomBookData::class], version = 1, exportSchema = false)
abstract class BookDatabase: RoomDatabase() {
	abstract fun bookDoa(): BookDataDoa

	companion object {
		@Volatile
		private var INSTANCE: BookDatabase? = null

		fun getDatabase(context: Context): BookDatabase {
			val tempInstance = INSTANCE
			if (tempInstance != null) return tempInstance

			synchronized(this) {
				val instance = Room.databaseBuilder(
					context.applicationContext,
					BookDatabase::class.java,
					"BookData"
				).build()

				INSTANCE = instance
				return instance
			}
		}
	}
}