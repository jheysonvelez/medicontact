package com.json.medicontact.application.service;

import com.json.medicontact.domain.model.User;

import java.util.List;


public interface UserService
{
	User findUserById(Long id);

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(Long id);

	List<User> findAllUsers();
}
