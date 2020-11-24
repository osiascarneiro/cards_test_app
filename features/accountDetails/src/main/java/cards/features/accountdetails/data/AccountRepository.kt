package cards.features.accountdetails.data

import cards.core.data.safeAPICall
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
        return@withContext safeAPICall { service.getAccountDetail(accountId) }
    }

}