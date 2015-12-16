package test

object Main {
  def main(args: Array[String]): Unit = {
    args match {
      case Array("low-level-client") =>
        LowLevelClient.run
      case Array("request-level-client") =>
        RequestLevelClient.run
      case Array("stub-client") =>
        StubClient.run
      case _ =>
        sys.error("Need to specify either low-level-client, request-level-client, stub-client")
    }

  }
}
