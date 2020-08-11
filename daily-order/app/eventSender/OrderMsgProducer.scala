package eventSender

import java.util.Properties
import javax.inject.Singleton

import org.apache.kafka.clients.producer._

@Singleton
class OrderMsgProducer {

  def informToOthers(topic: String, message: String): Unit = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

    props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer")

    val producer = new KafkaProducer[String, String](props)

    val record = new ProducerRecord[String, String](topic, "order-change", message)
    producer.send(record)
    producer.close()

  }

}