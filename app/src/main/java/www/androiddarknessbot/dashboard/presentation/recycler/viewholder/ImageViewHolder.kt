package www.androiddarknessbot.dashboard.presentation.recycler.viewholder

import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.presentation.recycler.item.ImageItem
import www.androiddarknessbot.databinding.ItemDashboardImageBinding

class ImageViewHolder(private val binding: ItemDashboardImageBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: ImageItem) {
        binding.tvImageDescription.text = item.title
        binding.ivImage.setImageResource(item.image)
    }
}