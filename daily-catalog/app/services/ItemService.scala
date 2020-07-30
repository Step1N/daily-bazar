package services

import javax.inject.{Inject, Singleton}

import dataops.FileDataOperation
import models.Item

class ItemService @Inject()(fileHelper: FileDataOperation) {

  def listAllItems(startIndex: Int, pageSize: Int):List[Item] = {
    fileHelper.allItemList().toList
  }

}
