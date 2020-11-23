package cards.features.accountdetails.networking

import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail

interface AccountRepositoryInterface {

    suspend fun getAccountDetail(accountId: String): ApiResult<AccountDetail>

}