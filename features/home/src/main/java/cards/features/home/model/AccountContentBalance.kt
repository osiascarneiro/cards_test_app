package cards.features.home.model

data class AccountContentBalance(val label: String, val value: String) {

    companion object {
        private const val LABEL_KEY = "label"
        private const val VALUE_KEY = "value"

        fun fromMap(map: Map<String,Any>): AccountContentBalance {
            if(!map.containsKey(LABEL_KEY)) throw InstantiationError("Class must have a label")
            if(!map.containsKey(VALUE_KEY)) throw InstantiationError("Class must have a value")
            val label = map[LABEL_KEY] as? String ?: ""
            val value = map[VALUE_KEY] as? String ?: ""
            return AccountContentBalance(label, value)
        }
    }

}