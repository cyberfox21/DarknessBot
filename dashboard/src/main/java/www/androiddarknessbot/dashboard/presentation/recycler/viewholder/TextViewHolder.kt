package www.androiddarknessbot.dashboard.presentation.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.databinding.ItemDashboardTextBinding
import www.androiddarknessbot.dashboard.presentation.recycler.item.TextItem

/**
 * @author t.shkolnik
 */
class TextViewHolder(private val binding: ItemDashboardTextBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: TextItem) {
        binding.tvDescription.text = item.title
        binding.tvValue.text = item.value.toString()
        binding.tvDimension.text = item.dimension
    }
}
