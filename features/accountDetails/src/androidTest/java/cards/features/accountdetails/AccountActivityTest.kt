package cards.features.accountdetails

import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cards.actions.Actions
import cards.core.test.base.BaseTest
import cards.features.accountdetails.di.errorMockModule
import cards.features.accountdetails.di.mockModule
import cards.features.accountdetails.view.AccountActivity
import org.junit.After
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@RunWith(AndroidJUnit4::class)
@LargeTest
class AccountActivityTest: BaseTest() {

    private val intent = Intent(ApplicationProvider.getApplicationContext(), AccountActivity::class.java). apply {
        putExtra(AccountActivity.TEST_EXTRA, true)
        putExtra(Actions.ACCOUNT_ID_EXTRA, "0")
    }

    private var scenario: ActivityScenario<AccountActivity>? = null

    @After
    fun tearDown() {
        scenario?.close()
        unloadKoinModules(mockModule)
        unloadKoinModules(errorMockModule)
    }

    @Test
    fun testSuccessWithCardData() {
        loadKoinModules(mockModule)
        scenario = ActivityScenario.launch(intent)
        account {
            checkLoadingGone()
            checkErrorGone()

            checkSixItemsInTransactionList()

            checkLabelBalanceTitleText()
            checkLabelBalanceValueText()

            validateValuesInTransactionList()
        }
    }

    @Test
    fun testFailure() {
        loadKoinModules(errorMockModule)
        scenario = ActivityScenario.launch(intent)
        account {
            checkLoadingGone()
            checkErrorVisible()

            checkErrorText()
        }
    }

}