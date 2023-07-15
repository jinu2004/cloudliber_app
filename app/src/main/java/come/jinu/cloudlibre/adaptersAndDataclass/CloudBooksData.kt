package come.jinu.cloudlibre.adaptersAndDataclass

data class CloudBooksData(
	val title:String,
	val author:String,
	val publisher:String,
	val about:String,
	val price:Int,
	val coverPageUri:Int,
	val fileUri:String,
	val rate: Double,
	val category:String
)
