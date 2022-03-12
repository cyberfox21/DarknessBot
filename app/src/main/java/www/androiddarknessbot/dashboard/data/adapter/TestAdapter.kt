package www.androiddarknessbot.dashboard.data.adapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import www.androiddarknessbot.dashboard.domain.CharacteristicType
import www.androiddarknessbot.dashboard.domain.adapter.BaseAdapter
import www.androiddarknessbot.dashboard.domain.entity.Characteristic

class TestAdapter : BaseAdapter() {

//    val characteristics = listOf<Characteristic>()
//
//    fun update() {
//        characteristics.forEach { characteristic ->
//            characteristic.value  = when (characteristic.type) {
//                CharacteristicType.Speed,
//                CharacteristicType.SingleMaxSpeed,
//                CharacteristicType.Temperature,
//                CharacteristicType.Battery -> DashboardItemType.PROGRESS
////        CharacteristicType.avgSpeed -> TODO()
////        CharacteristicType.totalRuntime -> TODO()
////        CharacteristicType.singleRuntime -> TODO()
//                CharacteristicType.TotalMileage,
//                CharacteristicType.SingleMileage -> DashboardItemType.TEXT
//
////        CharacteristicType.remainingMileage -> TODO()
////        CharacteristicType.power -> TODO()
////        CharacteristicType.current -> TODO()
////        CharacteristicType.voltage -> TODO()
////        CharacteristicType.pitch -> TODO()
////        CharacteristicType.roll -> TODO()
////        CharacteristicType.energy -> TODO()
//                CharascacteristicType.Compass -> DashboardItemType.IMAGE
//                CharacteristicType.Weather -> DashboardItemType.IMAGE
//                CharacteristicType.Beep -> DashboardItemType.IMAGE
//                CharacteristicType.Torch -> DashboardItemType.IMAGE
////        CharacteristicType.strobe -> TODO()
//                CharacteristicType.Lights -> DashboardItemType.IMAGE
//                CharacteristicType.Lock -> DashboardItemType.IMAGE
//                CharacteristicType.TurnOff -> DashboardItemType.IMAGE
////        CharacteristicType.unknown -> TODO()
//            }
//        }
//    }

    override fun getCharacteristics(): LiveData<List<Characteristic>> {

        val list = listOf(
            Characteristic("totalMileage", 1000, 2000, "km", CharacteristicType.TotalMileage),
            Characteristic("battery", 15, 100, "%", CharacteristicType.Battery),
            Characteristic("speed", 70, 100, "km/h", CharacteristicType.Speed),
            Characteristic("temperature", 43, 60, "'C", CharacteristicType.Temperature),
            Characteristic("singleMileage", 104, 2000, "km", CharacteristicType.SingleMileage),
            Characteristic("singleMaxSpeed", 40, 80, "km/h", CharacteristicType.SingleMaxSpeed),
            Characteristic("totalMileage", 106, 2000, "km", CharacteristicType.TotalMileage),
            Characteristic("speed", 56, 200, "km/h", CharacteristicType.Speed),
        )

        return MutableLiveData<List<Characteristic>>().apply { value = list }
    }

}