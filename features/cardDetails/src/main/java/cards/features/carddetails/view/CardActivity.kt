package cards.features.carddetails.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import cards.features.carddetails.R
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
        configureActionBar()
        if(!isTest) loadKoinModules(module)
    }

    override fun onDestroy() {
        super.onDestroy()
        if(!isTest) unloadKoinModules(module)
    }

    private fun obtainBackDrawable(): Drawable? {
        val a = theme.obtainStyledAttributes(android.R.style.Theme_Material_NoActionBar, intArrayOf(
            R.attr.homeAsUpIndicator
        ))
        val attributeResourceId = a.getResourceId(0, 0)
        val icon = ContextCompat.getDrawable(this, attributeResourceId)
        a.recycle()
        return icon
    }

    private fun configureActionBar() {
        with(binding.toolbar) {
            setSupportActionBar(this)
            supportActionBar?.apply {
                setHomeButtonEnabled(true)
                setDisplayShowHomeEnabled(true)
            }

            navigationIcon = obtainBackDrawable()
            setNavigationOnClickListener {
                super.onBackPressed()
            }
        }
    }
}