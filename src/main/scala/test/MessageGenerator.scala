package test

import java.text.SimpleDateFormat
import java.util.Date

import akka.actor.{Props, Actor}
import akka.stream.actor.ActorPublisher
import test.MessageGenerator.Tick
import scala.concurrent.duration._

object MessageGenerator {
  def props = Props(new MessageGenerator)

  case object Tick
}

class MessageGenerator extends Actor with ActorPublisher[String] {
  import context.dispatcher

  override def preStart(): Unit = {
    context.system.scheduler.schedule(1 second, 10 milliseconds, self, Tick)
  }

  override def receive: Receive = {
    case Tick =>
      if (isActive && totalDemand > 0)
        onNext(timestampNow)
  }

  private def timestampNow = {
    new SimpleDateFormat("yyyy-MM-dd HH:MM:ss").format(new Date(System.currentTimeMillis()))
  }
}
