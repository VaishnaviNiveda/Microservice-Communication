package com.deepdive.Product.Catalogue.Service.Controller;


import java.util.List;

import org.apache.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.deepdive.Product.Catalogue.Service.Model.Product;
import com.deepdive.Product.Catalogue.Service.Service.ProductService;

@RestController
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	Product product;
	
	@GetMapping("/test")
	private String printSuccess() {
		String s = "Success Success Success";
		return s;
	}
	
	@GetMapping("/products")
	private List<Product> getAllProducts() {
		return productService.getAllProducts();
	}
	
	@GetMapping("/product/{productId}")  
	private Product getProduct(@PathVariable("productId") long productId)   
	{  
		try {
			
			 product = productService.getProductById(productId);
		} catch (Exception e) {
			throw new ResponseStatusException(
					  HttpStatus.SC_NOT_FOUND, "entity not found", e
					);
		}
		return product;
	}  

	
	@PostMapping("/add")  
	private Long saveProduct(@RequestBody Product product)   
	{  
		productService.saveOrUpdate(product);  
		return product.getProductId();  
	}  
	
	@DeleteMapping("/product/{productId}")  
	private void deleteBook(@PathVariable("productId") int productId)   
	{  
		productService.delete(productId);  
	}  

}
