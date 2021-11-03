package www.androiddarknessbot.presentation

import android.Manifest
import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanSettings
import android.content.Context
import android.content.Context.BLUETOOTH_SERVICE
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemServiceName
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import www.androiddarknessbot.ScanService
import www.androiddarknessbot.data.DeviceListRepositoryImpl
import www.androiddarknessbot.domain.entity.BotBluetoothDevice
import www.androiddarknessbot.domain.usecase.AddDeviceUseCase
import www.androiddarknessbot.domain.usecase.GetDeviceListUseCase
import www.androiddarknessbot.domain.usecase.GetDeviceUseCase

class ScanViewModel(application: Application) : AndroidViewModel(application) {

    //    private val onBtnScanClickListener : OnBtnScanClickListener? = null

    private val repository = DeviceListRepositoryImpl()

    private val getDeviceListUseCase = GetDeviceListUseCase(repository)
    private val getDeviceUseCase = GetDeviceUseCase(repository)
    private val addDeviceUseCase = AddDeviceUseCase(repository)

    val deviceListLD = getDeviceListUseCase()

    fun getDevice(id: Int) = getDeviceUseCase(id)

    fun addDevice(device: BotBluetoothDevice) = addDeviceUseCase(device)

    private val _scanState = MutableLiveData<Boolean>()


//    interface OnBtnScanClickListener {
//        fun onBtnScanClick()
//    }

    fun startScan() {
//        if(_scanState.value == true) return
//        _scanState.value = true
//        //start scanning
//        val bluetoothManager = getSystemServiceName(BLUETOOTH_SERVICE) as BluetoothManager
//        val bluetoothLeScanner = bluetoothManager
    }

    fun stopScan(){
        _scanState.value = false
    }



}