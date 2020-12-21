package com.demo.orderservice


import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.junit.runner.RunWith
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.web.servlet.MockMvc
import org.junit.Test
import org.mockito.Mockito
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.test.web.servlet.MvcResult
import org.skyscreamer.jsonassert.JSONAssert
import org.mockito.MockitoAnnotations
import org.junit.Assert
import javax.ws.rs.BadRequestException
import org.springframework.scheduling.annotation.Async
import java.util.concurrent.CompletableFuture
import java.util.UUID


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class ServiceTest {

	@Autowired
	lateinit var orderService:OrderService
	
	@Autowired
	lateinit var backendServiceAsync:BackendServiceAsync
	
	@MockBean
	lateinit var productDao:ProductDao
	
	@MockBean
	lateinit var notificationService: NotificationService
	
	
	
	 @Test
	 fun testProcessOrderSuccess() {
		 var productList: ArrayList<Products> =ArrayList<Products>()
		 productList.add(Products(1,"Apple",10,10.00))
		 productList.add(Products(2,"Orange",10,10.00))
		 productList.add(Products(3,"Banana",10,10.00))
		 Mockito.`when`(productDao. fetchEnquiredProducts(Mockito.anyList())).thenReturn(productList);
		 var order:Map<String,Int> = mapOf<String,Int>("Apple" to 1,"Orange" to 2)
		 val result :String= orderService.processOrder(order)
		 val expected:String = "Order Submited. Your Order total is $ 30.0";
		 Assert.assertTrue(result.contains(expected));		 
		 //Assert.assertEquals(expected, result);
	 }
	
	@Test
	 fun testProcessOrderFailureInavlidProductSelected() {
		 var productList: ArrayList<Products> =ArrayList<Products>()
		 productList.add(Products(1,"Apple",10,10.00))
		 productList.add(Products(2,"Orange",10,10.00))
		 productList.add(Products(3,"Banana",10,10.00))
		 Mockito.`when`(productDao. fetchEnquiredProducts(Mockito.anyList())).thenReturn(productList);
		 var order:Map<String,Int> = mapOf<String,Int>("Grapes" to 1,"Orange" to 2)
		 val result :String= orderService.processOrder(order)
		 val expected:String = "Invalid product Selected, Order Discarded!";		 
		 Assert.assertEquals(expected, result);
	 }
	
	@Test
	 fun testProcessOrderSuccessForPromotionApplied() {
		 var productList: ArrayList<Products> =ArrayList<Products>()
		 productList.add(Products(1,"Apple",10,10.00))
		 productList.add(Products(2,"Orange",10,10.00))
		 productList.add(Products(3,"Banana",10,10.00))
		 Mockito.`when`(productDao. fetchEnquiredProducts(Mockito.anyList())).thenReturn(productList);
		 var order:Map<String,Int> = mapOf<String,Int>("Apple" to 4,"Orange" to 6)
		 val result :String= orderService.processOrder(order)
		 val expected:String = "Order Submited. Your Order total is $ 60.0 & Order Id #";		 
		 //Assert.asser assertEquals(expected, result);
		 Assert.assertTrue(result.contains(expected));
	 }
	
	@Async
	@Test
	 fun testBackendServiceForCheckStockAvailabilityAndProcessOrderSuccess() {
		 var productList: ArrayList<Products> =ArrayList<Products>()
		 productList.add(Products(1,"Apple",10,10.00))
		 productList.add(Products(2,"Orange",10,10.00))
		 Mockito.`when`(notificationService. sendMessage(Mockito.anyString(),Mockito.anyString())).thenReturn(null)
		 Mockito.`when`(productDao. fetchEnquiredProducts(Mockito.anyList())).thenReturn(productList);
		 var products:Map<String,Int> = mapOf<String,Int>("Apple" to 1,"Orange" to 2)
		 val result :CompletableFuture<String> =  backendServiceAsync.checkStockAvailabilityAndProcessOrder(Order(UUID.randomUUID(),products,30.00,"Order Submitted"))
		 val expected:String = "Your Order was successfully processed.";		 
		 Assert.assertEquals(expected, result.get());
	 }
	
	@Async
	@Test
	 fun testBackendServiceForCheckStockAvailabilityAndProcessOrderOutOfStock() {
		 var productList: ArrayList<Products> =ArrayList<Products>()
		 productList.add(Products(1,"Apple",10,10.00))
		 productList.add(Products(2,"Orange",10,10.00))
		 Mockito.`when`(notificationService. sendMessage(Mockito.anyString(),Mockito.anyString())).thenReturn(null)
		 Mockito.`when`(productDao. fetchEnquiredProducts(Mockito.anyList())).thenReturn(productList);
		 var products:Map<String,Int> = mapOf<String,Int>("Apple" to 100,"Orange" to 2)
		 val result :CompletableFuture<String> =  backendServiceAsync.checkStockAvailabilityAndProcessOrder(Order(UUID.randomUUID(),products,30.00,"Order Submitted"))
		 val expected:String = "Your Order Failed due to unavailable stock";		 
		 Assert.assertEquals(expected, result.get());
	 }
	

}
