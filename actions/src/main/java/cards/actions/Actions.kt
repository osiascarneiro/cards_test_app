package cards.actions

import android.content.Context
import android.content.Intent

object Actions {

    const val CARD_ID_EXTRA = "CARD_ID_EXTRA"
    const val ACCOUNT_ID_EXTRA = "ACCOUNT_ID_EXTRA"

    fun openHomeIntent(context: Context) = internalIntent(context, "com.osias.cards.home.open")
    fun openCardDetails(context: Context, cardId: String) = internalIntent(context, "com.osias.cards.cardDetails.open").putExtra(CARD_ID_EXTRA, cardId)
    fun openAccountDetails(context: Context, accountId: String) = internalIntent(context, "com.osias.cards.accountDetails.open").putExtra(ACCOUNT_ID_EXTRA, accountId)

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}