package cards.features.home

import android.content.Intent
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import cards.features.home.di.mockModule
import cards.features.home.di.module
import cards.features.home.view.HomeActivity
import org.junit.*
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin

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
        if(GlobalContext.getOrNull() != null) {
            stopKoin()
        }
    }

    @After
    fun tearDown() {
        activityRule.scenario.close()
    }

    @Test
    fun testWidgetList() {
        onView(withId(R.id.loadingBar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)))
    }

}