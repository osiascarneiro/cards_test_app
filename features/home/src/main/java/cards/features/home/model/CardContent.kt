package cards.features.home.model

data class CardContent(val title: String, val cardNumber: String, val button: ActionButton) {

    companion object {
        private const val TITLE_KEY = "title"
        private const val CARD_NUMBER_KEY = "cardNumber"
        private const val BUTTON_KEY = "button"

        fun fromMap(map: Map<String,Any>): CardContent {
            if(!map.containsKey(TITLE_KEY)) throw InstantiationError("Class must have a text")
            if(!map.containsKey(CARD_NUMBER_KEY)) throw InstantiationError("Class must have a cardNumber")
            if(!map.containsKey(BUTTON_KEY)) throw InstantiationError("Class must have a action")
            val title = map[TITLE_KEY] as? String ?: ""
            val cardNumber = map[CARD_NUMBER_KEY] as? String ?: ""
            val buttonMap = map[BUTTON_KEY] as? Map<String,Any> ?: emptyMap()
            val button = ActionButton.fromMap(buttonMap)
            return CardContent(title, cardNumber, button)
        }

    }

}