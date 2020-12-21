package com.demo.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.Banner
import org.springframework.context.annotation.ComponentScan

class Constant{
	
	companion object {
       		@JvmField val NO_PRODUCT_SELECTED = "No Products Selected"
		@JvmField val EXCEPTION_OCCURED = "Exception Occured!"
		@JvmField val INAVLID_PRODUCT_SELECTED = "Invalid product Selected, Order Discarded!"
		@JvmField val ORDER_SUBMITED_AND_AMOUNT = "Order Submited. Your Order total is $ "
		@JvmField val ORDER_SUBMITED_ID = " & Order Id #"
		@JvmField val SERVICE_FAILED_PROCESSORDER = "Service Failed for ==> processOrder"
		@JvmField val ORDER_PENDING = "Order Pending"
		@JvmField val PROMOTION_APPLE = "Apple"
		@JvmField val PROMOTION_ORANGE = "Orange"

    }
}
