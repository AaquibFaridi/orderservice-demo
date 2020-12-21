package com.demo.orderservice

import org.springframework.stereotype.Service
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Component

@Component
interface ProductDao{	
	fun fetchAllProducts() : List<Products>
	fun fetchEnquiredProducts(products:List<String>) : List<Products>
	fun saveAll(productsList : List<Products>)
}
