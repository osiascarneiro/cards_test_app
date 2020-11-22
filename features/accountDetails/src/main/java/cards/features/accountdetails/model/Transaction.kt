package cards.features.accountdetails.model

import com.google.gson.annotations.SerializedName

data class Transaction(
    @SerializedName("label")
    val label: String,
    @SerializedName("value")
    val value: String,
    @SerializedName("description")
    val description: String
)