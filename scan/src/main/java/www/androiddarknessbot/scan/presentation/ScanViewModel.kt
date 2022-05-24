package www.androiddarknessbot.scan.presentation

import android.app.Application
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import www.androiddarknessbot.core.domain.BotBluetoothDevice
import java.util.concurrent.TimeUnit

/**
 * @author t.shkolnik
 */
class ScanViewModel(application: Application) : AndroidViewModel(application) {

    private val context = application

    private var _state = MutableLiveData<ScanScreenState>()
    val state get() = _state

    var deviceList = sortedSetOf<BotBluetoothDevice>(
        comparator = { o1, o2 ->
            (o1.device.address).compareTo(o2.device.address)
        })

    private lateinit var bManager: BluetoothManager
    private var bAdapter: BluetoothAdapter? = null

    private var disposable: Disposable? = null

    private val leScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
            log("onScanResult")
            val device = BotBluetoothDevice(
                device = result.device,
                rssi = result.rssi
            )
            deviceList.add(device)
            _state.value = ScanScreenState.Scanning(deviceList.toList())
            log("deviceList = $deviceList")
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
            log("onScanFailed $errorCode")
        }
    }

    init {
        _state.value = ScanScreenState.Empty
        try {
            bManager = context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
            bAdapter = bManager.adapter
        } catch (e: Throwable) {
            log("Cant get Bluetooth Adapter")
        }
    }

    fun startScan() {
        bAdapter?.bluetoothLeScanner?.let { bluetoothLeScanner ->
            log("Scanner startScan")
            cleanList()
            bluetoothLeScanner.startScan(leScanCallback)
            disposable = Observable.timer(SCAN_DURATION_IN_MILLIS, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .subscribe {
                    log("Scanner stopScan")
                    bluetoothLeScanner.stopScan(leScanCallback)
                    state.postValue(ScanScreenState.Result(deviceList.toList()))
                }
        }
    }

    fun stopScan() {
        _state.value = ScanScreenState.Result(deviceList.toList())
        bAdapter?.bluetoothLeScanner?.stopScan(leScanCallback)
        disposable?.dispose()
    }

    private fun cleanList() {
        deviceList.clear()
        state.value = ScanScreenState.Result(listOf())
    }

    override fun onCleared() {
        super.onCleared()
        stopScan()
    }

    private fun log(message: String) {
        Log.d("CHECKER", "ScanViewModel: $message")
    }

    companion object {
        private const val SCAN_DURATION_IN_SECONDS = 15
        private const val SCAN_DURATION_IN_MILLIS = SCAN_DURATION_IN_SECONDS * 1000L
    }

}
