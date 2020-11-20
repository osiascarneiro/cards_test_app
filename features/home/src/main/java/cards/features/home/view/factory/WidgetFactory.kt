package cards.features.home.view.factory

import android.content.Context
import android.view.View
import cards.features.home.model.Widget
import cards.features.home.model.WidgetType
import cards.features.home.view.widgets.HomeAccountView
import cards.features.home.view.widgets.HomeCardView
import cards.features.home.view.widgets.HomeHeaderView

object WidgetFactory {

    fun getWidget(widget: Widget, context: Context): View {
        return when(widget.identifier) {
            WidgetType.HOME_HEADER -> {
                HomeHeaderView(widget, context)
            }
            WidgetType.HOME_CARD -> {
                HomeCardView(widget, context)
            }
            WidgetType.HOME_STATEMENT -> {
                HomeAccountView(widget, context)
            }
            else -> {
                //Widget not supported
                View(context)
            }
        }
    }

}