package com.deepdive.User.Profile.Service.Repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.deepdive.User.Profile.Service.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
