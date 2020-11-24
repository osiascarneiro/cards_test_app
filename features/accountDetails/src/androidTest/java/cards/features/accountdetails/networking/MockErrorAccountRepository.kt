package cards.features.accountdetails.networking

import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail

class MockErrorAccountRepository: AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): ApiResult<AccountDetail> {
        return ApiResult.Failure(Error("Error in getting transactions"))
    }
}