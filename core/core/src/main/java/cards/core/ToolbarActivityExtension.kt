package cards.core

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import com.osias.core.R

fun AppCompatActivity.configureToolbar(toolbar: Toolbar) {

    val a = theme.obtainStyledAttributes(android.R.style.Theme_Material_NoActionBar, intArrayOf(
        R.attr.homeAsUpIndicator
    ))
    val attributeResourceId = a.getResourceId(0, 0)
    val icon = ContextCompat.getDrawable(this, attributeResourceId)
    a.recycle()

    with(toolbar) {
        setSupportActionBar(this)
        supportActionBar?.apply {
            setHomeButtonEnabled(true)
            setDisplayShowHomeEnabled(true)
        }

        navigationIcon = icon
        setNavigationOnClickListener {
            onBackPressed()
        }
    }
}