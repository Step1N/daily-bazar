package services

import javax.inject.{Inject, Singleton}

import dataops.ExcelDataOperation
import models.Item

class ItemService @Inject()(fileHelper: ExcelDataOperation) {

  def listAllItems(startIndex: Int, pageSize: Int):List[Item] = {
    fileHelper.loadItemFromFile().toList
  }

  def fetchItemByName(itemName: String):Item = {
    fileHelper.findItemByName(itemName)
  }

}
