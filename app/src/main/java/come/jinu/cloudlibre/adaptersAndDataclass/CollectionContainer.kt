package come.jinu.cloudlibre.adaptersAndDataclass

import come.jinu.cloudlibre.adaptersAndDataclass.CloudBooksData
import come.jinu.cloudlibre.apiCloudeliber.ApiClass

data class CollectionContainer(val Tittle:String,val BookList:List<ApiClass>)
