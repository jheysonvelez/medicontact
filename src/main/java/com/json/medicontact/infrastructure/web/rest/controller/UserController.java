package com.json.medicontact.infrastructure.web.rest.controller;

import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.dto.UserDTO;
import com.json.medicontact.infrastructure.web.mapper.UserMapper;
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
	private final UserMapper userMapper;

	@Autowired
	public UserController(UserService userService, UserMapper userMapper)
	{
		this.userService = userService;
		this.userMapper = userMapper;
	}

	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable(value = "userId") Long id)
	{
		User user = userService.findUserById(id);
		return userMapper.userToUserDTO(user);
	}

	@GetMapping("")
	public List<User> findAllUsers()
	{
		return userService.findAllUsers();
	}

	@PostMapping("")
	public UserDTO createUser(@RequestBody UserDTO userDTO)
	{
		User user = userMapper.userDTOToUser(userDTO);
		User saveUser = userService.saveUser(user);
		return userMapper.userToUserDTO(saveUser);
	}

	@PutMapping("")
	public UserDTO updateUser(@RequestBody UserDTO userDTO)
	{
		User user = userMapper.userDTOToUser(userDTO);
		User updatedUser = userService.updateUser(user);
		return userMapper.userToUserDTO(updatedUser);
	}

	@DeleteMapping("/{userId}")
	public void removeUser(@PathVariable(value = "userId") Long id)
	{
		userService.deleteUser(id);
	}
}
