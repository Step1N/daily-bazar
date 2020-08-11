package eventReciever

import java.time.Duration
import java.util
import java.util.Properties
import javax.inject.{Inject, Singleton}

import org.apache.kafka.clients.consumer.KafkaConsumer
import services.OrderService

@Singleton
class OrderMsgConsumer @Inject()(orderService: OrderService){

  def consumeFromKafka(topic: String) = {
    println(" I am listening now")
    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("auto.offset.reset", "latest")

    props.put("group.id", "order-consumer")

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Arrays.asList(topic))
    val duration = Duration.ofSeconds(3000)
    consumer.poll(duration).records("test").forEach {
      e => e.value match {
        case "addedItemToCatalog" =>
          orderService.placeOrder()
        case _ =>
          println("I dont care about this one", e.value)
      }
    }
  }
}
