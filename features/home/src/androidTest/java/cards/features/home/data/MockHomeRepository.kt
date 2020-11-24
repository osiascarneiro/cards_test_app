package cards.features.home.data

import cards.core.model.RequestState
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType
import cards.features.home.util.MockMapProvider

class MockHomeRepository: HomeRepositoryInterface {

    override suspend fun getWidgets(): RequestState<WidgetList> {
        val list = ArrayList<Widget>()
        val widgetHome = Widget(WidgetType.HOME_CARD, MockMapProvider.getCardContent())
        list.add(widgetHome)
        val widgetHeader = Widget(WidgetType.HOME_HEADER, MockMapProvider.getHeaderContent())
        list.add(widgetHeader)
        val widgetStatement = Widget(WidgetType.HOME_STATEMENT, MockMapProvider.getStatementContent())
        list.add(widgetStatement)
        val widgetList = WidgetList(list)

        return RequestState.Success(widgetList)
    }

}