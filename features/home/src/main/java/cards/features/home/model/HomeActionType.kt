package cards.features.home.model

import android.content.Context
import android.content.Intent
import cards.actions.Actions

sealed class HomeActionType {

    abstract fun intent(context: Context): Intent

    data class CardScreen(val cardId: String): HomeActionType() {
        override fun intent(context: Context): Intent = Actions.openCardDetails(context, cardId)
    }
    data class StatementScreen(val accountId: String): HomeActionType() {
        override fun intent(context: Context): Intent = Actions.openAccountDetails(context, accountId)
    }

    companion object {
        private const val IDENTIFIER_KEY = "identifier"
        private const val CONTENT_KEY = "content"

        private const val CARD_SCREEN = "CARD_SCREEN"
        private const val CARD_ID_KEY = "cardId"

        private const val STATEMENT_SCREEN = "STATEMENT_SCREEN"
        private const val ACCOUNT_ID_KEY = "accountId"

        fun fromMap(action: Map<String,Any>): HomeActionType? {
            if(action.isEmpty()) return null
            if(!action.containsKey(IDENTIFIER_KEY)) throw InstantiationError("Class must have a identifier")
            if(!action.containsKey(CONTENT_KEY)) throw InstantiationError("Class must have a content")
            val content = action[CONTENT_KEY] as? Map<String,Any> ?: emptyMap()
            return when(action[IDENTIFIER_KEY] as? String) {
                CARD_SCREEN -> {
                    CardScreen(content[CARD_ID_KEY] as? String ?: "")
                }
                STATEMENT_SCREEN -> {
                    StatementScreen(content[ACCOUNT_ID_KEY] as? String ?: "")
                }
                else -> {
                    null
                }
            }
        }
    }
}