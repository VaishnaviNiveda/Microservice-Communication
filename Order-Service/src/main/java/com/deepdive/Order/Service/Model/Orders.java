package com.deepdive.Order.Service.Model;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	
	private long userId;

    private String customerEmail;

    private String customerAddress;
    
    private BigDecimal totalAmount;
    
    @OneToMany(cascade = CascadeType.ALL)
    private List<OrderItem> items;
}
