package www.androiddarknessbot.dashboard.domain.entity

import www.androiddarknessbot.dashboard.domain.CharacteristicType

data class Characteristic(
    val title: String,
    var value: Int,
    val maxValue: Int,
    val dimension: String,
    val type: CharacteristicType
)
