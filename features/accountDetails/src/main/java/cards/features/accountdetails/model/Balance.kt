package cards.features.accountdetails.model

import com.google.gson.annotations.SerializedName

data class Balance(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: String
)