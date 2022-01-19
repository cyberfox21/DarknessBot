package www.androiddarknessbot.dashboard.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import www.androiddarknessbot.dashboard.data.TestAdapter
import www.androiddarknessbot.dashboard.domain.usecase.GetCharacteristicsUseCase
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

class DashboardViewModel : ViewModel() {
    private var _characteristicList = MutableLiveData<List<DelegateItem>>()
    val characteristicList get() = _characteristicList

    fun callDemoAdapter() {
        _characteristicList.value = CharacteristicMapper()
            .mapToDashboardItemType(GetCharacteristicsUseCase(TestAdapter()).invoke())
    }
}