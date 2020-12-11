package cards.features.home.view.factory

import android.content.Context
import cards.features.home.model.WidgetType
import cards.features.home.view.widgets.BaseWidget
import cards.features.home.view.widgets.HomeAccountView
import cards.features.home.view.widgets.HomeCardView
import cards.features.home.view.widgets.HomeHeaderView

object WidgetFactory {

    fun getWidget(widgetValue: Int, context: Context): BaseWidget? {
        val type = WidgetType.valueOf(widgetValue) ?: return null
        return when(type) {
            WidgetType.HOME_HEADER -> HomeHeaderView(context)
            WidgetType.HOME_CARD -> HomeCardView(context)
            WidgetType.HOME_STATEMENT -> HomeAccountView(context)
        }
    }

}