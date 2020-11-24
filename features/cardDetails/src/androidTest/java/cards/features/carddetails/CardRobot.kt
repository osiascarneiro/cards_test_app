package cards.features.carddetails

import androidx.test.espresso.matcher.ViewMatchers
import cards.core.test.util.BaseRobot

fun cardRobot(func: CardRobot.() -> Unit) = CardRobot().apply { func() }

class CardRobot: BaseRobot() {

    fun checkLoadingGone() = checkVisibilityWithId(R.id.loadingBar, ViewMatchers.Visibility.GONE)
    fun checkErrorGone() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.GONE)

    fun checkErrorVisible() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.VISIBLE)

    fun checkErrorText() = checkText(R.id.errorText, "Error getting card details")

    fun checkCardNumber() = checkText(R.id.cardNumber, "1234 5678 9012 3456")
    fun checkCardName() = checkText(R.id.cardName, "Fulano de tal")
    fun checkCardExpiration() = checkText(R.id.cardExpiration, "Expiration 12/99")
    fun checkAvailableLimit() = checkText(R.id.availableLimit, "Limite disponível: R$ 1.000.000,00")
    fun checkTotalLimit() = checkText(R.id.totalLimit, "Limite total: R$ 5.000.000,00")

}