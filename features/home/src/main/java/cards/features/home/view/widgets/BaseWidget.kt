package cards.features.home.view.widgets

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import cards.features.home.model.Widget

abstract class BaseWidget(widget: Widget, context: Context): FrameLayout(context) {

    init {
        layoutParams = LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        )
        buildView(widget)
    }

    abstract fun buildView(widget: Widget)

}