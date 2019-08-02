package com.stom.app.service;

import com.stom.app.jpa.User;

public interface UserService extends CRUDService<User>{

	User findByUsername(String username);

}