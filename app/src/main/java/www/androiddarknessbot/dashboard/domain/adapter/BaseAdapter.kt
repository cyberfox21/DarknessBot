package www.androiddarknessbot.dashboard.domain.adapter

import www.androiddarknessbot.dashboard.domain.entity.Characteristic

abstract class BaseAdapter {
    abstract fun getCharacteristics(): List<Characteristic>
}