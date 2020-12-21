package com.demo.orderservice

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.util.concurrent.ListenableFuture
import org.springframework.kafka.support.SendResult
import org.apache.kafka.clients.producer.ProducerRecord
import org.apache.kafka.clients.producer.KafkaProducer
import org.apache.kafka.clients.producer.RecordMetadata
import java.util.concurrent.Future
import org.slf4j.Logger
import org.slf4j.LoggerFactory

@Component
class NotificationService {
	
	@Autowired
	lateinit var kafkaTemplate:KafkaTemplate<String, String>
	
	@Value("\${boostrap.server}")
	lateinit var bootrapServer: String
	
	private val log = LoggerFactory.getLogger(NotificationService::class.java)
	
	fun sendMessage(topic: String, message : String ) :String {
		kafkaTemplate.send(topic, message)!!
		return "Sent"
	}
	
	fun produceMessage( topic: String, message : String) :String {
		var producerRecord :ProducerRecord<String, String> = ProducerRecord(topic, message)
		val map = mutableMapOf<String, String>()
		map[Constant.SERIALIZER_KEY]   = Constant.SERIALIZER_KEY_VAL
		map[Constant.SERIALIZER_VALUE] = Constant.SERIALIZER_VALUE_VAL
		map[Constant.BOOTSTRAP_SERVER] = bootrapServer
		var producer = KafkaProducer<String, String>(map as Map<String, Any>?)
		var future:Future<RecordMetadata> = producer.send(producerRecord)!!
		return " message sent to " + future.get().topic()
}

}
