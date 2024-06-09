package com.json.medicontact.infrastructure.web.assembler;

import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.dto.UserDTO;
import com.json.medicontact.infrastructure.web.mapper.UserMapper;
import com.json.medicontact.infrastructure.web.controller.UserController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class UserModelAssembler extends RepresentationModelAssemblerSupport<User, UserDTO>
{

	private final UserMapper userMapper;

	@Autowired
	public UserModelAssembler(UserMapper userMapper)
	{
		super(UserController.class, UserDTO.class);
		this.userMapper = userMapper;
	}

	@NonNull
	@Override
	public UserDTO toModel(@NonNull User user)
	{
		UserDTO userDTO = userMapper.userToUserDTO(user);

		userDTO.add(linkTo(methodOn(UserController.class).getUserById(user.getId())).withSelfRel());

		userDTO.add(linkTo(methodOn(UserController.class).updateUser(null)).withRel("update"));
		userDTO.add(linkTo(methodOn(UserController.class).removeUser(user.getId())).withRel("delete"));
		userDTO.add(linkTo(methodOn(UserController.class).findAllUsers()).withRel("allUsers"));

		return userDTO;

	}

	public User toDomain(UserDTO userDTO)
	{
		return userMapper.userDTOToUser(userDTO);
	}
}
