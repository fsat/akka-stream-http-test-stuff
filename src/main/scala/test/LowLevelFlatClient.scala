package test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.Future

object LowLevelFlatClient {
  def run: Unit = {
    val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer.create(actorSystem)
    import actorSystem.dispatcher

    val httpConnection = Http(actorSystem).outgoingConnection("localhost", 7777)

    Source.actorPublisher[String](MessageGenerator.props)
      .map(message => HttpRequest(HttpMethods.POST, "/test", List.empty, HttpEntity(ContentType(MediaTypes.`text/plain`), message)))
      .via(httpConnection)
      .map(Right(_))
      .recover {
        case e => Left(e)
      }
      .toMat(Sink.foreach  {
        case Right(httpResponse) =>
        case Left(exception) =>
      })(Keep.left)
      .run()
  }

}
