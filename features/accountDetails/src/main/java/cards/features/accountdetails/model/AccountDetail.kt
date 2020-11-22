package cards.features.accountdetails.model

import com.google.gson.annotations.SerializedName

data class AccountDetail(
    @SerializedName("balance")
    val balance: Balance,
    @SerializedName("transactions")
    val transactions: List<Transaction>
)