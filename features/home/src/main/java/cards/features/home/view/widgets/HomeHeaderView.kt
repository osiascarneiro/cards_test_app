package cards.features.home.view.widgets

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.FrameLayout
import cards.features.home.R
import cards.features.home.model.HeaderContent
import cards.features.home.model.Widget
import kotlinx.android.synthetic.main.home_header_view.view.*

class HomeHeaderView(widget: Widget, context: Context): BaseWidget(widget, context) {

    override fun buildView(widget: Widget) {
        inflate(context, R.layout.home_header_view, this)
        val header = HeaderContent.fromMap(widget.content)
        titleText.text = header.title
    }

}