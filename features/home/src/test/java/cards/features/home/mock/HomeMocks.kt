package cards.features.home.mock

import cards.core.model.RequestState
import cards.features.home.model.Widget
import cards.features.home.model.WidgetList
import cards.features.home.model.WidgetType
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

object HomeMocks {

    fun createSuccessResponse(): Response<WidgetList> {
        val list = ArrayList<Widget>()
        for(i in 0..10) {
            val widget = Widget(WidgetType.HOME_CARD, emptyMap())
            list.add(widget)
        }
        val widgetList = WidgetList(list)
        return Response.success(widgetList)
    }

    fun createSuccessRequestState(): RequestState.Success<WidgetList> {
        val list = ArrayList<Widget>()
        for(i in 0..10) {
            val widget = Widget(WidgetType.HOME_CARD, emptyMap())
            list.add(widget)
        }
        val widgetList = WidgetList(list)
        return RequestState.Success(widgetList)
    }

    fun createFailureRequestState() = RequestState.Failure<WidgetList>(Error("Error in getting widgets"))

    fun createFailureResponse() = Response.error<WidgetList>(
                                            404,
                                            ResponseBody.create(
                                                    MediaType.get("application/json"),
                                                    "Error in getting widgets"
                                            )
                                    )

}