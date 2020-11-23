package cards.features.home.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import cards.features.home.R
import cards.features.home.di.module
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@KoinApiExtension
class HomeActivity: AppCompatActivity(), KoinComponent {

    companion object {
        //used to inject mock modules in test
        const val TEST_EXTRA = "test_extra_key"
    }

    private val isTest: Boolean
        get() = intent.getBooleanExtra(TEST_EXTRA, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        if(!isTest) loadKoinModules(module)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isTest) unloadKoinModules(module)
    }

}