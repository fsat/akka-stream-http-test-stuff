package test

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model._
import akka.stream.ActorMaterializer
import akka.stream.scaladsl.{Keep, Sink, Source}

import scala.concurrent.Future

object RequestLevelClient {
  def run: Unit = {
    val actorSystem = ActorSystem()
    implicit val materializer = ActorMaterializer.create(actorSystem)
    import actorSystem.dispatcher

    val http = Http(actorSystem)

    def post(message: String): Future[HttpResponse] =
      http.singleRequest(HttpRequest(HttpMethods.POST, "http://localhost:9200/test", List.empty, HttpEntity(ContentType(MediaTypes.`text/plain`), message)))

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
