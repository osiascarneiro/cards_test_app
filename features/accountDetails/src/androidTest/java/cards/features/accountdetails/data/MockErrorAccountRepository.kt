package cards.features.accountdetails.data

import cards.core.model.RequestState
import cards.features.accountdetails.model.AccountDetail

class MockErrorAccountRepository: AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): RequestState<AccountDetail> {
        return RequestState.Failure(Error("Error in getting transactions"))
    }
}