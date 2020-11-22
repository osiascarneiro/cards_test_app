package cards.features.accountdetails.networking

import androidx.lifecycle.LiveData
import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail

interface AccountRepositoryInterface {

    fun getLiveData(): LiveData<ApiResult<AccountDetail>>
    suspend fun getAccountDetail(accountId: String)

}