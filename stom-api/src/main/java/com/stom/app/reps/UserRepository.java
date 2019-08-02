package com.stom.app.reps;

import org.springframework.data.repository.CrudRepository;

import com.stom.app.jpa.User;

public interface UserRepository extends CrudRepository<User, Integer>{

	User findByUsername(String username);
}
