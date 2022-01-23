package www.androiddarknessbot.dashboard.domain.adapter

import androidx.lifecycle.LiveData
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

abstract class BaseAdapter {
    abstract fun getCharacteristics(): LiveData<List<Characteristic>>
}