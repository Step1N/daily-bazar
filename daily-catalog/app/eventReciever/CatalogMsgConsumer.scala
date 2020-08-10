package eventReciever

import java.time.Duration
import java.util
import java.util.Properties
import javax.inject.Singleton

import org.apache.kafka.clients.consumer.KafkaConsumer

@Singleton
class CatalogMsgConsumer {
  def consumeFromKafka(topic: String) = {

    val props = new Properties()

    props.put("bootstrap.servers", "localhost:9092")

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
