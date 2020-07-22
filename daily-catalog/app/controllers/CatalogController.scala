package controllers

import play.api.mvc.{Action, Controller}

object CatalogController extends Controller{

  def ping = Action { implicit request =>
    Ok("""{"msg": "pong from daily catalog "}""").as("application/json")
  }
}
