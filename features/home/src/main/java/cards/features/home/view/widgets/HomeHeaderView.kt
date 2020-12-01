package cards.features.home.view.widgets

import android.content.Context
import android.view.LayoutInflater
import cards.features.home.databinding.HomeHeaderViewBinding
import cards.features.home.model.HeaderContent
import cards.features.home.model.Widget

class HomeHeaderView(context: Context): BaseWidget(context) {

    private lateinit var binding: HomeHeaderViewBinding

    override fun buildView(widget: Widget) {
        binding = HomeHeaderViewBinding.inflate(LayoutInflater.from(context), this)
        val header = HeaderContent.fromMap(widget.content)
        binding.titleText.text = header.title
    }

}