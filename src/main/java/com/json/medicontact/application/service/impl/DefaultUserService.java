package com.json.medicontact.application.service.impl;

import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class DefaultUserService implements UserService
{
	final UserRepository userRepository;

	@Autowired
	public DefaultUserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	public User findUserById(Long id)
	{
		Optional<User> optionalUser = userRepository.findById(id);
		return optionalUser.orElseThrow(() -> new EntityNotFoundException("User with id " + id + " not found"));
	}

	@Override
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user)
	{
		validateUserExistsById(user.getId());
		return saveUser(user);
	}

	@Override
	public void deleteUser(Long id)
	{
		validateUserExistsById(id);
		userRepository.deleteById(id);
	}

	protected void validateUserExistsById(Long id)
	{
		if (!userRepository.existsById(id))
		{
			throw new EntityNotFoundException("User with id " + id + " not found");
		}
	}

	@Override
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}
}
