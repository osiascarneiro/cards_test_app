package cards.features.home.model

data class HomeAction(val identifier: String, val content: Map<String,Any>) {
    companion object {
        fun fromMap(map: Map<String,Any>): HomeAction {
            if(map.isEmpty()) return HomeAction("", emptyMap())
            if(!map.containsKey("identifier")) throw InstantiationError("Class must have a identifier")
            if(!map.containsKey("content")) throw InstantiationError("Class must have a content")
            val title = map["identifier"] as? String ?: ""
            val content = map["content"] as? Map<String,Any> ?: emptyMap()
            return HomeAction(title, content)
        }
    }
}