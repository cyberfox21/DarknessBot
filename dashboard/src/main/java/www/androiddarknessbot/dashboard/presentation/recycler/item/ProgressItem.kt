package www.androiddarknessbot.dashboard.presentation.recycler.item

import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

/**
 * @author t.shkolnik
 */
data class ProgressItem(
    val id: Int,
    val title: String,
//    val progress: Int,
    val max: Int,
    val value: Int,
    val dimension: String
) : DelegateItem {

    override fun id() = title

    override fun content() = value
}
