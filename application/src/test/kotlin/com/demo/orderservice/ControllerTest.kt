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
class ControllerTest {

	@Autowired
	lateinit var mockmvc : MockMvc
	
	@Autowired
	lateinit var mapper: ObjectMapper 
	
	
	@MockBean
	lateinit var orderService:OrderService
	
	
	 @Test
	 fun testSaveOrderByListSuccess() {
		 var orderList: ArrayList<String> =ArrayList<String>()
		 orderList.add("Apple")
		 orderList.add("Orange")
		 Mockito.`when`(orderService. processOrder(Mockito.anyMap())).thenReturn("Order Sumbitted");
		 val requestBuilder: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/order/placement")
		 requestBuilder.content(mapper.writeValueAsString(orderList));
		 requestBuilder.contentType("application/json");
		 val expected:String = "Order Sumbitted";
		 val result:MvcResult= mockmvc.perform(requestBuilder).andReturn();
		 Assert.assertEquals(expected, result.getResponse().getContentAsString());
	 }

	@Test(expected = Exception::class)
	 fun testSaveOrderByListFailureWhenForEmptyOrderList() {
		 var orderList: ArrayList<String> =ArrayList<String>() //Empty List
		 Mockito.`when`(orderService. processOrder(Mockito.anyMap())).thenReturn("Order Sumbitted");
		 val requestBuilder: MockHttpServletRequestBuilder = MockMvcRequestBuilders.post("/order/placement")
		 requestBuilder.content(mapper.writeValueAsString(orderList));
		 requestBuilder.contentType("application/json");
		 val result:MvcResult= mockmvc.perform(requestBuilder).andReturn();
	 }
}
