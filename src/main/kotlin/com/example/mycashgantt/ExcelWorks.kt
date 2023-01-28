package com.example.mycashgantt

import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.ss.usermodel.WorkbookFactory
import java.io.FileInputStream
import java.io.FileOutputStream

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

        val sheet = workbook.getSheetAt(0)
        var lastRow = sheet.lastRowNum


        for (item in dataList) {
            val row = sheet.createRow(++lastRow)
            // saving item pojo
            row.createCell(0).setCellValue(item.name)
            row.createCell(1).setCellValue(item.price)
            row.createCell(2).setCellValue(item.startDate)
            row.createCell(3).setCellValue(item.endDate)

            item.costMonths.forEachIndexed { index, month ->
                row.createCell(row.lastCellNum.toInt()).setCellValue(month.name)
            }
        }
        // close file inputstream before using fileoutput stream
        fileInputStream.close()
        val out = FileOutputStream(fileName)
        workbook.write(out)
        out.close()
        workbook.close()
        println("excel succedded succsseefully")
    } catch (e: java.lang.Exception) {
        e.printStackTrace()
    }
//    out.flush()
//    workbook.close()
}



