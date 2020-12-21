package com.demo.orderservice

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Component
 
@Component
interface ProductRepo: CrudRepository<Products, Long>{

	 override fun findAll(): List<Products>
	 fun findByProductIn(products: List<String>): List<Products>

} 