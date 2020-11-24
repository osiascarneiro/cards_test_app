package cards.features.accountdetails.data

import cards.core.model.RequestState
import cards.features.accountdetails.model.AccountDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccountRepository(
    private val service: AccountService,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): RequestState<AccountDetail> = withContext(dispatcher) {
        val response = service.getAccountDetail(accountId)
        if(response.code() != 200) { return@withContext RequestState.Failure(Error(response.errorBody()?.string())) }
        else { response.body()?.let { return@withContext RequestState.Success(it) } }
        RequestState.Failure(Error("Erro desconhecido"))
    }

}