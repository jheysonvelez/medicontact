package com.json.medicontact.infrastructure.web.rest.controller;

import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/users")
public class UserController
{
	private final UserService userService;

	@Autowired
	public UserController(UserService userService)
	{
		this.userService = userService;
	}

	@GetMapping("/{userId}")
	public User getUserById(@PathVariable(value = "userId") Long id)
	{
		return userService.findUserById(id);
	}

	@GetMapping("")
	public List<User> findAllUsers()
	{
		return userService.findAllUsers();
	}

	@PostMapping("")
	public User createUser(@RequestBody User user)
	{
		return userService.saveUser(user);
	}

	@PutMapping("")
	public User updateUser(@RequestBody User user)
	{
		return userService.updateUser(user);
	}

	@DeleteMapping("/{userId}")
	public void removeUser(@PathVariable(value = "userId") Long id)
	{
		userService.deleteUser(id);
	}
}
