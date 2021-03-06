package www.androiddarknessbot.dashboard.presentation.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.databinding.ItemDashboardProgressBinding
import www.androiddarknessbot.dashboard.presentation.recycler.item.ProgressItem

/**
 * @author t.shkolnik
 */
class ProgressViewHolder(private val binding: ItemDashboardProgressBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ProgressItem) {
        binding.tvName.text = item.title
        binding.tvPercent.text = item.value.toString()
        binding.tvDimension.text = item.dimension
        binding.arcIndicator.progressMax = item.max
        binding.arcIndicator.progress = item.value
    }
}
