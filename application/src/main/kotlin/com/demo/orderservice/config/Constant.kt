package com.demo.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.Banner
import org.springframework.context.annotation.ComponentScan

class Constant{
	
	companion object {
		@JvmField val SEPERATOR = "$$"
		@JvmField val ORDER_ACCEPTED_STATUS = "Order Accepted"
		@JvmField val ORDER_FAILED_UNAVAILABLE_STOCK_STATUS = "Order Failed"
		@JvmField val ESTIMATED_DEL = " Estimated Delivery on : "
        @JvmField val NO_PRODUCT_SELECTED = "No Products Selected"
		@JvmField val EXCEPTION_OCCURED = "Exception Occured!"
		@JvmField val INAVLID_PRODUCT_SELECTED = "Invalid product Selected, Order Discarded!"
		@JvmField val ORDER_SUBMITED_AND_AMOUNT = "Order Submited. Your Order total is $ "
		@JvmField val ORDER_SUBMITED_ID = " & Order Id #"
		@JvmField val SERVICE_FAILED_PROCESSORDER = "Service Failed for ==> processOrder"
		@JvmField val ORDER_PENDING = "Order Pending"
		@JvmField val PROMOTION_APPLE = "Apple"
		@JvmField val PROMOTION_ORANGE = "Orange"
		@JvmField val ORDER_SUBMITTED_TOPIC = "order-submitted"
		@JvmField val UNAVAILABLE_STOCK = "Your Order Failed due to unavailable stock"
		@JvmField val ORDER_ACCEPTED = "Your Order was successfully processed."
		@JvmField val EXCEPTION_AT_CHECK_STOCK_AVAILABILITY = "Exception Occured at ==> checkStockAvailabilityAndProcessOrder()"
		
		
		
		//KAFKA
		@JvmField val SERIALIZER_KEY = "key.serializer"
		@JvmField val SERIALIZER_KEY_VAL = "org.apache.kafka.common.serialization.StringSerializer"
		@JvmField val SERIALIZER_VALUE = "value.serializer"
		@JvmField val SERIALIZER_VALUE_VAL = "org.apache.kafka.common.serialization.StringSerializer"
		@JvmField val BOOTSTRAP_SERVER = "bootstrap.servers"
		
    }
}
