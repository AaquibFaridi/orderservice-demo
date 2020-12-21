package com.demo.orderservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.Banner
import org.springframework.context.annotation.ComponentScan

@SpringBootApplication
@ComponentScan("com.demo.orderservice")
class OrderserviceApplication

fun main(args: Array<String>) {
	runApplication<OrderserviceApplication>(*args){
		setBannerMode(Banner.Mode.OFF)
	}
}
