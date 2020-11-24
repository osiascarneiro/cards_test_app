package cards.features.accountdetails.mock

import cards.core.model.RequestState
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.model.Balance
import okhttp3.MediaType
import okhttp3.ResponseBody
import retrofit2.Response

object AccountMocks {

    fun createSuccessResponse(): Response<AccountDetail> {
        val account = AccountDetail(Balance("asd", "123,00"), emptyList())
        return Response.success(account)
    }

    fun createFailureResponse(): Response<AccountDetail> {
        return Response.error(404, ResponseBody.create(MediaType.get("application/json"), "Error in getting account"))
    }

    fun createSuccessRequestState(): RequestState.Success<AccountDetail> {
        val account = AccountDetail(Balance("asd", "123,00"), emptyList())
        return RequestState.Success(account)
    }

    fun createFailureRequestState(): RequestState.Failure<AccountDetail> {
        return RequestState.Failure<AccountDetail>( Error("Error in getting account"))
    }

}