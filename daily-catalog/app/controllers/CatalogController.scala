package controllers

import javax.inject.Inject

import io.swagger.annotations.{ApiResponse, ApiResponses}
import play.api.mvc.InjectedController
import services.ItemService
import play.api.libs.json._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

class CatalogController @Inject()(itemService: ItemService) extends InjectedController {


  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid Request")))
  def ping = Action { implicit request =>
    Ok("""{"msg": "pong from daily catalog "}""").as("application/json")
  }

  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid Request")))
  def items = Action { implicit request =>
    val items = itemService.listAllItems(0, 10)
    Ok(Json.prettyPrint(Json.obj("items" -> items)))
  }

  @ApiResponses(Array(new ApiResponse(code = 400, message = "Invalid Request")))
  def item(itemName:String) = Action { implicit request =>
    val item = itemService.fetchItemByName(itemName)
    Ok(Json.prettyPrint(Json.obj("item" -> item)))
  }
}
