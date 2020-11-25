package cards.features.carddetails.view

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import cards.core.configureToolbar
import cards.features.carddetails.databinding.ActivityCardBinding
import cards.features.carddetails.di.module
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@KoinApiExtension
class CardActivity : AppCompatActivity(), KoinComponent {

    companion object {
        //used to inject mock modules in test
        const val TEST_EXTRA = "test_extra_key"
    }

    private lateinit var binding: ActivityCardBinding

    private val isTest: Boolean
        get() = intent.getBooleanExtra(TEST_EXTRA, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCardBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        configureToolbar(binding.toolbar)
        if(!isTest) loadKoinModules(module)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isTest) unloadKoinModules(module)
    }

}