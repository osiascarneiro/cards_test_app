package cards.features.home

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cards.features.home.view.HomeActivity
import org.junit.After
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

    @After
    fun tearDown() {
        activityRule.scenario.close()
    }

    @Test
    fun testSuccessWithComplete3Itens() {
        home {
            //Loading and error gone
            checkLoadingGone()
            checkErrorGone()
            //3 itens in list
            check3ItemsInList()
            //Header view
            checkHomeHeaderViewInSecondPosition()
            checkMockTitleInSecondPosition()
            //Card view
            checkHomeCardViewInFirstPosition()
            checkMockCardTitleInFirstPosition()
            checkMockCardNumberInFirstPosition()
            checkMockCardDetailsInFirstPosition()
            //Account view
            checkHomeAccountViewInThirdPosition()
            checkMockAccountTitleInThirdPosition()
            checkSaldoInThirdPosition()
            checkValueInThirdPosition()
            checkMockAccountDetailsInThirdPosition()
        }
    }

    @Test
    fun testChangeToCardDetails() {
        home {
            val am = addCardDetailsInstrumentationMonitor()
            performClickInCardDetailsButton()
            checkInstrumentationMonitorHitAndRemove(am)
        }
    }

    @Test
    fun testChangeToAccountDetails() {
        home {
            val am = addAccountDetailsInstrumentationMonitor()
            performClickInAccountDetailsButton()
            checkInstrumentationMonitorHitAndRemove(am)
        }
    }

}