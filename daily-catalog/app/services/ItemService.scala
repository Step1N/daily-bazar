package services

import javax.inject.{Inject, Singleton}

import dataops.ExcelDataOperation
import eventSender.CatalogMsgProducer
import models.Item

class ItemService @Inject()(fileHelper: ExcelDataOperation, eventProducer:CatalogMsgProducer) {

  def listAllItems(startIndex: Int, pageSize: Int):List[Item] = {
    fileHelper.loadItemFromFile().toList
  }

  def fetchItemByName(itemName: String):Item = {
    fileHelper.findItemByName(itemName)
  }

  def addItemToCatalog(itemName: Item):Item = {
    val item  = fileHelper.addItemToInventory(itemName)
    eventProducer.informToOthers("test", "addedItemToCatalog")
    item
  }

}
