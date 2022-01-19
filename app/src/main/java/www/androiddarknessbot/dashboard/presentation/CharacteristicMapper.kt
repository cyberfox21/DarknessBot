package www.androiddarknessbot.dashboard.presentation

import www.androiddarknessbot.R
import www.androiddarknessbot.dashboard.domain.CharacteristicType
import www.androiddarknessbot.dashboard.domain.entity.Characteristic
import www.androiddarknessbot.dashboard.presentation.recycler.DashboardItemType
import www.androiddarknessbot.dashboard.presentation.recycler.delegate.DelegateItem
import www.androiddarknessbot.dashboard.presentation.recycler.item.ImageItem
import www.androiddarknessbot.dashboard.presentation.recycler.item.ProgressItem
import www.androiddarknessbot.dashboard.presentation.recycler.item.TextItem

class CharacteristicMapper {
    fun mapToDashboardItemType(characteristics: List<Characteristic>): List<DelegateItem> =
        characteristics.map { mapToDelegateItem(it) }

    private fun mapToDelegateItem(characteristic: Characteristic): DelegateItem {
        val type = when (characteristic.type) {
            CharacteristicType.Speed,
            CharacteristicType.SingleMaxSpeed,
            CharacteristicType.Temperature,
            CharacteristicType.Battery -> DashboardItemType.PROGRESS
//        CharacteristicType.avgSpeed -> TODO()
//        CharacteristicType.totalRuntime -> TODO()
//        CharacteristicType.singleRuntime -> TODO()
            CharacteristicType.TotalMileage,
            CharacteristicType.SingleMileage -> DashboardItemType.TEXT

//        CharacteristicType.remainingMileage -> TODO()
//        CharacteristicType.power -> TODO()
//        CharacteristicType.current -> TODO()
//        CharacteristicType.voltage -> TODO()
//        CharacteristicType.pitch -> TODO()
//        CharacteristicType.roll -> TODO()
//        CharacteristicType.energy -> TODO()
            CharacteristicType.Compass -> DashboardItemType.IMAGE
            CharacteristicType.Weather -> DashboardItemType.IMAGE
            CharacteristicType.Beep -> DashboardItemType.IMAGE
            CharacteristicType.Torch -> DashboardItemType.IMAGE
//        CharacteristicType.strobe -> TODO()
            CharacteristicType.Lights -> DashboardItemType.IMAGE
            CharacteristicType.Lock -> DashboardItemType.IMAGE
            CharacteristicType.TurnOff -> DashboardItemType.IMAGE
//        CharacteristicType.unknown -> TODO()
        }
        return when (type) {
            DashboardItemType.PROGRESS -> createProgressItem(characteristic)
            DashboardItemType.IMAGE -> createImageItem(characteristic)
            DashboardItemType.TEXT -> createTextItem(characteristic)
        }
    }

    private fun createProgressItem(characteristic: Characteristic) = ProgressItem(
        id = 0,
        title = characteristic.title,
        //progress = characteristic.value,
        //progress = getProgress(characteristic.value, characteristic.maxValue),
        max = characteristic.maxValue,
        value = characteristic.value,
        dimension = characteristic.dimension
    )

    private fun createImageItem(characteristic: Characteristic) = ImageItem(
        id = 0,
        title = characteristic.title,
        image = getCharacteristicImageByType(characteristic.type)
    )

    private fun createTextItem(characteristic: Characteristic) = TextItem(
        id = 0,
        title = characteristic.title,
        value = characteristic.value,
        dimension = characteristic.dimension
    )

    private fun getCharacteristicImageByType(type: CharacteristicType): Int = when (type) {
        CharacteristicType.Lights -> R.drawable.bluetooth_low
        else -> R.drawable.bluetooth_low
    }

//    private fun getProgress(value: Int, maxValue: Int): Int {
//        val fraction = (value.toDouble() / maxValue)
//        val result = (fraction * PERCENT).toInt()
//        return result
//    }
//
//    companion object {
//        const val PERCENT = 100
//    }
}