package www.androiddarknessbot.dashboard.presentation.recycler.item

import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

data class TextItem(
    val id: Int,
    val title: String,
    val value: Int,
    val dimension: String
) : DelegateItem {

    override fun id() = id

    override fun content() = value
}