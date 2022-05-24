package www.androiddarknessbot.dashboard.presentation.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.databinding.ItemDashboardImageBinding
import www.androiddarknessbot.dashboard.presentation.recycler.item.ImageItem

/**
 * @author t.shkolnik
 */
class ImageViewHolder(private val binding: ItemDashboardImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageItem) {
        binding.tvImageDescription.text = item.title
        binding.ivImage.setImageResource(item.image)
    }
}
