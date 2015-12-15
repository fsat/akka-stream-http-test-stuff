package test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.Future

object StubClient {
  def run: Unit = {
    val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer.create(actorSystem)
    import actorSystem.dispatcher

    def post(message: String): Future[HttpResponse] =
      Future.failed(new RuntimeException("DUMMY"))

    Source.actorPublisher[String](MessageGenerator.props)
      .mapAsync(1) { message =>
        post(message)
          .map(Right(_))
          .recover {
            case e => Left(e)
          }
      }
      .toMat(Sink.foreach  {
        case Right(httpResponse) =>
        case Left(exception) =>
      })(Keep.left)
      .run()
  }

}
