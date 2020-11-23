package cards.features.accountdetails.networking

import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(
    private val service: AccountService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): ApiResult<AccountDetail> = withContext(dispatcher) {
        val response = service.getAccountDetail(accountId)
        if(response.code() != 200) { return@withContext ApiResult.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext ApiResult.Success(it) } }
        ApiResult.Failure(Error("Erro desconhecido"))
    }

}