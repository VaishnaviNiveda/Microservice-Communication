package com.deepdive.User.Profile.Service.Controller;

import java.util.List;
import java.util.Properties;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.deepdive.User.Profile.Service.MessagingConfig;
import com.deepdive.User.Profile.Service.Model.User;
import com.deepdive.User.Profile.Service.Service.UserProfileService;

@RestController
public class UserController {
	
	private static final String KAFKA_TOPIC = "test-topic3";

	@Autowired
	UserProfileService userProfileService;
	
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	
	@GetMapping("/user-kafka/{id}")
	private User getUserByIdKafka(@PathVariable Long id) throws NotFoundException {
		
		User user = userProfileService.getUserById(id);
		//KafkaTemplate.send(topic, object);
		kafkaTemplate.send(KAFKA_TOPIC, user);
		return user;
	}	
	
	
	@GetMapping("/test")
	private String printSuccess() {
		String s = "Success Success Success";
		return s;
	}
	
	@GetMapping("/users")
	private List<User> getAllUsers() {
		return userProfileService.getAllUsers();
	}
	
	@GetMapping("/user/{id}")
	private User getUserById(@PathVariable Long id) throws NotFoundException {
		User user = userProfileService.getUserById(id);
		rabbitTemplate.convertAndSend(MessagingConfig.USER_EXCHANGE, MessagingConfig.USER_ROUTINGKEY, user);
		return user;
	}	
	

	@PostMapping("/add-user")
	private User addUser(@RequestBody User user) {
		return userProfileService.addUser(user);
	}
	
	
}
