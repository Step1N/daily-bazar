package models

import play.api.libs.json._

case class Item(itemNumber: Int, itemName: String, itemQty: String, itemDescription: String)

object Item {
  implicit val item = Json.format[Item]
}
