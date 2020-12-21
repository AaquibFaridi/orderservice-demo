package com.demo.orderservice

//import org.junit.jupiter.api.Test
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


@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class DaoTest {


	
	@Autowired
	lateinit var productDao:ProductDao
	
	@MockBean
    lateinit var productRepo: ProductRepo
	
	
	 @Test
	 fun testFetchAllProducts() {
		 var productList: List<Products> = listOf(Products(1,"Apple",10,10.00),Products(2,"Orange",10,10.00),Products(3,"Banana",10,10.00))
		 Mockito.`when`(productRepo.findAll()).thenReturn(productList);	 
		 val result :List<Products> = productDao.fetchAllProducts()		 
		 Assert.assertEquals(3, result.size);
	 }
	
	@Test
	 fun testFetchEnquiredProducts() {
		 var productList: List<Products> = listOf(Products(1,"Apple",10,10.00),Products(2,"Orange",10,10.00),Products(3,"Banana",10,10.00))
		 Mockito.`when`(productRepo.findByProductIn(Mockito.anyList())).thenReturn(productList);	 
		 val result :List<Products> = productDao.fetchEnquiredProducts(listOf("Apple","Orange","Banana"))	 		 
		 Assert.assertEquals(3, result.size);
		
	 }
	
	@Test
	 fun testSaveAll() {
		 var productList: List<Products> = listOf(Products(1,"Apple",10,10.00),Products(2,"Orange",10,10.00),Products(3,"Banana",10,10.00))
		 Mockito.`when`(productRepo.save(Mockito.any())).thenReturn(Products(1,"Apple",10,10.00));	 
		 productDao.saveAll(productList)	 		 	
	 }
}
