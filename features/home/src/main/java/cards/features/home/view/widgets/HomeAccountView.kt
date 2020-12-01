package cards.features.home.view.widgets

import android.content.Context
import android.view.LayoutInflater
import cards.features.home.databinding.HomeAccountViewBinding
import cards.features.home.model.AccountContent
import cards.features.home.model.Widget

class HomeAccountView(context: Context): BaseWidget(context) {

    private lateinit var binding: HomeAccountViewBinding

    override fun buildView(widget: Widget) {
        binding = HomeAccountViewBinding.inflate(LayoutInflater.from(context), this, true)
        val account = AccountContent.fromMap(widget.content)
        with(binding) {
            accountTitle.text = account.title
            accountLabel.text = account.balance.label
            accountBalance.text = account.balance.value
            actionButton.text = account.button.title
            actionButton.setOnClickListener {
                account.button.action?.intent(context)?.let { context.startActivity(it) }
            }
        }
    }

}