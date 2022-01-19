package www.androiddarknessbot.dashboard.presentation.recycler

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

class DiffUtilCallback : DiffUtil.ItemCallback<DelegateItem>() {
    override fun areItemsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean =
        oldItem.id() == newItem.id()


    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: DelegateItem, newItem: DelegateItem): Boolean =
        oldItem.content() == newItem.content()

}