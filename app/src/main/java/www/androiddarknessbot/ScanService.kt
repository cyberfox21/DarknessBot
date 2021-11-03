package www.androiddarknessbot

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.os.IBinder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ScanService : Service() {

    private val scope = CoroutineScope(Dispatchers.Main)

    private val leScanCallback = object : ScanCallback(){
        override fun onScanResult(callbackType: Int, result: ScanResult?) {
            super.onScanResult(callbackType, result)
            result?.device
        }

        override fun onScanFailed(errorCode: Int) {
            super.onScanFailed(errorCode)
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
//        scope.launch {
//            val bluetoothManager = getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
//            val bAdapter = bluetoothManager.adapter
//            val bluetoothLeScanner = bAdapter.bluetoothLeScanner
//            bluetoothLeScanner.startScan(leScanCallback)
//        }
        return START_NOT_STICKY
    }

    override fun onBind(intent: Intent): IBinder {
        TODO("Return the communication channel to the service.")
    }

    companion object{

        fun newIntent(context: Context) : Intent{
            return Intent(context, ScanService::class.java)
        }
    }
}