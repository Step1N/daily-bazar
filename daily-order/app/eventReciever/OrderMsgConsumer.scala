package eventReciever

import java.time.Duration
import java.util
import java.util.Properties
import javax.inject.{Inject, Singleton}

import akka.actor.Status.Success
import akka.actor.{Actor, ActorSystem, Cancellable}
import scala.concurrent.duration._
import org.apache.kafka.clients.consumer.KafkaConsumer

import services.OrderService

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


@Singleton
class OrderMsgConsumer @Inject()(orderService: OrderService, system: ActorSystem) {


  private def process(consumer: KafkaConsumer[String, String]): Future[Unit] = {
    val duration = Duration.ofSeconds(1)
    consumer.poll(duration).records("test").forEach {
      e =>
        e.value match {
          case "addedItemToCatalog" =>
            orderService.placeOrder()
          case _ =>
            println("I dont care about this one", e.value)
        }
    }
    Future {
      Success
    }

  }

  def schedule(consumer: KafkaConsumer[String, String]): Cancellable = {
    system.scheduler.scheduleOnce(1.second)(
      process(consumer).onComplete { _ =>
        schedule(consumer)
      }
    )
  }
}
