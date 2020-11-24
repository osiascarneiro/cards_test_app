package cards.features.home.model

data class HeaderContent(val title: String) {

    companion object {
        private const val TITLE_KEY = "title"

        fun fromMap(map: Map<String,Any>): HeaderContent {
            if(!map.containsKey(TITLE_KEY)) throw InstantiationError("Class must have a title")
            val title = map[TITLE_KEY] as? String
            return HeaderContent(title ?: "")
        }
    }

}