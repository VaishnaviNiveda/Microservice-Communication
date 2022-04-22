package com.deepdive.Order.Service.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.deepdive.Order.Service.Controller.ProductCtalogueFeignClient;
import com.deepdive.Order.Service.Model.OrderItem;
import com.deepdive.Order.Service.Model.Orders;
import com.deepdive.Order.Service.Model.Product;
import com.deepdive.Order.Service.Model.User;
import com.deepdive.Order.Service.Repository.OrderRepository;

@Service
public class OrderService {

	private static final String USER_QUEUE = "user_queue";

	@Autowired
	OrderRepository orderRepository;

	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private WebClient.Builder webClientBuilder;

	@Autowired
	ProductCtalogueFeignClient productCtalogueFeignClient;

	User user;

	@Autowired
	RabbitTemplate template;

	public Orders createOrder(Orders order) {

		List<OrderItem> items = order.getItems();
		Double totalAmt = 0d;

		for (OrderItem item : items) {
			Long productId = item.getProductId();

			Product product;

//			product = restTemplate.getForObject("http://localhost:8080/product/" + productId, Product.class);

//			product = restTemplate.getForObject("http://product-catalogue-service/product/" + productId, Product.class);

			/*
			 * product = webClientBuilder.build() .get()
			 * .uri("http://localhost:8080/product/" + productId) .retrieve()
			 * .bodyToMono(Product.class) .block();
			 */

			product = productCtalogueFeignClient.getProduct(productId);

			Double productPrice = product.getPrice();
			BigDecimal price = new BigDecimal(productPrice);
			price = price.multiply(new BigDecimal(item.getQuantity()));
			item.setProductPrice(price);
			totalAmt += price.doubleValue();
		}
		order.setTotalAmount(new BigDecimal(totalAmt));
		return orderRepository.save(order);
	}

	@RabbitListener(queues = USER_QUEUE)
	public void getUser(User user) {
		this.user = user;
		System.out.println("*************adfaasfd*****************");
		System.out.println(user);
	}

	@KafkaListener(topics = "test-topic3", groupId = "group_1")
	public void getUserUsinfKafka(User user) {
		this.user = user;

	}

	public Optional<Orders> findById(Long id) {
		return orderRepository.findById(id);
	}

}
