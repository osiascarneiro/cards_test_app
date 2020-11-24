package cards.features.home.model

data class AccountContent(val title: String, val balance: AccountContentBalance, val button: ActionButton) {

    companion object {
        private const val TITLE_KEY = "title"
        private const val BALANCE_KEY = "balance"
        private const val BUTTON_KEY = "button"

        fun fromMap(map: Map<String,Any>): AccountContent {
            if(!map.containsKey(TITLE_KEY)) throw InstantiationError("Class must have a title")
            if(!map.containsKey(BALANCE_KEY)) throw InstantiationError("Class must have a balance")
            if(!map.containsKey(BUTTON_KEY)) throw InstantiationError("Class must have a button")
            val title = map[TITLE_KEY] as? String ?: ""
            val balanceMap = map[BALANCE_KEY] as? Map<String,Any> ?: emptyMap()
            val balance = AccountContentBalance.fromMap(balanceMap)
            val buttonMap = map[BUTTON_KEY] as? Map<String,Any> ?: emptyMap()
            val button = ActionButton.fromMap(buttonMap)
            return AccountContent(title, balance, button)
        }
    }

}