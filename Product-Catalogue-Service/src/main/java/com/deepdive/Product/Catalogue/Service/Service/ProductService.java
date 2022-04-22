package com.deepdive.Product.Catalogue.Service.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.deepdive.Product.Catalogue.Service.Model.Product;
import com.deepdive.Product.Catalogue.Service.Repository.ProductRepository;


@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository; 

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();  
		productRepository.findAll().forEach(product1 -> products.add(product1));  
		return products;  
	}
	
	public Product getProductById(long id) {
		return productRepository.findById(id).get(); 		
	}

	public void saveOrUpdate(Product product)   
	{  
		productRepository.save(product);  
	}

	public void delete(long productId) {
		productRepository.deleteById(productId); 
		
	}  
	
}
