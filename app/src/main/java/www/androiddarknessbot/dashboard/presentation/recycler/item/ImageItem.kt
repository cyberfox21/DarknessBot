package www.androiddarknessbot.dashboard.presentation.recycler.item

import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

class ImageItem(
    val id: Int,
    val title: String,
    val image: Int
) : DelegateItem {

    override fun id() = id

    override fun content() = image
}