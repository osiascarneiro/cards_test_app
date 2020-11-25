package cards.features.carddetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import cards.actions.Actions
import cards.core.model.RequestState
import cards.features.carddetails.R
import cards.features.carddetails.databinding.FragmentCardBinding
import cards.features.carddetails.model.CardDetails
import cards.features.carddetails.viewmodel.CardViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val cardViewModel: CardViewModel by viewModel()

    private var binding: FragmentCardBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        cardViewModel.cardLiveData.observe(requireActivity(), {
            when(it) {
                is RequestState.Failure -> {
                    with(binding?.errorText) {
                        this?.visibility = View.VISIBLE
                        this?.text = it.error.message
                    }
                }
                is RequestState.Success -> {
                    populateCard(it.result)
                }
                is RequestState.Loading -> {
                    binding?.loadingBar?.isVisible = it.loading
                }
            }
        })

        extractArguments()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCardBinding.inflate(inflater)
        return binding?.root
    }

    private fun extractArguments() {
        activity?.intent?.apply {
            val cardId = this.getStringExtra(Actions.CARD_ID_EXTRA)
            cardViewModel.cardId = cardId
        }
    }

    private fun populateCard(card: CardDetails) {
        binding?.cardNumber?.text = card.cardNumber
        binding?.cardName?.text = card.cardName
        binding?.cardExpiration?.text = getString(R.string.expiration_date, card.expirationDate)
        binding?.availableLimit?.text = getString(R.string.available_limit, card.availableLimit)
        binding?.totalLimit?.text = getString(R.string.total_limit, card.totalLimit)
    }

}