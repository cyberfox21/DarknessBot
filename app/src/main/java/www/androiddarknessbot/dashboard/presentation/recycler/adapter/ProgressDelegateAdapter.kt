package www.androiddarknessbot.dashboard.presentation.recycler.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.presentation.recycler.ScreenMeasurer
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateAdapter
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem
import www.androiddarknessbot.dashboard.presentation.recycler.item.ProgressItem
import www.androiddarknessbot.dashboard.presentation.recycler.viewholder.ProgressViewHolder
import www.androiddarknessbot.databinding.ItemDashboardProgressBinding

class ProgressDelegateAdapter(private val screenMeasurer: ScreenMeasurer) : DelegateAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder =
        ProgressViewHolder(
            ItemDashboardProgressBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                .apply { root.layoutParams.width = screenMeasurer.getDashboardItemWidth() }
        )

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        item: DelegateItem,
        position: Int
    ) {
        (holder as ProgressViewHolder).bind(item as ProgressItem)
    }

    override fun isMatchViewType(item: DelegateItem): Boolean = item is ProgressItem

}