# Reproducer for Akka Streams and HTTP 2.0-M2

*Setup*

* `sbt stage`
* Start `jvisualvm` in a different terminal


*Low level client*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff low-level-client`
* Profile the low level client using `jvisualvm`
* The heap usage will increase until OOM is encountered 

*Request level client*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff request-level-client`
* Profile the requestlevel client using `jvisualvm`
* The heap usage will increase until OOM is encountered 

*Stub client*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff stub-client`
* Profile the low level client using `jvisualvm`
* The heap exhibits saw-tooth pattern 

