import sbt._
import sbt.Resolver.bintrayRepo

object Version {
  val akka                   = "2.3.11"
  val akkaHttp               = "2.0-M2"
  val akkaStream             = "2.0-M2"
  val scala                  = "2.11.7"
}

object Library {
  val akkaActor               = "com.typesafe.akka"      %% "akka-actor"                     % Version.akka
  val akkaHttp                = "com.typesafe.akka"      %% "akka-http-experimental"         % Version.akkaHttp
  val akkaStream              = "com.typesafe.akka"      %% "akka-stream-experimental"       % Version.akkaStream
  val akkaTestkit             = "com.typesafe.akka"      %% "akka-testkit"                   % Version.akka
}

object Resolver {
}
