package come.jinu.cloudlibre.roomdatabase
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "BookData")
class RoomBookData(
 @PrimaryKey(autoGenerate = true) val id:Int? = null,
 val title:String? = null,
 val author:String? = null,
 val publisher:String? = null,
 val about:String? = null,
 val price:Int? = null,
 val coverPageUri:Int? = null,
 val fileUri:String,
 val rate:Double? = null,
 @ColumnInfo("category")val category:String? = null,
)