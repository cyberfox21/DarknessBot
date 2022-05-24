package www.androiddarknessbot.dashboard.data.adapter

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import www.androiddarknessbot.dashboard.domain.CharacteristicType
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic
import java.util.*

/**
 * @author t.shkolnik
 */
class DemoAdapter : BaseAdapter() {

    private var _characteristicList = MutableLiveData<List<Characteristic>>()
    private var list: List<Characteristic> = listOf(
        Characteristic("speed", 56, 200, "km/h", CharacteristicType.Speed),
        Characteristic("singleMaxSpeed", 40, 80, "km/h", CharacteristicType.SingleMaxSpeed),
        Characteristic("avgSpeed", 40, 80, "km/h", CharacteristicType.AvgSpeed),
        Characteristic("totalRuntime", 40, 44584, "sec", CharacteristicType.TotalRuntime),
        Characteristic("singleRuntime", 40, 454, "sec", CharacteristicType.SingleRuntime),
        Characteristic("totalMileage", 1000, 2000, "km", CharacteristicType.TotalMileage),
        Characteristic("singleMileage", 104, 2000, "km", CharacteristicType.SingleMileage),
        Characteristic("temperature", 43, 60, "'C", CharacteristicType.Temperature),
        Characteristic("battery", 15, 100, "%", CharacteristicType.Battery),
        Characteristic("remainingMileage", 15, 100, "km", CharacteristicType.RemainingMileage),
        Characteristic("power", 15, 100, "watt", CharacteristicType.Power),
        Characteristic("current", 15, 100, "watt", CharacteristicType.Current),
        Characteristic("voltage", 15, 100, "V", CharacteristicType.Voltage),
        Characteristic("pitch", 15, 100, "%", CharacteristicType.Pitch),
        Characteristic("roll", 15, 100, "%", CharacteristicType.Roll),
        Characteristic("energy", 15, 100, "%", CharacteristicType.Energy),
        Characteristic("compass", 15, 100, "", CharacteristicType.Compass),
        Characteristic("weather", 15, 100, "", CharacteristicType.Weather),
        Characteristic("beep", 15, 100, "", CharacteristicType.Beep),
        Characteristic("torch", 15, 100, "", CharacteristicType.Torch),
        Characteristic("strobe", 15, 100, "", CharacteristicType.Strobe),
        Characteristic("lights", 15, 100, "", CharacteristicType.Lights),
        Characteristic("lock", 15, 100, "", CharacteristicType.Lock),
        Characteristic("turnoff", 15, 100, "", CharacteristicType.TurnOff)
    )

    private val timer = Timer()

    init {
        _characteristicList.value = list
    }

    private fun update() {
        val random = (0..Int.MAX_VALUE).random()
        Log.d("DemoAdapter", "timer update random $random")
        list.forEach { characteristic ->
            characteristic.value = (random % characteristic.maxValue)
        }
        updateLD()
    }

    override fun getCharacteristics(): LiveData<List<Characteristic>> {
        startTimer()
        return _characteristicList
    }

    private fun updateLD() = _characteristicList.postValue(list)

    private fun startTimer() {
        val task = object : TimerTask() {
            override fun run() {
                update()
            }

        }
        timer.scheduleAtFixedRate(task, UPDATE_REQUEST_DELAY, UPDATE_REQUEST_PERIOD)
        Log.d("DemoAdapter", "timer.start()")
    }

    fun stopTimer() {
        timer.cancel()
        Log.d("DemoAdapter", "timer.cancel()")
    }

    companion object {
        const val UPDATE_REQUEST_DELAY = 200L
        const val UPDATE_REQUEST_PERIOD = 1000L
    }

}
