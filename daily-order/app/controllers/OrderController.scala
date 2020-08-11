package controllers

import javax.inject.Inject

import io.swagger.annotations.{ApiResponse, ApiResponses}
import play.api.libs.json._
import play.api.mvc.InjectedController
import models.{Order}
import services.OrderService


class OrderController @Inject()(orderService: OrderService) extends InjectedController {

  def ping = Action { implicit request =>
    Ok("""{"msg": "pong from daily order "}""").as("application/json")
  }

  @ApiResponses(Array(new ApiResponse(code = 500, message = "Internal Error"), new ApiResponse(code = 400, message = "Invalid Payload")))
  def placeOrder = Action(parse.json(maxLength = 20 * 1000)) { implicit request =>
    request.body.validate[Order].fold(
      errors => BadRequest(errors.mkString),
      order => {
        val ord = orderService.createOrder(order)
        Ok(Json.prettyPrint(Json.obj("order placed" -> ord)))
      }
    )
  }

}
