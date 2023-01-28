package com.example.mycashgantt


import javafx.scene.control.CheckBox

fun CheckBox.toCustomMonth(checkBox: CheckBox): CustomMonth? {
    if (checkBox.isSelected) {
        return when (checkBox.text) {
            "Jan" -> CustomMonth.Jan
            "Feb" -> CustomMonth.Feb
            "Mar" -> CustomMonth.Mar
            "Apr" -> CustomMonth.Apr
            "May" -> CustomMonth.May
            "Jun" -> CustomMonth.Jun
            "Jul" -> CustomMonth.Jul
            "Aug" -> CustomMonth.Aug
            "Sep" -> CustomMonth.Sep
            "Oct" -> CustomMonth.Oct
            "Nov" -> CustomMonth.Nov
            "Dec" -> CustomMonth.Dec
            else -> CustomMonth.Empty

        }
    }
    return null
}

fun CustomMonth.toCounter(customMonth: CustomMonth): Int {
    return when (customMonth) {
        CustomMonth.Jan -> 0
        CustomMonth.Feb -> 1
        CustomMonth.Mar -> 2
        CustomMonth.Apr -> 3
        CustomMonth.May -> 4
        CustomMonth.Jun -> 5
        CustomMonth.Jul -> 6
        CustomMonth.Aug -> 7
        CustomMonth.Sep -> 8
        CustomMonth.Oct -> 9
        CustomMonth.Nov -> 10
        CustomMonth.Dec -> 11
        else -> 0
    }
}