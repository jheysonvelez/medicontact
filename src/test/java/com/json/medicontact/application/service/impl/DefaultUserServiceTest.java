package com.json.medicontact.application.service.impl;



import com.json.medicontact.domain.model.User;
import com.json.medicontact.domain.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DefaultUserServiceTest
{
	private UserRepository userRepository;
	private DefaultUserService userService;

	@BeforeEach
	void setUp()
	{
		userRepository = mock(UserRepository.class);
		userService = new DefaultUserService(userRepository);
	}

	@Test
	void testFindUserById_shouldFindUser()
	{
		// Given
		Long userId = 1L;
		User user = new User();
		user.setId(userId);
		when(userRepository.findById(userId)).thenReturn(Optional.of(user));

		// When
		User foundUser = userService.findUserById(userId);

		// Then
		assertNotNull(foundUser);
		assertEquals(userId, foundUser.getId());
	}

	@Test
	void testFindUserById_shouldThrowNotFoundException()
	{
		// Given
		Long userId = 1L;
		when(userRepository.findById(userId)).thenReturn(Optional.empty());

		// When/Then
		assertThrows(EntityNotFoundException.class, () -> userService.findUserById(userId));
	}

	@Test
	void testSaveUser_shouldSaveUser()
	{
		// Given
		User user = new User();
		when(userRepository.save(user)).thenReturn(user);

		// When
		User savedUser = userService.saveUser(user);

		// Then
		assertNotNull(savedUser);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testUpdateUser_shouldUpdateUser()
	{
		// Given
		User user = new User();
		user.setId(1L);
		when(userRepository.existsById(user.getId())).thenReturn(true);
		when(userRepository.save(user)).thenReturn(user);

		// When
		User updatedUser = userService.updateUser(user);

		// Then
		assertNotNull(updatedUser);
		verify(userRepository, times(1)).save(user);
	}

	@Test
	void testUpdateUser_shouldThrowNotFoundException()
	{
		// Given
		User user = new User();
		user.setId(1L);
		when(userRepository.existsById(user.getId())).thenReturn(false);

		// When/Then
		assertThrows(EntityNotFoundException.class, () -> userService.updateUser(user));
	}

	@Test
	void testDeleteUser_shouldDeleteUser()
	{
		// Given
		Long userId = 1L;
		when(userRepository.existsById(userId)).thenReturn(true);

		// When
		userService.deleteUser(userId);

		// Then
		verify(userRepository, times(1)).deleteById(userId);
	}

	@Test
	void testDeleteUser_shouldThrowNoSuchElementException()
	{
		// Given
		Long userId = 1L;
		when(userRepository.existsById(userId)).thenReturn(false);

		// When/Then
		assertThrows(EntityNotFoundException.class, () -> userService.deleteUser(userId));
	}

	@Test
	void testFindAllUsers()
	{
		// Given
		List<User> users = Arrays.asList(new User(), new User());
		when(userRepository.findAll()).thenReturn(users);

		// When
		List<User> foundUsers = userService.findAllUsers();

		// Then
		assertNotNull(foundUsers);
		assertEquals(2, foundUsers.size());
	}
}
