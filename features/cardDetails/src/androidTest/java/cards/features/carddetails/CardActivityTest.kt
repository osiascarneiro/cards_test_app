package cards.features.carddetails

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cards.actions.Actions
import cards.features.carddetails.di.mockErrorModule
import cards.features.carddetails.di.mockModule
import cards.features.carddetails.view.CardActivity
import org.junit.After
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@RunWith(AndroidJUnit4::class)
@LargeTest
class CardActivityTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val intent = Intent(ApplicationProvider.getApplicationContext(), CardActivity::class.java). apply {
        putExtra(CardActivity.TEST_EXTRA, true)
        putExtra(Actions.CARD_ID_EXTRA, "0")
    }

    private var scenario: ActivityScenario<CardActivity>? = null

    @After
    fun tearDown() {
        scenario?.close()
        unloadKoinModules(mockModule)
        unloadKoinModules(mockErrorModule)
    }

    @Test
    fun testWithCardData() {
        loadKoinModules(mockModule)
        scenario = ActivityScenario.launch(intent)
        cardRobot {
            checkLoadingGone()
            checkErrorGone()

            checkCardNumber()
            checkCardName()
            checkCardExpiration()
            checkAvailableLimit()
            checkTotalLimit()
        }
    }

    @Test
    fun testWithError() {
        loadKoinModules(mockErrorModule)
        scenario = ActivityScenario.launch(intent)
        cardRobot {
            checkLoadingGone()
            checkErrorVisible()

            checkErrorText()
        }
    }

}