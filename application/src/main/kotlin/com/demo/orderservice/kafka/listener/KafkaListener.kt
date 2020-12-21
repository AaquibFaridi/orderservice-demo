package com.demo.orderservice

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.beans.factory.annotation.Autowired
import java.util.Calendar
import java.util.Date
import java.time.LocalDateTime



@Component
class KafkaListener {

	@Autowired
	lateinit var notificationService: NotificationService

	private val logger = LoggerFactory.getLogger(javaClass)

	@KafkaListener(topics = ["order-submitted"], groupId = "order-submitted")
	fun processSubmittedOrder(message: String) {
		var orderDetails: List<String> = message.split(Constant.SEPERATOR)
				logger.info("Order Status :::: "+ orderDetails.get(0))
				if(orderDetails.get(1) == Constant.ORDER_FAILED_UNAVAILABLE_STOCK_STATUS) {
					notificationService.sendMessage("Order-Status", Constant.UNAVAILABLE_STOCK);
				} else if(orderDetails.get(1) == Constant.ORDER_ACCEPTED_STATUS) {
					var estimatedDelTime: String = calculateEstimatesDeliveryTime()
					println(Constant.ORDER_ACCEPTED + Constant.ESTIMATED_DEL + estimatedDelTime)
					notificationService.sendMessage("Order-Status", Constant.ORDER_ACCEPTED + Constant.ESTIMATED_DEL + estimatedDelTime);
				}

	}
	
	@KafkaListener(topics = ["Order-Status"], groupId = "Order-Status")
	fun process(message: String) {
		println("$$$$$$$$$$$$$$$$$$$$$ Order-Status : "+message)
	}
	
	fun calculateEstimatesDeliveryTime(): String {	
		
		val estimatedDelTime = Calendar.getInstance()
		estimatedDelTime.add(Calendar.DATE, 2) 
		return estimatedDelTime.time.toString()
	}
}