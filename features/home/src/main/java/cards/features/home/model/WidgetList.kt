package cards.features.home.model

import com.google.gson.annotations.SerializedName

data class WidgetList(
    @SerializedName("widgets")
    val widgets: List<Widget>
)