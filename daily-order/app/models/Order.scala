package models

import play.api.libs.json.Json


sealed case class Description(st: String)

case class Order(orderId: Int, itemId: Int, itemQty: String, shipDate: Option[String], status: String, description: Option[Description], complete: Boolean)

object Description {
  implicit val description = Json.format[Description]
}

object Order {
  implicit val order = Json.format[Order]
}