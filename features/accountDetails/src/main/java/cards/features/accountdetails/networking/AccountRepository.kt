package cards.features.accountdetails.networking

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AccountRepository(
    private val service: AccountService
): AccountRepositoryInterface {

    private val data: LiveData<ApiResult<AccountDetail>> = MutableLiveData()

    override fun getLiveData(): LiveData<ApiResult<AccountDetail>> = data

    override fun getAccountDetail(accountId: String) {
        (data as MutableLiveData).value = ApiResult.Loading(true)
        service.getAccountDetail(accountId).enqueue(object: Callback<AccountDetail> {
            override fun onResponse(call: Call<AccountDetail>, response: Response<AccountDetail>) {
                data.value = ApiResult.Loading(false)
                if(response.code() != 200) { data.value = ApiResult.Failure(Error(response.errorBody()?.string())) }
                else { response.body()?.let { data.value = ApiResult.Success(it) } }
            }

            override fun onFailure(call: Call<AccountDetail>, t: Throwable) {
                data.value = ApiResult.Loading(false)
                data.value = ApiResult.Failure(Error(t.message))
            }
        })
    }

}