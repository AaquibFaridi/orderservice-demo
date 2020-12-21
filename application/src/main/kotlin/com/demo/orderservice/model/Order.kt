package com.demo.orderservice

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import java.util.UUID
 
//@Entity
//@Table(name = "products")
public class Order(
	
	var orderId: UUID,
	var products: Map<String,Int>,
	var totalAmout: Double,
	var status: String

	//  @Id
    //@GeneratedValue
    //val id: Long = -1,
 
    //@Column(name = "product")
    //val product: String = "",
 
    //@Column(name = "availableQty")
    //val availableQty: Int = 0 ,  

    //@Column(name = "price")
    //val price: Int = 0   
){
  }
