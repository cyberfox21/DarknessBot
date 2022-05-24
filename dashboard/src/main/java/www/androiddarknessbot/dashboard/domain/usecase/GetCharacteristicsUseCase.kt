package www.androiddarknessbot.dashboard.domain.usecase

import androidx.lifecycle.LiveData
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

/**
 * @author t.shkolnik
 */
class GetCharacteristicsUseCase(private val adapter: BaseAdapter) {
    operator fun invoke(): LiveData<List<Characteristic>> = adapter.getCharacteristics()
}
