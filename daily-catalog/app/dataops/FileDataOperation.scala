package dataops

import java.nio.file.{Files, Paths, StandardOpenOption}
import java.nio.charset.StandardCharsets
import java.util.stream.Stream
import javax.inject.Singleton

import models.Item

@Singleton
class FileDataOperation {
  val path = "./ItemDataSet.csv"

  def loadItemFromFile() = {
    val stream: Stream[String] = Files.lines(Paths.get(path))
    stream.toArray().drop(1).map { e =>
      e.toString.split(",") match {
        case Array(itemNumber, itemName, itemQty, itemDescription) => Item(itemNumber.toInt, itemName, itemQty, itemDescription)
      }
    }
  }

  def allItemList() = {
    loadItemFromFile()
  }

  def insertItemInFile(item: Item) = {
    val line = ("\n" + item.itemNumber.toString + "," + item.itemName + "," + item.itemQty + "," + item.itemDescription)
    val input: Array[Byte] = Array(line).flatMap(_.getBytes(StandardCharsets.UTF_8))
    Files.write(Paths.get(path), input, StandardOpenOption.APPEND)
  }

  def updateItemInFile(item: Item) = {
    val lineNumber = item.itemNumber;
    val newLine = (item.itemNumber.toString + "," + item.itemName + "," + item.itemQty + "," + item.itemDescription)
    val lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8)
    lines.set(lineNumber, newLine)
    Files.write(Paths.get(path), lines, StandardCharsets.UTF_8)
  }

  def deleteItemFromFile(itemNumber: Int) = {
    val lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8)
    lines.remove(itemNumber)
    Files.write(Paths.get(path), lines, StandardCharsets.UTF_8)
  }
}


