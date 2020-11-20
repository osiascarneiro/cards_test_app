package cards.features.home.view.adapter

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.recyclerview.widget.RecyclerView
import cards.features.home.model.Widget
import cards.features.home.view.factory.WidgetFactory

class HomeAdapter(var widgets: List<Widget>): RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder = HomeViewHolder(
        FrameLayout(parent.context)
    )

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val widget = widgets[position]
        holder.view.addView(WidgetFactory.getWidget(widget, holder.view.context))
    }

    override fun getItemCount(): Int = widgets.count()

    inner class HomeViewHolder(val view: FrameLayout): RecyclerView.ViewHolder(view) {
        init {
            view.layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            )
        }
    }

}