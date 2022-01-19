package www.androiddarknessbot.dashboard.domain.usecase

import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

class GetCharacteristicsUseCase(private val adapter: BaseAdapter) {
    operator fun invoke(): List<Characteristic> = adapter.getCharacteristics()
}