package www.androiddarknessbot.dashboard.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import www.androiddarknessbot.dashboard.data.adapter.DemoAdapter
import www.androiddarknessbot.dashboard.domain.usecase.GetCharacteristicsUseCase
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem

class DashboardViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private lateinit var adapter: DemoAdapter

    private val mapper = CharacteristicMapper()

    private var _characteristicList: LiveData<List<DelegateItem>> =
        MediatorLiveData<List<DelegateItem>>()

    val characteristicList get() = _characteristicList

    //private val _shouldCallBluetoothService = MutableLiveData<Boolean>().apply { value = false }
    ////val shouldCallBluetoothService get() = _shouldCallBluetoothService

    fun callDemoAdapter() {
        adapter = DemoAdapter()
        (_characteristicList as MediatorLiveData)
            .addSource(GetCharacteristicsUseCase(adapter).invoke()) { list ->
                (_characteristicList as MediatorLiveData)
                    .postValue(mapper.mapToDashboardItemType(list))
                Log.d("DashboardViewModel", "update")
            }
    }


    fun callWheelResolver() {
        // todo call bluetoothService
        //shouldCallBluetoothService.value = true
    }

    override fun onCleared() {
        super.onCleared()
        adapter.stopTimer()
    }
}