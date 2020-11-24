package cards.features.home

import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
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
        Espresso.onView(ViewMatchers.withId(id))
            .check(
                ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
            )

    private fun checkNumberOfItensInList(count: Int): ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.widgetList))
        .check(ViewAssertions.matches(CustomMatchers.withItemCount(count)))

    private fun checkInstaceTypeInPosition(position: Int, classType: Class<*>): ViewInteraction =
        Espresso.onView(ViewMatchers.withId(R.id.widgetList))
            .check(
                ViewAssertions.matches(
                    CustomMatchers.atPosition(position, AllOf.allOf(Matchers.instanceOf(classType)))
                )
            )

    private fun checkTextInPosition(position: Int, text: String): ViewInteraction = Espresso.onView(ViewMatchers.withId(R.id.widgetList))
        .check(
            ViewAssertions.matches(
                CustomMatchers.atPosition(
                    position,
                    ViewMatchers.withText(text)
                )
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
}