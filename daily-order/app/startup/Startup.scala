package startup

import java.util
import java.util.Properties
import javax.inject.{Inject, Named}

import scala.concurrent.duration._
import akka.actor.{ActorRef, ActorSystem}
import eventReciever.OrderMsgConsumer
import org.apache.kafka.clients.consumer.KafkaConsumer

import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.Random

class Startup @Inject()(system: ActorSystem,
                        orderConsumer: OrderMsgConsumer) {

  val props = new Properties()
  props.put("bootstrap.servers", "localhost:9092")
  props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")
  props.put("auto.offset.reset", "latest")
  props.put("group.id", "order-consumer")
  val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)
  consumer.subscribe(util.Arrays.asList("test"))

  system.scheduler.scheduleOnce(Random.nextInt(3) seconds)(orderConsumer.schedule(consumer))
  println("I am up now", consumer)

}
