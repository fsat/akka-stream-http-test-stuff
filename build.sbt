name := "akka-stream-http-test-stuff"

version := "0.0.1"

scalaVersion := Version.scala

libraryDependencies ++= List(
  Library.akkaActor,
  Library.akkaHttp,
  Library.akkaStream
)


enablePlugins(JavaAppPackaging)
