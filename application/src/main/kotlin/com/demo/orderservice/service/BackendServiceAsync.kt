package com.demo.orderservice

import org.springframework.stereotype.Service

import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture

@Service
@Async
interface BackendServiceAsync{	
	fun checkStockAvailabilityAndProcessOrder(order : Order): CompletableFuture<String>
}
