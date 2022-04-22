package com.deepdive.Product.Catalogue.Service.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepdive.Product.Catalogue.Service.Model.Product;  

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
