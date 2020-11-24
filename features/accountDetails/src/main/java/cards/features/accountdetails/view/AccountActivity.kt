package cards.features.accountdetails.view

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import cards.features.accountdetails.R
import cards.features.accountdetails.di.module
import kotlinx.android.synthetic.main.activity_account.*
import org.koin.core.component.KoinApiExtension
import org.koin.core.component.KoinComponent
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules

@KoinApiExtension
class AccountActivity : AppCompatActivity(), KoinComponent {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_account)
        configureActionBar()
        loadKoinModules(module)
    }

    override fun onDestroy() {
        super.onDestroy()
        unloadKoinModules(module)
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
        setSupportActionBar(toolbar)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        toolbar.navigationIcon = obtainBackDrawable()
        toolbar.setNavigationOnClickListener {
            super.onBackPressed()
        }
    }

}