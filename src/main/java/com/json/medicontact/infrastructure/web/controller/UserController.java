package com.json.medicontact.infrastructure.web.controller;

import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.assembler.UserModelAssembler;
import com.json.medicontact.infrastructure.web.dto.UserDTO;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/users")
@Tag(name = "User Management", description = "Operations related to user management")
public class UserController
{
	private final UserService userService;
	private final UserModelAssembler userModelAssembler;

	@Autowired
	public UserController(@Qualifier("defaultUserService") UserService userService, UserModelAssembler userModelAssembler)
	{
		this.userService = userService;
		this.userModelAssembler = userModelAssembler;
	}

	@GetMapping("/{userId}")
	@Operation(summary = "Get a user by Id", description = "Returns a single user by it's Id")
	public ResponseEntity<UserDTO> getUserById(@PathVariable(value = "userId") Long id)
	{
		Optional<User> user = userService.findUserById(id);
		if (user.isPresent())
		{
			UserDTO userDTO = userModelAssembler.toModel(user.get());
			return ResponseEntity.ok(userDTO);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}

	}


	@GetMapping
	@Operation(summary = "Get all users", description = "find all users")
	public ResponseEntity<List<UserDTO>> findAllUsers()
	{
		List<User> users = userService.findAllUsers();
		List<UserDTO> userDTOs = users.stream()
				.map(userModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(userDTOs);
	}

	@PostMapping
	@Operation(summary = "Create a new user")
	public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO)
	{
		User user = userModelAssembler.toDomain(userDTO);
		User saveUser = userService.saveUser(user);
		return ResponseEntity.ok(userModelAssembler.toModel(saveUser));
	}

	@PutMapping
	@Operation(summary = "Update parts of an existing user")
	public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO userDTO)
	{
		User user = userModelAssembler.toDomain(userDTO);
		User updatedUser = userService.updateUser(user);
		return ResponseEntity.ok(userModelAssembler.toModel(updatedUser));
	}

	@DeleteMapping("/{userId}")
	@Operation(summary = "Delete a user by ID")
	public ResponseEntity<Void> removeUser(@PathVariable(value = "userId") Long id)
	{
		userService.deleteUser(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{userId}")
	@Operation(summary = "Partially update an existing user")
	public ResponseEntity<UserDTO> patchUser(@PathVariable(value = "userId") Long id, @RequestBody UserDTO userDTO)
	{
		User patchedUser = userService.patchUser(id, userModelAssembler.toDomain(userDTO));
		return ResponseEntity.ok(userModelAssembler.toModel(patchedUser));
	}
}
