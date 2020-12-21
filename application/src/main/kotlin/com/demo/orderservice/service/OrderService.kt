package com.demo.orderservice

import org.springframework.stereotype.Service
import org.springframework.scheduling.annotation.Async

@Service
interface OrderService{	
fun processOrder(order : Map<String,Int>) : String
}
