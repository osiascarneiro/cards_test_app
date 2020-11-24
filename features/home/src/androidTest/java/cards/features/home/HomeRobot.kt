package cards.features.home

import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import cards.actions.Actions
import cards.features.home.view.widgets.HomeAccountView
import cards.features.home.view.widgets.HomeCardView
import cards.features.home.view.widgets.HomeHeaderView
import cards.core.test.robot.BaseRobot

fun home(func: HomeRobot.() -> Unit) = HomeRobot().apply { func() }

class HomeRobot: BaseRobot() {

    fun checkLoadingGone() = checkVisibilityWithId(R.id.loadingBar, ViewMatchers.Visibility.GONE)
    fun checkErrorGone() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.GONE)

    fun checkErrorVisible() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.VISIBLE)
    fun checkErrorText() = checkText(R.id.errorText, "Error in getting widgets")

    fun check3ItemsInList() = checkNumberOfItensInList(R.id.widgetList, 3)

    fun checkHomeHeaderViewInSecondPosition() = checkInstaceTypeInPosition(R.id.widgetList, 1, HomeHeaderView::class.java)
    fun checkMockTitleInSecondPosition() = checkTextInPosition(R.id.widgetList, 1, "mock title")

    fun checkHomeCardViewInFirstPosition() = checkInstaceTypeInPosition(R.id.widgetList, 0, HomeCardView::class.java)
    fun checkMockCardTitleInFirstPosition() = checkTextInPosition(R.id.widgetList, 0, "mock card title")
    fun checkMockCardNumberInFirstPosition() = checkTextInPosition(R.id.widgetList, 0, "mock card number")
    fun checkMockCardDetailsInFirstPosition() = checkTextInPosition(R.id.widgetList, 0, "mock card details")

    fun checkHomeAccountViewInThirdPosition() = checkInstaceTypeInPosition(R.id.widgetList, 2, HomeAccountView::class.java)
    fun checkMockAccountTitleInThirdPosition() = checkTextInPosition(R.id.widgetList, 2, "mock account title")
    fun checkSaldoInThirdPosition() = checkTextInPosition(R.id.widgetList, 2, "Saldo")
    fun checkValueInThirdPosition() = checkTextInPosition(R.id.widgetList, 2, "R$ 50,00")
    fun checkMockAccountDetailsInThirdPosition() = checkTextInPosition(R.id.widgetList, 2, "mock account details")

    fun performClickInCardDetailsButton() = getViewWithIdAndTitle(R.id.actionButton, "mock card details").perform(click())
    fun performClickInAccountDetailsButton() = getViewWithIdAndTitle(R.id.actionButton, "mock account details").perform(click())

    fun addCardDetailsInstrumentationMonitor() = addInstrumentationMonitor(Actions.CARD_DETAILS_ACTION)

    fun addAccountDetailsInstrumentationMonitor() = addInstrumentationMonitor(Actions.ACCOUNT_DETAILS_ACTION)

}