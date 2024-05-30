package com.json.medicontact.infrastructure.web.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.assembler.UserModelAssembler;
import com.json.medicontact.infrastructure.web.dto.UserDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest
{
	public static final String TEST_EMAIL = "jheyson@test.com";
	public static final String DOCUMENT_NUMBER = "12345678";
	public static final String NAME = "John Doe";
	public static final String DOCUMENT_TYPE = "ID";
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@MockBean
	private UserModelAssembler userModelAssembler;

	@Autowired
	private ObjectMapper objectMapper;

	private User user;
	private UserDTO userDTO;

	@BeforeEach
	public void setup()
	{
		user = new User();
		user.setId(1L);
		user.setEmail(TEST_EMAIL);
		user.setDocumentNumber(DOCUMENT_NUMBER);
		user.setDocumentType(DOCUMENT_TYPE);
		user.setName(NAME);
		user.setBirthDate(new Date());

		userDTO = new UserDTO();
		userDTO.setId(1L);
		userDTO.setEmail(TEST_EMAIL);
		userDTO.setDocumentNumber(DOCUMENT_NUMBER);
		userDTO.setDocumentType(DOCUMENT_TYPE);
		userDTO.setName(NAME);
		userDTO.setBirthDate(user.getBirthDate());
	}

	@Test
	void testGetUserById() throws Exception
	{
		when(userService.findUserById(1L)).thenReturn(Optional.of(user));
		when(userModelAssembler.toModel(user)).thenReturn(userDTO);

		mockMvc.perform(get("/users/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)));
	}

	@Test
	void testGetUserByIdNotFound() throws Exception
	{
		when(userService.findUserById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/users/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testFindAllUsers() throws Exception
	{
		when(userService.findAllUsers()).thenReturn(Arrays.asList(user));
		when(userModelAssembler.toModel(user)).thenReturn(userDTO);

		mockMvc.perform(get("/users"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$[0].documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$[0].documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$[0].name", is(NAME)));
	}

	@Test
	void testCreateUser() throws Exception
	{
		when(userService.saveUser(any(User.class))).thenReturn(user);
		when(userModelAssembler.toDomain(any(UserDTO.class))).thenReturn(user);
		when(userModelAssembler.toModel(any(User.class))).thenReturn(userDTO);

		mockMvc.perform(post("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)));
	}

	@Test
	void testUpdateUser() throws Exception
	{
		when(userService.updateUser(any(User.class))).thenReturn(user);
		when(userModelAssembler.toDomain(any(UserDTO.class))).thenReturn(user);
		when(userModelAssembler.toModel(any(User.class))).thenReturn(userDTO);

		mockMvc.perform(put("/users")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)));
	}

	@Test
	void testDeleteUser() throws Exception
	{
		mockMvc.perform(delete("/users/1"))
				.andExpect(status().isOk());

		Mockito.verify(userService, Mockito.times(1)).deleteUser(1L);
	}

	@Test
	void testPatchUser() throws Exception
	{
		when(userService.patchUser(eq(1L), any(User.class))).thenReturn(user);
		when(userModelAssembler.toDomain(any(UserDTO.class))).thenReturn(user);
		when(userModelAssembler.toModel(any(User.class))).thenReturn(userDTO);

		mockMvc.perform(patch("/users/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(userDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)));
	}
}
