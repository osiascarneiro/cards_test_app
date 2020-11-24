package cards.actions

import android.content.Context
import android.content.Intent

object Actions {

    const val CARD_ID_EXTRA = "CARD_ID_EXTRA"
    const val ACCOUNT_ID_EXTRA = "ACCOUNT_ID_EXTRA"

    const val HOME_ACTION = "com.osias.cards.home.open"
    const val CARD_DETAILS_ACTION = "com.osias.cards.cardDetails.open"
    const val ACCOUNT_DETAILS_ACTION = "com.osias.cards.accountDetails.open"

    fun openHomeIntent(context: Context) = internalIntent(context, HOME_ACTION)
    fun openCardDetails(context: Context, cardId: String) = internalIntent(context, CARD_DETAILS_ACTION).putExtra(CARD_ID_EXTRA, cardId)
    fun openAccountDetails(context: Context, accountId: String) = internalIntent(context, ACCOUNT_DETAILS_ACTION).putExtra(ACCOUNT_ID_EXTRA, accountId)

    private fun internalIntent(context: Context, action: String) = Intent(action).setPackage(context.packageName)
}