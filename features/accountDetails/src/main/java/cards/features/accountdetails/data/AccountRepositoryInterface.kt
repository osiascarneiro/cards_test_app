package cards.features.accountdetails.data

import cards.core.model.RequestState
import cards.features.accountdetails.model.AccountDetail

interface AccountRepositoryInterface {

    suspend fun getAccountDetail(accountId: String): RequestState<AccountDetail>

}