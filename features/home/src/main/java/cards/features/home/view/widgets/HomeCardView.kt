package cards.features.home.view.widgets

import android.content.ActivityNotFoundException
import android.content.Context
import android.view.LayoutInflater
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import cards.features.home.R
import cards.features.home.databinding.HomeCardViewBinding
import cards.features.home.model.CardContent
import cards.features.home.model.Widget

class HomeCardView(context: Context): BaseWidget(context) {

    init {
        setBackground(context)
        setMargins(context)
        setPadding(context)
    }

    private lateinit var binding: HomeCardViewBinding

    override fun buildView(widget: Widget) {
        binding = HomeCardViewBinding.inflate(LayoutInflater.from(context), this)
        val cardContent = CardContent.fromMap(widget.content)
        with(binding) {
            cardName.text = cardContent.title
            cardNumber.text = cardContent.cardNumber
            actionButton.text = cardContent.button.title
            actionButton.setOnClickListener {
                try {
                    cardContent.button.action?.intent(context)?.let { context.startActivity(it) }
                } catch (e: ActivityNotFoundException) {
                    Toast.makeText(context, "Em breve", Toast.LENGTH_SHORT).show()
                }
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