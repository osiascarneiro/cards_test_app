package cards.features.home.networking

import cards.core.model.ApiResult
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType

class MockWidgetRepository: WidgetRepositoryInterface {

    override suspend fun getWidgets(): ApiResult<WidgetList> {
        val list = ArrayList<Widget>()
        val widgetHome = Widget(WidgetType.HOME_CARD, emptyMap())
        list.add(widgetHome)
        val widgetHeader = Widget(WidgetType.HOME_HEADER, emptyMap())
        list.add(widgetHeader)
        val widgetStatement = Widget(WidgetType.HOME_STATEMENT, emptyMap())
        list.add(widgetStatement)
        val widgetList = WidgetList(list)

        return ApiResult.Success(widgetList)
    }

}