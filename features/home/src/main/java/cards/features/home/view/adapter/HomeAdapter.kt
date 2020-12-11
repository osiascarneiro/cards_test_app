package cards.features.home.view.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import cards.features.home.model.Widget
import cards.features.home.view.factory.WidgetFactory
import cards.features.home.view.widgets.BaseWidget

class HomeAdapter: RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    //Filtering null identifiers
    var widgets: List<Widget> = emptyList()
        set(value) {
            field = value.filter { it.identifier != null }
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = WidgetFactory.getWidget(viewType, parent.context)
                        ?: throw UnsupportedOperationException("Identifier not mapped")
        return HomeViewHolder(view)
    }

    override fun getItemViewType(position: Int): Int = widgets[position].identifier!!.value

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val widget = widgets[position]
        holder.view.buildView(widget)
    }

    override fun getItemCount(): Int = widgets.count()

    inner class HomeViewHolder(val view: BaseWidget): RecyclerView.ViewHolder(view)

}