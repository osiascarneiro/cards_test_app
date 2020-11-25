package cards.features.accountdetails.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cards.core.configureToolbar
import cards.features.accountdetails.R
import cards.features.accountdetails.databinding.ActivityAccountBinding
import cards.features.accountdetails.di.module
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@KoinApiExtension
class AccountActivity : AppCompatActivity(), KoinComponent {

    companion object {
        //used to inject mock modules in test
        const val TEST_EXTRA = "test_extra_key"
    }

    private lateinit var binding: ActivityAccountBinding

    private val isTest: Boolean
        get() = intent.getBooleanExtra(TEST_EXTRA, false)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        configureToolbar(binding.toolbar)
        if(!isTest) loadKoinModules(module)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isTest) unloadKoinModules(module)
    }

}