# Reproducer for Akka Streams and HTTP 2.0-M2

*Setup*

* `sbt stage`
* Start `jvisualvm` in a different terminal


*Low level client*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff low-level-client`
* Profile the low level client using `jvisualvm`
* If the Dummy server is not running, the heap usage will increase until OOM is encountered 
* This does not happen if Dummy server is running 

*Request level client*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff request-level-client`
* Profile the requestlevel client using `jvisualvm`
* If the Dummy server is not running, the heap usage will increase until OOM is encountered 
* This does not happen if Dummy server is running 

*Low level client - without mapAsync*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff low-level-flat-client`
* Profile the low level client using `jvisualvm`
* The heap usage does not seem to increase regardless Dummy server is running 


*Dummy server*

* Execute `./target/universal/stage/bin/akka-stream-http-test-stuff server`
* Accepts HTTP Post on `http://localhost:7777/test` - replies with `text/plain` `Ok`