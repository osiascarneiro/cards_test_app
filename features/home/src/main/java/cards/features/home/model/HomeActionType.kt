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
        fun fromMap(action: Map<String,Any>): HomeActionType? {
            if(action.isEmpty()) return null
            if(!action.containsKey("identifier")) throw InstantiationError("Class must have a identifier")
            if(!action.containsKey("content")) throw InstantiationError("Class must have a content")
            val content = action["content"] as? Map<String,Any> ?: emptyMap()
            return when(action["identifier"] as? String) {
                "CARD_SCREEN" -> {
                    CardScreen(content["cardId"] as? String ?: "")
                }
                "STATEMENT_SCREEN" -> {
                    StatementScreen(content["accountId"] as? String ?: "")
                }
                else -> {
                    null
                }
            }
        }
    }
}