package com.json.medicontact.infrastructure.web.mapper;

import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface UserMapper
{
	@Mapping(target = "id", source = "user.id")
	@Mapping(target = "documentNumber", source = "user.documentNumber")
	@Mapping(target = "documentType", source = "user.documentType")
	@Mapping(target = "birthDate", source = "user.birthDate")
	@Mapping(target = "name", source = "user.name")
	@Mapping(target = "email", source = "user.email")
	UserDTO userToUserDTO(User user);

	@Mapping(target = "id", source = "user.id")
	@Mapping(target = "documentNumber", source = "user.documentNumber")
	@Mapping(target = "documentType", source = "user.documentType")
	@Mapping(target = "birthDate", source = "user.birthDate")
	@Mapping(target = "name", source = "user.name")
	@Mapping(target = "email", source = "user.email")
	User userDTOToUser(UserDTO user);
}
