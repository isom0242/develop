package controllers


import play.api.mvc._

class Traceroute extends Controller {

  def index = Action {
    Ok(views.html.traceroute())
  }
}
