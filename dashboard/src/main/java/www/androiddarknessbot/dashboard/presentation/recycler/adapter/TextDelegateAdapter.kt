package www.androiddarknessbot.dashboard.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.databinding.ItemDashboardTextBinding
import www.androiddarknessbot.dashboard.presentation.recycler.ScreenMeasurer
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem
import www.androiddarknessbot.dashboard.presentation.recycler.item.TextItem
import www.androiddarknessbot.dashboard.presentation.recycler.viewholder.TextViewHolder

/**
 * @author t.shkolnik
 */
class TextDelegateAdapter(private val screenMeasurer: ScreenMeasurer) : DelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder = TextViewHolder(
        ItemDashboardTextBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            .apply { root.layoutParams.width = screenMeasurer.getDashboardItemWidth() }
    )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as TextViewHolder).bind(item as TextItem)
    }

    override fun isMatchViewType(item: DelegateItem): Boolean = item is TextItem

}
