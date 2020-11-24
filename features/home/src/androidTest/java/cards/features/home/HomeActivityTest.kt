package cards.features.home

import android.app.Instrumentation
import android.content.Intent
import android.content.IntentFilter
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import cards.actions.Actions
import cards.features.home.util.CustomMatchers.atPosition
import cards.features.home.util.CustomMatchers.withItemCount
import cards.features.home.view.HomeActivity
import cards.features.home.view.widgets.HomeAccountView
import cards.features.home.view.widgets.HomeCardView
import cards.features.home.view.widgets.HomeHeaderView
import org.hamcrest.Matchers.instanceOf
import org.hamcrest.core.AllOf.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)
                            .putExtra(HomeActivity.TEST_EXTRA, true)

    @get:Rule
    val activityRule = ActivityScenarioRule<HomeActivity>(intent)

    @Before
    fun setup() {

    }

    @After
    fun tearDown() {
        activityRule.scenario.close()
    }

    @Test
    fun testSuccessWithComplete3Itens() {
        //Loading and error gone
        onView(withId(R.id.loadingBar)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        onView(withId(R.id.errorText)).check(matches(withEffectiveVisibility(Visibility.GONE)))
        //3 itens in list
        onView(withId(R.id.widgetList)).check(matches(withItemCount(3)))
        //Header view
        onView(withId(R.id.widgetList)).check(matches(atPosition(1, allOf(instanceOf(HomeHeaderView::class.java)))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(1, withText("mock title"))))
        //Card view
        onView(withId(R.id.widgetList)).check(matches(atPosition(0, allOf(instanceOf(HomeCardView::class.java)))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(0, withText("mock card title"))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(0, withText("mock card number"))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(0, withText("mock card details"))))
        //Account view
        onView(withId(R.id.widgetList)).check(matches(atPosition(2, allOf(instanceOf(HomeAccountView::class.java)))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(2, withText("mock account title"))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(2, withText("Saldo"))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(2, withText("R$ 50,00"))))
        onView(withId(R.id.widgetList)).check(matches(atPosition(2, withText("mock account details"))))
    }

    @Test
    fun testChangeToCardDetails() {
        val filter = IntentFilter(Actions.CARD_DETAILS_ACTION)
        val am = Instrumentation.ActivityMonitor(filter, null, true)
        InstrumentationRegistry.getInstrumentation().addMonitor(am)
        onView(allOf(withId(R.id.actionButton), withText("mock card details"))).perform(click())
        assert(InstrumentationRegistry.getInstrumentation().checkMonitorHit(am, 1))
        InstrumentationRegistry.getInstrumentation().removeMonitor(am)
    }

    @Test
    fun testChangeToAccountDetails() {
        val filter = IntentFilter(Actions.ACCOUNT_DETAILS_ACTION)
        val am = Instrumentation.ActivityMonitor(filter, null, true)
        InstrumentationRegistry.getInstrumentation().addMonitor(am)
        onView(allOf(withId(R.id.actionButton), withText("mock account details"))).perform(click())
        assert(InstrumentationRegistry.getInstrumentation().checkMonitorHit(am, 1))
        InstrumentationRegistry.getInstrumentation().removeMonitor(am)
    }

}