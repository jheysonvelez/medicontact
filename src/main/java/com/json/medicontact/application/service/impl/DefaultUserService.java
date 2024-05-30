package com.json.medicontact.application.service.impl;

import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Qualifier("defaultUserService")
public class DefaultUserService implements UserService
{
	final UserRepository userRepository;

	@Autowired
	public DefaultUserService(UserRepository userRepository)
	{
		this.userRepository = userRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<User> findUserById(Long id)
	{
		return userRepository.findById(id);
	}

	@Override
	@Transactional
	public User saveUser(User user)
	{
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public User updateUser(User user)
	{
		validateUserExistsById(user.getId());
		return userRepository.save(user);
	}

	@Override
	@Transactional
	public void deleteUser(Long id)
	{
		validateUserExistsById(id);
		userRepository.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findAllUsers()
	{
		return userRepository.findAll();
	}

	@Override
	@Transactional
	public User patchUser(Long id, User user)
	{
		validateUserExistsById(id);
		User existingUser = userRepository.findById(id).get();
		copyNonNullProperties(user, existingUser);
		return userRepository.save(existingUser);
	}

	public void copyNonNullProperties(User source, User target)
	{
		if (Objects.nonNull(source.getEmail()))
		{
			target.setEmail(source.getEmail());
		}
		if (Objects.nonNull(source.getDocumentNumber()))
		{
			target.setDocumentNumber(source.getDocumentNumber());
		}
		if (Objects.nonNull(source.getDocumentType()))
		{
			target.setDocumentType(source.getDocumentType());
		}
		if (Objects.nonNull(source.getBirthDate()))
		{
			target.setBirthDate(source.getBirthDate());
		}
		if (Objects.nonNull(source.getName()))
		{
			target.setName(source.getName());
		}
	}

	protected void validateUserExistsById(Long id)
	{
		if (!userRepository.existsById(id))
		{
			throw new EntityNotFoundException("User with id " + id + " not found");
		}
	}
}
