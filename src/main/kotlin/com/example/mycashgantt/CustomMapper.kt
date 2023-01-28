package com.example.mycashgantt


import javafx.scene.control.CheckBox

fun CheckBox.toCustomMonth( checkBox: CheckBox): CustomMonth? {
    if (checkBox.isSelected){
        return when (checkBox.text){
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