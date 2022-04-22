package com.deepdive.Order.Service.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.deepdive.Order.Service.Model.Orders;
import com.deepdive.Order.Service.Model.Product;
import com.deepdive.Order.Service.Service.OrderService;

@RestController
public class OrderContoller {

		@Autowired
	   private OrderService orderService;
		
		
		
		RestTemplate restTemplate = new RestTemplate();
		
	
	    @PostMapping("/api/orders")
	    public Orders createOrder(@RequestBody Orders order) {
	        return orderService.createOrder(order);
	    }

	    @GetMapping("/api/orders/{id}")
	    public Optional<Orders> findOrderById(@PathVariable Long id) {
	        return orderService.findById(id);
	    }

	  
	
	
	
}
