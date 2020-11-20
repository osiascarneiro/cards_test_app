package cards.features.carddetails

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_card.*

class CardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)
        configureActionBar()
    }

    private fun obtainBackDrawable(): Drawable? {
        val a = theme.obtainStyledAttributes(android.R.style.Theme_Material_NoActionBar, intArrayOf(R.attr.homeAsUpIndicator))
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