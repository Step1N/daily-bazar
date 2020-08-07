package eventSender

import java.util
import org.apache.kafka.clients.consumer.KafkaConsumer
import java.util.Properties
import java.time.Duration

object CatalogMsgConsumer {
  def consumeFromKafka(topic: String) = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9094")

    props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer")

    props.put("auto.offset.reset", "latest")

    props.put("group.id", "consumer-group")

    val consumer: KafkaConsumer[String, String] = new KafkaConsumer[String, String](props)

    consumer.subscribe(util.Arrays.asList(topic))
    val duration = Duration.ofSeconds(3000)
    consumer.poll(duration)
  }
}
