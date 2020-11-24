package cards.features.accountdetails

import androidx.test.espresso.matcher.ViewMatchers
import cards.core.test.util.BaseRobot

fun account(func: AccountRobot.() -> Unit) = AccountRobot().apply { func() }

class AccountRobot: BaseRobot() {

    fun checkLoadingGone() = checkVisibilityWithId(R.id.loadingBar, ViewMatchers.Visibility.GONE)
    fun checkErrorGone() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.GONE)

    fun checkSixItemsInTransactionList() = checkNumberOfItensInList(R.id.transactions, 6)

    fun checkLabelBalanceTitleText() = checkText(R.id.labelBalance, "Saldo disponível")
    fun checkLabelBalanceValueText() = checkText(R.id.valueBalance, "R$ 5.000,00")

    fun validateValuesInTransactionList() {
        for(i in 0..5) {
            checkTextInPosition(R.id.transactions, i, "Compra nº $i")
            checkTextInPosition(R.id.transactions, i, "R$ $i,00")
            checkTextInPosition(R.id.transactions, i, "Compra com cartão $i")
        }

    }

}