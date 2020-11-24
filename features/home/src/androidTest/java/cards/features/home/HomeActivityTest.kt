package cards.features.home

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cards.core.test.base.BaseTest
import cards.features.home.di.mockErrorModule
import cards.features.home.di.mockModule
import cards.features.home.view.HomeActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@RunWith(AndroidJUnit4::class)
@LargeTest
class HomeActivityTest: BaseTest() {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), HomeActivity::class.java)
                            .putExtra(HomeActivity.TEST_EXTRA, true)

    private var scenario: ActivityScenario<HomeActivity>? = null

    @After
    fun tearDown() {
        scenario?.close()
        unloadKoinModules(mockModule)
    }

    @Test
    fun testSuccessWithComplete3Itens() {
        loadKoinModules(mockModule)
        ActivityScenario.launch<HomeActivity>(intent)
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
    fun testFailure() {
        loadKoinModules(mockErrorModule)
        ActivityScenario.launch<HomeActivity>(intent)
        home {
            checkLoadingGone()

            checkErrorVisible()
            checkErrorText()
        }
    }

    @Test
    fun testChangeToCardDetails() {
        loadKoinModules(mockModule)
        ActivityScenario.launch<HomeActivity>(intent)
        home {
            val am = addCardDetailsInstrumentationMonitor()
            performClickInCardDetailsButton()
            checkInstrumentationMonitorHitAndRemove(am)
        }
    }

    @Test
    fun testChangeToAccountDetails() {
        loadKoinModules(mockModule)
        ActivityScenario.launch<HomeActivity>(intent)
        home {
            val am = addAccountDetailsInstrumentationMonitor()
            performClickInAccountDetailsButton()
            checkInstrumentationMonitorHitAndRemove(am)
        }
    }

}