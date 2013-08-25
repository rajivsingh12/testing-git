package my.foo.patterns.creational

import akka.event.Logging
import akka.actor._
import akka.pattern._
import akka.routing._
import akka.dispatch._
import akka.util._
import akka.util.duration._

object ObjectPoolTest extends App {
  implicit val timeout = Timeout(5 seconds)
  val system = ActorSystem("MySystem")
  val poolManager = system.actorOf(Props[PoolManager], name = "poolManager")
  system.scheduler.schedule(1 seconds, 5 seconds, poolManager, StartWork())
}

case class StartWork
case class RouterPing
case class WorkDone

class PoolManager extends Actor {
  implicit val timeout = Timeout(5 seconds)
  val log = Logging(context.system, this)
  implicit val actorSystem = context.system

  val router = context.actorOf(Props[RouteeActor].withDispatcher("bounded-mailbox-dispatcher").withRouter(RoundRobinRouter(2)), "router")

  def receive = {
    case StartWork() =>
      log.debug("Received StartWork..")
      val listFutures = (1 to 1000).map { i =>
        router ? RouterPing()
      }
      val futures = Future.sequence(listFutures.toList)

      //Waiting for response, tail logs to see same thread logging the messages in this actor.
      //Await.result(futures, timeout.duration)
      //log.debug("Received all WorkDones")

      //Reacting on response, 
      //checkout logs to see different threads logging messages in this actor.  
      futures.onComplete {
        case Right(x) => log.debug("Received all WorkDones")
      }

    case _ => log.error("Fuck you for messing with me")
  }
}

class RouteeActor extends Actor {
  val log = Logging(context.system, this)
  def receive = {
    case RouterPing() =>
      sender ! WorkDone()
    case _ => log.error("Fuck you man!!")
  }
}
