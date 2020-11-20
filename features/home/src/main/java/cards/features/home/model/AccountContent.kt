package cards.features.home.model

data class AccountContent(val title: String, val balance: AccountContentBalance, val button: ActionButton) {

    companion object {
        fun fromMap(map: Map<String,Any>): AccountContent {
            if(!map.containsKey("title")) throw InstantiationError("Class must have a title")
            if(!map.containsKey("balance")) throw InstantiationError("Class must have a balance")
            if(!map.containsKey("button")) throw InstantiationError("Class must have a button")
            val title = map["title"] as? String ?: ""
            val balanceMap = map["balance"] as? Map<String,Any> ?: emptyMap()
            val balance = AccountContentBalance.fromMap(balanceMap)
            val buttonMap = map["button"] as? Map<String,Any> ?: emptyMap()
            val button = ActionButton.fromMap(buttonMap)
            return AccountContent(title, balance, button)
        }
    }

}