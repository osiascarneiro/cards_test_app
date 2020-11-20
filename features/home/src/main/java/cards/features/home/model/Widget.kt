package cards.features.home.model

import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("identifier")
    val identifier: WidgetType?,
    @SerializedName("content")
    val content: Map<String,Any>
)