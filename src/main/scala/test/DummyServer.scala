package test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer

object DummyServer {
  def run: Unit = {
    implicit val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer.create(actorSystem)
    import actorSystem.dispatcher

    val route = path("test") {
      post {
        complete("ok")
      }
    }

    Http(actorSystem).bindAndHandle(route, "localhost", 7777)
  }
}
