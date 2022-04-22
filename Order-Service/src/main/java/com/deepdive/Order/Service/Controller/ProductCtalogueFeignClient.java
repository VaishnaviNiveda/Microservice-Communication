package com.deepdive.Order.Service.Controller;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.deepdive.Order.Service.Model.Product;

@FeignClient(name = "product-catalogue-service")
public interface ProductCtalogueFeignClient {
	
	@GetMapping("/product/{productId}")  
	Product getProduct(@PathVariable Long productId);   
	
}
