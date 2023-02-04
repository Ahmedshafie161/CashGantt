package com.example.mycashgantt

import kotlinx.coroutines.flow.MutableSharedFlow
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Sheet
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

//val sharedFlow = MutableSharedFlow<String>()
val sharedFlow = MutableSharedFlow<String?>()

public fun readExcel(): List<List<Any>> {
    val file = FileInputStream(FILE_PATH)
    val workbook = WorkbookFactory.create(file)
    val sheet = workbook.getSheetAt(0)
    val data = mutableListOf<List<Any>>()
    println("last row num ${sheet.lastRowNum}")
    for (i in 0..sheet.lastRowNum) {
        println("i is : $i")
        val row = sheet.getRow(i)
        val rowData = mutableListOf<Any>()
        for (j in 0 until row.lastCellNum) {
            println("last cell num ${row.lastCellNum}")

            println("j is : $j")

            val cell = row.getCell(j)
            when (cell.cellType) {
                CellType.STRING -> rowData.add(cell.stringCellValue)
                CellType.NUMERIC -> rowData.add(cell.numericCellValue)
                CellType.BOOLEAN -> rowData.add(cell.booleanCellValue)
                else -> {}
            }
            println(rowData.joinToString("*"))
        }
        data.add(rowData)
    }
    file.close()
    return data
}

fun writeExcelFile(fileName: String, dataList: List<Item>) {
    var workbook: Workbook
    try {
        val fileInputStream = FileInputStream(fileName)
        workbook = WorkbookFactory.create(fileInputStream)



        saveDataInExcel(dataList, workbook)
        fileInputStream.close()
        val out = FileOutputStream(fileName)
        workbook.write(out)
        out.close()
        workbook.close()

        println("excel succedded succsseefully")
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
}

private fun saveDataInExcel(
    dataList: List<Item>,
    workbook: Workbook
) {
    for (item in dataList) {
        val sheet = workbook.getSheetAt(0)
        var lastRow = sheet.lastRowNum
        val row = sheet.createRow(++lastRow)
        // saving item pojo
        row.createCell(0).setCellValue(item.name)
        row.createCell(1).setCellValue(item.price)

        // dates
        row.createCell(2).setCellValue("${item.startDate.year} ${item.startDate.month} ${item.startDate.dayOfMonth}")
        row.createCell(3).setCellValue("${item.endDate.year} ${item.endDate.month} ${item.endDate.dayOfMonth}")

        // spacing months
        val counter = item.costMonths.map { it.toCounter(it) }
        val intialMonthOrder = row.lastCellNum.toInt() + (item.startDate.year - 2023) * 12
        item.costMonths.forEachIndexed { index, customMonth ->
            val cellOrder = intialMonthOrder + counter.get(index)
            row.createCell(cellOrder)
                .setCellValue(item.price)
            // set value for date header
            sheet.getRow(sheet.firstRowNum).createCell(cellOrder)
                .setCellValue(item.startDate.year.toString() + " " + customMonth.name)
            // add sum
            addSumCulumnToExcel(sheet, cellOrder)
            addAccumSumRowToExcel(sheet)
        }


        /*
                            item.costMonths.forEachIndexed { index, month ->
                                month.toCounter(month)
                                row.createCell(row.lastCellNum.toInt()).setCellValue(month.name)
                            }
                */
    }
}

private fun addSumCulumnToExcel(sheet: Sheet, cellOrder: Int) {
    var sum = 0.0
    for (i in sheet.firstRowNum + 3..sheet.lastRowNum) {
        if (sheet.getRow(i).getCell(cellOrder) != null) {
            sum += sheet.getRow(i).getCell(cellOrder).numericCellValue
        }
    }
    sheet.getRow(2).createCell(cellOrder).setCellValue(sum)
}

private fun addAccumSumRowToExcel(sheet: Sheet) {
    var sum = 0.0
    val acummSumRowIndex = sheet.firstRowNum + 2
    for (i in 4..sheet.getRow(acummSumRowIndex).lastCellNum) {
        if (sheet.getRow(acummSumRowIndex).getCell(i) != null) {
            sum += sheet.getRow(acummSumRowIndex).getCell(i).numericCellValue
            if (sheet.getRow(acummSumRowIndex).getCell(i - 1) != null)
                sum += sheet.getRow(1).getCell(i - 1).numericCellValue
        }
        sheet.getRow(1).createCell(i).setCellValue(sum)
        sum = 0.0
    }
}




