package com.deepdive.Order.Service.Model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class OrderItem {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Itemid;
    private Long productId;
    private int quantity;
    private BigDecimal productPrice;

//    public BigDecimal getPrice() {
//        return productPrice.multiply(new BigDecimal(quantity));
//    }
    
}
