package startup

import javax.inject.{Inject, Named}

import akka.actor.{ActorRef, ActorSystem}
import eventReciever.OrderMsgConsumer

class Startup @Inject()(system: ActorSystem, orderConsumer: OrderMsgConsumer) {
  orderConsumer.consumeFromKafka("test")
}
