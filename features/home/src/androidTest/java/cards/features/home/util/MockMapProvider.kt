package cards.features.home.util

object MockMapProvider {

    fun getHeaderContent(): Map<String,Any> = HashMap<String,Any>().apply {
        put("title", "mock title")
    }

    fun getCardContent(): Map<String,Any> = HashMap<String,Any>().apply {
        put("title", "mock card title")
        put("cardNumber", "mock card number")
        val buttonMap: Map<String,Any> = HashMap<String,Any>().apply {
            put("text", "mock card details")
            val actionMap: Map<String,Any> = HashMap<String,Any>().apply {
                put("identifier", "CARD_SCREEN")
                val contentMap: Map<String,Any> = HashMap<String,Any>().apply {
                    put("cardId", "123")
                }
                put("content", contentMap)
            }
            put("action", actionMap)
        }
        put("button", buttonMap)
    }

    fun getStatementContent(): Map<String,Any> = HashMap<String,Any>().apply {
        put("title", "mock account title")
        val balanceMap: Map<String,Any> = HashMap<String,Any>().apply {
            put("label", "Saldo")
            put("value", "R$ 50,00")
        }
        put("balance", balanceMap)
        val buttonMap: Map<String,Any> = HashMap<String,Any>().apply {
            put("text", "mock account details")
            val actionMap: Map<String,Any> = HashMap<String,Any>().apply {
                put("identifier", "STATEMENT_SCREEN")
                val contentMap: Map<String,Any> = HashMap<String,Any>().apply {
                    put("accountId", "123")
                }
                put("content", contentMap)
            }
            put("action", actionMap)
        }
        put("button", buttonMap)
    }

}