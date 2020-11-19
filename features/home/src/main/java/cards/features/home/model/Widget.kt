package cards.features.home.model

import com.google.gson.annotations.SerializedName

data class Widget(
    @SerializedName("identifier")
    val identifier: String,
    @SerializedName("content")
    val content: String
)