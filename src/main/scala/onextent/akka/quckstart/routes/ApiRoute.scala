package onextent.akka.quckstart.routes

import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.{Directives, Route}
import ch.megard.akka.http.cors.scaladsl.CorsDirectives._
import com.typesafe.scalalogging.LazyLogging
import onextent.akka.quckstart.ErrorSupport
import onextent.akka.quckstart.models.JsonSupport

object ApiRoute
    extends JsonSupport
    with LazyLogging
    with Directives
    with ErrorSupport {

  def apply: Route =
    path(urlpath) {
      logRequest(urlpath) {
        handleErrors {
          cors(corsSettings) {
            get {
              logger.debug(s"get $urlpath")
              complete(
                HttpEntity(ContentTypes.`application/json`,
                           "{\"msg\": \"Say hello to Streams Quickstart\"}\n"))
            }
          }
        }
      }
    }
}
