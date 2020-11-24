package cards.core.test.base

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Rule

open class BaseUnitTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

}