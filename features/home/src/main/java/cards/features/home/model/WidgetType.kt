package cards.features.home.model

import com.google.gson.annotations.SerializedName

enum class WidgetType(val value: Int) {
    @SerializedName("HOME_HEADER_WIDGET")
    HOME_HEADER(1),
    @SerializedName("HOME_CARD_WIDGET")
    HOME_CARD(2),
    @SerializedName("HOME_STATEMENT_WIDGET")
    HOME_STATEMENT(3)

}