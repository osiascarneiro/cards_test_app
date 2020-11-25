package cards.features.accountdetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import cards.actions.Actions
import cards.core.model.RequestState
import cards.features.accountdetails.databinding.FragmentAccountBinding
import cards.features.accountdetails.model.Balance
import cards.features.accountdetails.view.adapter.TransactionsAdapter
import cards.features.accountdetails.viewmodel.AccountViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AccountFragment : Fragment() {

    private val accountViewModel: AccountViewModel by viewModel()
    private val transactionsAdapter = TransactionsAdapter()

    private var binding: FragmentAccountBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountViewModel.accountLiveData.observe(this, Observer {
            when(it) {
                is RequestState.Loading -> {
                    binding?.loadingBar?.isVisible = it.loading
                }
                is RequestState.Failure -> {
                    with(binding?.errorText) {
                        this?.visibility = View.VISIBLE
                        this?.text = it.error.message
                    }
                }
                is RequestState.Success -> {
                    with(it.result) {
                        populateBalance(balance)
                        transactionsAdapter.apply {
                            transactions = this@with.transactions
                            notifyDataSetChanged()
                        }
                    }
                }
            }
        })

        extractArguments()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAccountBinding.inflate(inflater)
        binding?.transactions?.adapter = transactionsAdapter
        return binding?.root
    }

    private fun populateBalance(balance: Balance) {
        binding?.labelBalance?.text = balance.label
        binding?.valueBalance?.text = balance.value
    }

    private fun extractArguments() {
        activity?.intent?.apply {
            val cardId = getStringExtra(Actions.ACCOUNT_ID_EXTRA)
            accountViewModel.accountId = cardId
        }
    }
}