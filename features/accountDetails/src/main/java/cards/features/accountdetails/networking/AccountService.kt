package cards.features.accountdetails.networking

import cards.features.accountdetails.model.AccountDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountService {

    @GET("statement/{account}")
    fun getAccountDetail(@Path("account") accountId: String): Call<AccountDetail>

}