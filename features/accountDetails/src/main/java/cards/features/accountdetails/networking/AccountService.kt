package cards.features.accountdetails.networking

import cards.features.accountdetails.model.AccountDetail
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountService {

    @GET("statement/{account}")
    suspend fun getAccountDetail(@Path("account") accountId: String): Response<AccountDetail>

}