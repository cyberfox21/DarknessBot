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
        val type =
            when (characteristic.type) {

                CharacteristicType.Speed,
                CharacteristicType.SingleMaxSpeed,
                CharacteristicType.AvgSpeed,
                CharacteristicType.Temperature,
                CharacteristicType.Battery,
                CharacteristicType.Power,
                CharacteristicType.Current,
                CharacteristicType.Voltage -> DashboardItemType.PROGRESS

                CharacteristicType.TotalRuntime,
                CharacteristicType.SingleRuntime,
                CharacteristicType.RemainingMileage,
                CharacteristicType.TotalMileage,
                CharacteristicType.SingleMileage -> DashboardItemType.TEXT

                CharacteristicType.Pitch,
                CharacteristicType.Roll,
                CharacteristicType.Energy,
                CharacteristicType.Compass,
                CharacteristicType.Weather,
                CharacteristicType.Beep,
                CharacteristicType.Torch,
                CharacteristicType.Strobe,
                CharacteristicType.Lights,
                CharacteristicType.Lock,
                CharacteristicType.TurnOff -> DashboardItemType.IMAGE

                CharacteristicType.Unknown ->
                    throw RuntimeException("Unknown characteristic type ${characteristic.type}")
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
        else -> R.drawable.ic_empty
    }
}