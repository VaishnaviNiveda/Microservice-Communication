package com.deepdive.User.Profile.Service.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.deepdive.User.Profile.Service.Model.User;
import com.deepdive.User.Profile.Service.Repo.UserRepository;

@Service
public class UserProfileService {
	
	@Autowired
	UserRepository userRepository;
	

	public List<User> getAllUsers() {
		return userRepository.findAll();		
	}


	public User addUser(User user) {
		return userRepository.save(user);
	}

	public User getUserById(Long id) throws NotFoundException {
		return userRepository.findById(id).orElseThrow(NotFoundException::new);
	}

}
