package com.example.mycashgantt

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.control.Alert.AlertType
import javafx.scene.layout.GridPane


class HelloController {
    @FXML
    var Jul: CheckBox? = null

    @FXML
    var Oct: CheckBox? = null

    @FXML
    var Apr: CheckBox? = null

    @FXML
    var Feb: CheckBox? = null

    @FXML
    var Jun: CheckBox? = null

    @FXML
    var Dec: CheckBox? = null

    @FXML
    var May: CheckBox? = null

    @FXML
    var endDate: DatePicker? = null

    @FXML
    var itemName: TextField? = null

    @FXML
    var Aug: CheckBox? = null

    @FXML
    var selectedMonth: GridPane? = null

    @FXML
    var Nov: CheckBox? = null

    @FXML
    var price: TextField? = null

    @FXML
    var Jan: CheckBox? = null

    @FXML
    var startDate: DatePicker? = null

    @FXML
    var addItemButton: Button? = null

    @FXML
    var Mar: CheckBox? = null

    @FXML
    var Sep: CheckBox? = null

    @FXML
    fun onAddBtnClicked(event: ActionEvent?) {

        var selectedMonthList: MutableList<CustomMonth> = getSelectedMonth()
        val item = getItem(selectedMonthList)

        showDataEntered(item)
    }

    private fun showDataEntered(item: Item) {
        for (customItem in item.costMonths) {
            println(customItem)
        }
    }

    private fun getSelectedMonth(): MutableList<CustomMonth> {
        val MonthList = mutableListOf(Jan, Feb, Mar, Apr, May, Jun, Jul, Aug, Sep, Oct, Nov, Dec)
        val selectedMonthList: MutableList<CustomMonth> = MonthList.mapNotNull { it?.toCustomMonth(it) }.toMutableList()
//        selectedMonthList.removeAll(listOf(null))
        return selectedMonthList
    }


    private fun getItem(selectedMonthList: MutableList<CustomMonth>): Item {
        return try {
            Item(
                itemName!!.text.ifEmpty { throw IllegalArgumentException("Empty String") },
                price!!.text.toDouble(),
                startDate!!.value,
                endDate!!.value,
                selectedMonthList
            )
        } catch (e: NumberFormatException) {
            e.printStackTrace()
            showErrorDialog(e)
            throw e
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            showErrorDialog(e)
            throw e
        }
        /*            itemName?.text?.let {
                        price?.text?.let { it1 ->
                            it1.toDoubleOrNull()?.let { it2 ->
                                Item(
                                    it, it2,
                                    startDate?.value!!, endDate?.value!!,
                                    selectedMonthList
                                )
                            }
                        }
                    }*/
    }

    private fun showErrorDialog(e: Exception) {
        val alert = Alert(AlertType.INFORMATION)
        alert.title = "Information Dialog"
        alert.headerText = "Look, an Information Dialog"
        alert.contentText = e.message.toString()

        alert.showAndWait()
    }

}
