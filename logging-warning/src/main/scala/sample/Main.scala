package sample

import akka.actor.{ActorLogging, ActorSystem, Props}
import akka.persistence.PersistentActor

case object Add
case object Get
case class Transaction(diff: Int)
case class State(sum: Int)
case object SaveSnapshot

class SampleActor extends PersistentActor with ActorLogging {
  override def persistenceId: String = "sample-warning"

  var sum = 0

  override def receiveRecover: Receive = {
    case Transaction(diff) => sum += diff
    case State(s) => sum = s
    case x => log.info(x.toString)
  }

  override def receiveCommand: Receive = {
    case Add => persist(Transaction(1)) { _ => sum += 1 }
    case Get => println(sum)
    case SaveSnapshot => saveSnapshot(State(sum))
    case x => log.info(x.toString)
  }
}

object Main {
  val system = ActorSystem("sample-warning")

  val actor = system.actorOf(Props[SampleActor])
}
