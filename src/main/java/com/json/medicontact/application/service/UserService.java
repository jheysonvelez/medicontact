package com.json.medicontact.application.service;

import com.json.medicontact.domain.model.User;

import java.util.List;
import java.util.Optional;


public interface UserService
{
	Optional<User> findUserById(Long id);

	List<User> findAllUsers();

	User saveUser(User user);

	User updateUser(User user);

	void deleteUser(Long id);

	User patchUser(Long id, User user);
}
