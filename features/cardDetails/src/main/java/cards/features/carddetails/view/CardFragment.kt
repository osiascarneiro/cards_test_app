package cards.features.carddetails.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import cards.actions.Actions
import cards.features.carddetails.R
import cards.features.carddetails.viewmodel.CardViewModel
import kotlinx.android.synthetic.main.fragment_card.*
import org.koin.android.viewmodel.ext.android.viewModel

class CardFragment : Fragment() {

    private val homeViewModel: CardViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.intent?.apply {
            val cardId = this.getStringExtra(Actions.CARD_ID_EXTRA)
            homeViewModel.cardId = cardId

            homeViewModel.cardLiveData.observe(requireActivity(), {
                cardNumber.text = it.cardNumber
                cardName.text = it.cardName
                cardExpiration.text = "Expiration ${it.expirationDate}"
                availableLimit.text = "Limite disponível: ${it.availableLimit}"
                totalLimit.text = "Limite total: ${it.totalLimit}"
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_card, container, false)
    }

    companion object {
        @JvmStatic fun newInstance() = CardFragment()
    }

}