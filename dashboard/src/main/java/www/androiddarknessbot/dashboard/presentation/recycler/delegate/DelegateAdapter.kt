package www.androiddarknessbot.dashboard.presentation.recycler.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * @author t.shkolnik
 */
interface DelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: DelegateItem, position: Int)
    fun isMatchViewType(item: DelegateItem): Boolean
}
