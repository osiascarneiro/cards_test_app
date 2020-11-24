package cards.features.accountdetails.networking

import cards.core.model.ApiResult
import cards.features.accountdetails.model.AccountDetail
import cards.features.accountdetails.model.Balance
import cards.features.accountdetails.model.Transaction

class MockAccountRepository: AccountRepositoryInterface {

    override suspend fun getAccountDetail(accountId: String): ApiResult<AccountDetail> {
        val balance = Balance("Saldo disponível", "R$ 5.000,00")
        val list = ArrayList<Transaction>()
        for(i in 0..5) {
            val transaction = Transaction("Compra nº $i", "R$ $i,00", "Compra com cartão $i")
            list.add(transaction)
        }
        val accountDetail = AccountDetail(balance, list)
        return ApiResult.Success(accountDetail)
    }

}