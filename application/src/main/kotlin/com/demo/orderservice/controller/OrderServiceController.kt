package com.demo.orderservice

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.beans.factory.annotation.Autowired
import javax.ws.rs.BadRequestException
import javax.ws.rs.InternalServerErrorException
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/order")
class OrderServiceController(@Autowired val orderService : OrderService){

	
	private val log = LoggerFactory.getLogger(OrderServiceController::class.java)

	@PostMapping("/placement")
	fun saveOrderByList(@RequestBody orderList:ArrayList<String>): String {
		
		if(orderList.isEmpty()){
			throw IllegalArgumentException(Constant.NO_PRODUCT_SELECTED)
		}
		try {
			val order: Map<String, Int> = orderList.groupingBy{ it }.eachCount().filter { it.value > 0 }
			return orderService.processOrder(order);
		} catch(e : Exception) {
			throw InternalServerErrorException(Constant.EXCEPTION_OCCURED)
		}

	}

	@PostMapping("/placement2")
	fun saveOrderByParamter(@RequestParam allRequestParams: Map<String,Int>): String {
		try {
			return orderService.processOrder(allRequestParams);
		} catch(e : Exception) {
			throw InternalServerErrorException(Constant.EXCEPTION_OCCURED)
		}
		
	}

}
