package cards.features.home

import android.app.Instrumentation
import android.content.IntentFilter
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import cards.actions.Actions
import cards.features.home.util.CustomMatchers
import cards.features.home.view.widgets.HomeAccountView
import cards.features.home.view.widgets.HomeCardView
import cards.features.home.view.widgets.HomeHeaderView
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

fun home(func: HomeRobot.() -> Unit) = HomeRobot().apply { func() }

class HomeRobot {
    //Reutilizable private methods
    private fun checkVisibilityWithId(id: Int, visibility: ViewMatchers.Visibility): ViewInteraction =
        getViewWithId(id)
            .check(
                ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
            )

    private fun checkNumberOfItensInList(count: Int): ViewInteraction = getViewWithId(R.id.widgetList)
        .check(ViewAssertions.matches(CustomMatchers.withItemCount(count)))

    private fun checkInstaceTypeInPosition(position: Int, classType: Class<*>): ViewInteraction =
        getViewWithId(R.id.widgetList)
            .check(
                ViewAssertions.matches(
                    CustomMatchers.atPosition(position, AllOf.allOf(Matchers.instanceOf(classType)))
                )
            )

    private fun checkTextInPosition(position: Int, text: String): ViewInteraction = getViewWithId(R.id.widgetList)
        .check(
            ViewAssertions.matches(
                CustomMatchers.atPosition(
                    position,
                    ViewMatchers.withText(text)
                )
            )
        )

    private fun getViewWithId(id: Int) = Espresso.onView(ViewMatchers.withId(id))
    private fun getViewWithIdAndTitle(id: Int, title: String) = Espresso.onView(
        AllOf.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.withText(title)
        )
    )

    fun checkLoadingGone() = checkVisibilityWithId(R.id.loadingBar, ViewMatchers.Visibility.GONE)
    fun checkErrorGone() = checkVisibilityWithId(R.id.errorText, ViewMatchers.Visibility.GONE)

    fun check3ItemsInList() = checkNumberOfItensInList(3)

    fun checkHomeHeaderViewInSecondPosition() = checkInstaceTypeInPosition(1, HomeHeaderView::class.java)
    fun checkMockTitleInSecondPosition() = checkTextInPosition(1, "mock title")

    fun checkHomeCardViewInFirstPosition() = checkInstaceTypeInPosition(0, HomeCardView::class.java)
    fun checkMockCardTitleInFirstPosition() = checkTextInPosition(1, "mock card title")
    fun checkMockCardNumberInFirstPosition() = checkTextInPosition(1, "mock card number")
    fun checkMockCardDetailsInFirstPosition() = checkTextInPosition(1, "mock card details")

    fun checkHomeAccountViewInThirdPosition() = checkInstaceTypeInPosition(2, HomeAccountView::class.java)
    fun checkMockAccountTitleInThirdPosition() = checkTextInPosition(1, "mock account title")
    fun checkSaldoInThirdPosition() = checkTextInPosition(2, "Saldo")
    fun checkValueInThirdPosition() = checkTextInPosition(2, "R$ 50,00")
    fun checkMockAccountDetailsInThirdPosition() = checkTextInPosition(2, "mock account details")

    fun performClickInCardDetailsButton() = getViewWithIdAndTitle(R.id.actionButton, "mock card details").perform(click())
    fun performClickInAccountDetailsButton() = getViewWithIdAndTitle(R.id.actionButton, "mock account details").perform(click())

    fun addCardDetailsInstrumentationMonitor(): Instrumentation.ActivityMonitor {
        val filter = IntentFilter(Actions.CARD_DETAILS_ACTION)
        val am = Instrumentation.ActivityMonitor(filter, null, true)
        InstrumentationRegistry.getInstrumentation().addMonitor(am)
        return am
    }

    fun addAccountDetailsInstrumentationMonitor(): Instrumentation.ActivityMonitor {
        val filter = IntentFilter(Actions.ACCOUNT_DETAILS_ACTION)
        val am = Instrumentation.ActivityMonitor(filter, null, true)
        InstrumentationRegistry.getInstrumentation().addMonitor(am)
        return am
    }

    fun checkInstrumentationMonitorHitAndRemove(am: Instrumentation.ActivityMonitor) {
        assert(InstrumentationRegistry.getInstrumentation().checkMonitorHit(am, 1))
        InstrumentationRegistry.getInstrumentation().removeMonitor(am)
    }
}