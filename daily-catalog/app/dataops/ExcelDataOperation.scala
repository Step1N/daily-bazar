package dataops

import java.io.{File, FileOutputStream}
import javax.inject.Singleton

import models.Item
import org.apache.poi.ss.usermodel.{DataFormatter, WorkbookFactory}
import java.io.FileInputStream

import scala.collection.JavaConversions._

@Singleton
class ExcelDataOperation {
  val path = "./InventoryWB.xlsx"

  def loadItemFromFile() = {
    val workbook = WorkbookFactory.create(new File(path))
    val formatter = new DataFormatter()
    for {
      sheet <- workbook
      row <- sheet.drop(1)
    } yield {
      Item(formatter.formatCellValue(row.getCell(0)).toInt, formatter.formatCellValue(row.getCell(1)), formatter.formatCellValue(row.getCell(2)), formatter.formatCellValue(row.getCell(3)))
    }
  }

  def addItemToInventory(item: Item) = {
    val fsIP = new FileInputStream(new File(path))
    val workbook = WorkbookFactory.create(fsIP)
    val sheet = workbook.getSheetAt(0)
    val rowNumber = workbook.getSheetAt(0).getLastRowNum
    val newRow = sheet.createRow(rowNumber + 1)
    newRow.createCell(0).setCellValue(item.itemNumber)
    newRow.createCell(1).setCellValue(item.itemName)
    newRow.createCell(2).setCellValue(item.itemQty)
    newRow.createCell(3).setCellValue(item.itemDescription)
    fsIP.close()
    val fos = new FileOutputStream(new File(path))
    workbook.write(fos)
    workbook.close()
    fos.close()
    item
  }

  def updateItemToInventory(item: Item) = {
    val fsIP = new FileInputStream(new File(path))
    val workbook = WorkbookFactory.create(fsIP)
    val sheet = workbook.getSheetAt(0)
    val rowNumber = workbook.getSheetAt(0).getLastRowNum
    val newRow = sheet.getRow(item.itemNumber)
    newRow.createCell(0).setCellValue(item.itemNumber)
    newRow.createCell(1).setCellValue(item.itemName)
    newRow.createCell(2).setCellValue(item.itemQty)
    newRow.createCell(3).setCellValue(item.itemDescription)
    fsIP.close()
    val fos = new FileOutputStream(new File(path))
    workbook.write(fos)
    workbook.close()
    fos.close()
    item
  }

  def deleteItemFromInventory(item: Item) = {
    val fsIP = new FileInputStream(new File(path))
    val workbook = WorkbookFactory.create(fsIP)
    val sheet = workbook.getSheetAt(0)
    val deleteRow = sheet.getRow(item.itemNumber)
    sheet.removeRow(deleteRow)
    fsIP.close()
    val fos = new FileOutputStream(new File(path))
    workbook.write(fos)
    workbook.close()
    fos.close()
    item
  }



  def findItemByName(itemName: String) = {
    val items = loadItemFromFile()
    items.filter(_.itemName.equals(itemName)).toList.get(0)
  }


  def main(args: Array[String]): Unit = {
    val newItem = Item(2, "Oil", "2l", "Mustard Oil")
    val foundItem = findItemByName("Oil")
    println("foundItem", foundItem)


  }
}