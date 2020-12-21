package com.demo.orderservice

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
 
@Entity
 class Products(
    @Id
    @GeneratedValue
    var id: Int =0,
    var product: String="",
    var availableQty: Int=0 ,  
    var price: Double=0.00   
)