package cards.features.home.model

data class CardContent(val title: String, val cardNumber: String, val button: CardContentButton) {

    companion object {

        fun fromMap(map: Map<String,Any>): CardContent {
            if(!map.containsKey("title")) throw InstantiationError("Class must have a text")
            if(!map.containsKey("cardNumber")) throw InstantiationError("Class must have a action")
            if(!map.containsKey("button")) throw InstantiationError("Class must have a action")
            val title = map["title"] as? String ?: ""
            val cardNumber = map["cardNumber"] as? String ?: ""
            val buttonMap = map["button"] as? Map<String,Any> ?: emptyMap()
            val button = CardContentButton.fromMap(buttonMap)
            return CardContent(title, cardNumber, button)
        }

    }

}