package cards.features.home.model

data class HeaderContent(val title: String) {

    companion object {
        fun fromMap(map: Map<String,Any>): HeaderContent {
            if(!map.containsKey("title")) throw InstantiationError("Class must have a title")
            val title = map["title"] as? String
            return HeaderContent(title ?: "")
        }
    }

}