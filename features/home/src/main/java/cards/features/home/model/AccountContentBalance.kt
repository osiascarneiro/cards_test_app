package cards.features.home.model

data class AccountContentBalance(val label: String, val value: String) {

    companion object {
        fun fromMap(map: Map<String,Any>): AccountContentBalance {
            if(!map.containsKey("label")) throw InstantiationError("Class must have a label")
            if(!map.containsKey("value")) throw InstantiationError("Class must have a value")
            val label = map["label"] as? String ?: ""
            val value = map["value"] as? String ?: ""
            return AccountContentBalance(label, value)
        }
    }

}