package cards.features.home.model

data class ActionButton(val title: String, val action: HomeActionType?) {

    companion object {
        fun fromMap(map: Map<String,Any>): ActionButton {
            if(map.isEmpty()) return ActionButton("", HomeActionType.CardScreen(""))
            if(!map.containsKey("text")) throw InstantiationError("Class must have a text")
            if(!map.containsKey("action")) throw InstantiationError("Class must have a action")
            val title = map["text"] as? String ?: ""
            val actionMap = map["action"] as? Map<String,Any> ?: emptyMap()
            val action = HomeActionType.fromMap(actionMap)
            return ActionButton(title, action)
        }
    }

}