package www.androiddarknessbot.dashboard.presentation.recycler.delegate

import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import www.androiddarknessbot.dashboard.presentation.recycler.DiffUtilCallback

/**
 * @author t.shkolnik
 */
class MainAdapter : ListAdapter<DelegateItem, RecyclerView.ViewHolder>(DiffUtilCallback()) {

    private val delegates = mutableListOf<DelegateAdapter>()

    fun addDelegate(delegateAdapter: DelegateAdapter) = delegates.add(delegateAdapter)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        delegates[viewType].onCreateViewHolder(parent)


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        delegates[holder.itemViewType].onBindViewHolder(holder, currentList[position], position)


    override fun getItemViewType(position: Int): Int =
        delegates.indexOfFirst { it.isMatchViewType(currentList[position]) }
}
