package com.demo.orderservice

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.stereotype.Service
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.concurrent.CompletableFuture

@EnableAsync
@Service
class BackendServiceAsyncImpl : BackendServiceAsync{	

	@Autowired
	lateinit var productDao: ProductDao
	
	@Autowired
	lateinit var notificationService: NotificationService
	
	private val log = LoggerFactory.getLogger(BackendServiceAsyncImpl::class.java)
	
	@Async
	override fun checkStockAvailabilityAndProcessOrder(order : Order): CompletableFuture<String> {
	
		try {
			
			var orderedProducts: Map<String,Int> = order.products
			//var availableProducts: List<Products> = productDao.fetchAllProducts()
			var availableProducts: List<Products> = productDao.fetchEnquiredProducts(orderedProducts.keys.toList())
			var outOfStock: Boolean = false
			loop@for(product in availableProducts){
				if(product.availableQty < orderedProducts.get(product.product)!!) {
					outOfStock=true
					break@loop
				} else {
					product.availableQty -= orderedProducts.get(product.product)!!
				}
			}	
			if(outOfStock){
				notificationService.sendMessage(Constant.ORDER_SUBMITTED_TOPIC, Constant.UNAVAILABLE_STOCK+Constant.SEPERATOR+Constant.ORDER_FAILED_UNAVAILABLE_STOCK_STATUS+Constant.SEPERATOR+order.orderId);
				return CompletableFuture.completedFuture(Constant.UNAVAILABLE_STOCK)
			} else {
				productDao.saveAll(availableProducts)
				notificationService.sendMessage(Constant.ORDER_SUBMITTED_TOPIC, Constant.ORDER_ACCEPTED+Constant.SEPERATOR+Constant.ORDER_ACCEPTED_STATUS+Constant.SEPERATOR+order.orderId);
				return CompletableFuture.completedFuture(Constant.ORDER_ACCEPTED)
			}
		} catch (e : Exception) {
			e.printStackTrace()
			log.debug(Constant.EXCEPTION_AT_CHECK_STOCK_AVAILABILITY+ e.getLocalizedMessage())
			return CompletableFuture.completedFuture(Constant.EXCEPTION_AT_CHECK_STOCK_AVAILABILITY+ e.getLocalizedMessage())
		}

	}
}
