package cards.features.home.model

data class ActionButton(val title: String, val action: HomeActionType?) {

    companion object {
        private const val TEXT_KEY = "text"
        private const val ACTION_KEY = "action"

        fun fromMap(map: Map<String,Any>): ActionButton {
            if(map.isEmpty()) return ActionButton("", HomeActionType.CardScreen(""))
            if(!map.containsKey(TEXT_KEY)) throw InstantiationError("Class must have a text")
            if(!map.containsKey(ACTION_KEY)) throw InstantiationError("Class must have a action")
            val title = map[TEXT_KEY] as? String ?: ""
            val actionMap = map[ACTION_KEY] as? Map<String,Any> ?: emptyMap()
            val action = HomeActionType.fromMap(actionMap)
            return ActionButton(title, action)
        }
    }

}