package com.demo.orderservice

import org.springframework.stereotype.Component
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value;
import org.hibernate.service.spi.ServiceException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.UUID

@Component
class OrderServiceImpl : OrderService{	

	@Autowired
	lateinit var productDao: ProductDao

	private val log = LoggerFactory.getLogger(OrderServiceImpl::class.java)

	override fun processOrder(order : Map<String,Int>): String {

		try {
			//val availableProducts: List<Products> = productDao.fetchAllProducts()
			val availableProducts: List<Products> = productDao.fetchEnquiredProducts(order.keys.toList())
					var invalidProductSelected : Boolean = false
					var totalAmount : Double = 0.00
					loop@for (item in order) {
						val prod: Products ? = availableProducts.find { it.product == item.key }			
						if(prod != null) {
							val quantityOrdered : Int = order.get(item.key)!!.toInt()		
							totalAmount =calculateAmount(quantityOrdered, prod,totalAmount)
						} else {
							invalidProductSelected = true
							break@loop
						}
					}
			if(invalidProductSelected) {
				return Constant.INAVLID_PRODUCT_SELECTED
			} else {

				var finalorder:Order = Order(UUID.randomUUID(),order,totalAmount,Constant.ORDER_PENDING)			
				return Constant.ORDER_SUBMITED_AND_AMOUNT+totalAmount+Constant.ORDER_SUBMITED_ID+finalorder.orderId
			}
		} catch (e : Exception) {
			throw ServiceException(Constant.SERVICE_FAILED_PROCESSORDER)
		}

	}

	fun calculateAmount(quantity: Int, product: Products, totalAmount: Double) : Double{
		var amt : Double
		amt = totalAmount +  (quantity * product.price)
		return amt
	}

}
