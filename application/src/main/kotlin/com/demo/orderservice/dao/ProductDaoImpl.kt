package com.demo.orderservice

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.apache.kafka.connect.errors.DataException
import java.sql.SQLException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import javax.ws.rs.BadRequestException


@Component
class ProductDaoImpl : ProductDao{	

	@Autowired
    lateinit var productRepo: ProductRepo
	
	private val log = LoggerFactory.getLogger(ProductDaoImpl::class.java)

	override fun fetchAllProducts() : List<Products> {
		try{
			return productRepo.findAll()
		} catch (e : Exception) {
			throw SQLException("Database Exception while calling ==> fetchAllProducts()")
		}
	}
	
	override fun fetchEnquiredProducts(products:List<String>) : List<Products> {
		try{
//		println("33333333333heyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyyy")
//		var product:Products = Products(0,"Apple",10,10.00)
//		productRepo.save(product)
//		var product1:Products = Products(0,"Orange",10,10.00)
//		productRepo.save(product1)
//		var product2:Products = Products(0,"Banana",10,10.00)
//		productRepo.save(product2)
			return productRepo.findByProductIn(products)				
		} catch (e : Exception) {
			throw SQLException("Database Exception while calling ==> fetchEnquiredProducts()")
		}
	}
	override fun saveAll(productsList : List<Products>){
		try {
			for(product in productsList){
				productRepo.save(product)
			}
		} catch (e : Exception) {
			throw SQLException("Database Exception while calling ==> saveAll()")
		}
	}
}
