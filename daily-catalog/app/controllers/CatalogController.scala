package controllers

import javax.inject.Inject

import io.swagger.annotations.{ApiResponse, ApiResponses}
import models.Item
import play.api.mvc.InjectedController
import services.ItemService
import play.api.libs.json._

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CatalogController @Inject()(itemService: ItemService) extends InjectedController {

  val MB_TO_BYTES = 1000 * 1000

  @ApiResponse(code = 400, message = "Invalid Request")
  def ping = Action { implicit request =>
    Ok("""{"msg": "pong from daily catalog "}""").as("application/json")
  }

  @ApiResponse(code = 400, message = "Invalid Request")
  def items = Action { implicit request =>
    val items = itemService.listAllItems(0, 10)
    Ok(Json.prettyPrint(Json.obj("items" -> items)))
  }

  @ApiResponse(code = 400, message = "Invalid Request")
  def item(itemName: String) = Action { implicit request =>
    val item = itemService.fetchItemByName(itemName)
    Ok(Json.prettyPrint(Json.obj("item" -> item)))
  }

  @ApiResponses(Array(new ApiResponse(code = 500, message = "Invalid Payload"), new ApiResponse(code = 400, message = "Invalid Payload")))
  def addItem() = Action(parse.json(maxLength = 20 * MB_TO_BYTES)) { implicit request =>
    request.body.validate[Item].fold(
      errors => BadRequest(errors.mkString),
      item => {
        val it = itemService.addItemToCatalog(item)
        Ok(Json.prettyPrint(Json.obj("catalog item saved" -> it)))
      }
    )
  }

}
