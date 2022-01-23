package www.androiddarknessbot.dashboard.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import www.androiddarknessbot.dashboard.data.DemoAdapter
import www.androiddarknessbot.dashboard.data.TestAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic
import www.androiddarknessbot.dashboard.domain.usecase.GetCharacteristicsUseCase
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

class DashboardViewModel : ViewModel() {

    private val demoAdapter = DemoAdapter()

    private val mapper = CharacteristicMapper()

    private var _characteristicList: LiveData<List<DelegateItem>> = MediatorLiveData<List<DelegateItem>>().apply{
        addSource(GetCharacteristicsUseCase(demoAdapter).invoke()){ list ->
            postValue(mapper.mapToDashboardItemType(list))
            Log.d("DashboardViewModel", "update")
        }
    }
           val characteristicList get() = _characteristicList

    fun callDemoAdapter() {
//        _characteristicList.value = CharacteristicMapper()
//            .mapToDashboardItemType(GetCharacteristicsUseCase(TestAdapter()).invoke())

    }

    override fun onCleared() {
        super.onCleared()
        demoAdapter.stopTimer()
    }
}