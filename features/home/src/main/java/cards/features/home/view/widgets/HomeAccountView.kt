package cards.features.home.view.widgets

import android.content.Context
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import cards.features.home.R
import cards.features.home.databinding.HomeAccountViewBinding
import cards.features.home.model.AccountContent
import cards.features.home.model.Widget

class HomeAccountView(context: Context): BaseWidget(context) {

    private lateinit var binding: HomeAccountViewBinding

    init {
        setBackground(context)
        setMargins(context)
        setPadding(context)
    }

    override fun buildView(widget: Widget) {
        binding = HomeAccountViewBinding.inflate(LayoutInflater.from(context), this)
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

    private fun setPadding(context: Context) {
        val doubleDefaultMargin = context.resources.getDimension(R.dimen.double_default_margin).toInt()
        setPadding(doubleDefaultMargin, doubleDefaultMargin, doubleDefaultMargin, doubleDefaultMargin)
    }

    private fun setMargins(context: Context) {
        val params = layoutParams as? LayoutParams
        val defaultMargin = context.resources.getDimension(R.dimen.default_margin).toInt()
        params?.setMargins(defaultMargin, defaultMargin, defaultMargin, defaultMargin)
        layoutParams = params
    }

    private fun setBackground(context: Context) {
        background = ContextCompat.getDrawable(context, R.drawable.stroke)
    }

}