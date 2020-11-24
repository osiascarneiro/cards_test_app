package cards.core.test.util

import android.app.Instrumentation
import android.content.IntentFilter
import androidx.test.espresso.Espresso
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.platform.app.InstrumentationRegistry
import org.hamcrest.Matchers
import org.hamcrest.core.AllOf

open class BaseRobot {

    protected fun checkVisibilityWithId(id: Int, visibility: ViewMatchers.Visibility): ViewInteraction =
        getViewWithId(id)
            .check(
                ViewAssertions.matches(ViewMatchers.withEffectiveVisibility(visibility))
            )

    protected fun checkNumberOfItensInList(recyclerId: Int, count: Int): ViewInteraction = getViewWithId(recyclerId)
        .check(ViewAssertions.matches(CustomMatchers.withItemCount(count)))

    protected fun checkInstaceTypeInPosition(recyclerId: Int, position: Int, classType: Class<*>): ViewInteraction =
        getViewWithId(recyclerId)
            .check(
                ViewAssertions.matches(
                    CustomMatchers.atPosition(position, AllOf.allOf(Matchers.instanceOf(classType)))
                )
            )

    protected fun checkTextInPosition(recyclerId: Int, position: Int, text: String): ViewInteraction = getViewWithId(recyclerId)
        .check(
            ViewAssertions.matches(
                CustomMatchers.atPosition(
                    position,
                    ViewMatchers.withText(text)
                )
            )
        )

    protected fun addInstrumentationMonitor(action: String): Instrumentation.ActivityMonitor {
        val filter = IntentFilter(action)
        val am = Instrumentation.ActivityMonitor(filter, null, true)
        InstrumentationRegistry.getInstrumentation().addMonitor(am)
        return am
    }

    protected fun getViewWithId(id: Int) = Espresso.onView(ViewMatchers.withId(id))
    protected fun getViewWithIdAndTitle(id: Int, title: String) = Espresso.onView(
        AllOf.allOf(
            ViewMatchers.withId(id),
            ViewMatchers.withText(title)
        )
    )

    fun checkInstrumentationMonitorHitAndRemove(am: Instrumentation.ActivityMonitor) {
        assert(InstrumentationRegistry.getInstrumentation().checkMonitorHit(am, 1))
        InstrumentationRegistry.getInstrumentation().removeMonitor(am)
    }

}