package com.json.medicontact.infrastructure.web.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.infrastructure.web.assembler.DoctorModelAssembler;
import com.json.medicontact.infrastructure.web.dto.DoctorDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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


@WebMvcTest(DoctorController.class)
@AutoConfigureMockMvc
public class DoctorControllerIntegrationTest
{
	public static final String TEST_EMAIL = "jheyson@doctor.com";
	public static final String DOCUMENT_NUMBER = "12345";
	public static final String NAME = "Jheyson velez";
	public static final String DOCUMENT_TYPE = "ID";
	public static final String SPECIALIZATION = "psychologist";
	@Autowired
	private MockMvc mockMvc;

	@MockBean
	@Qualifier("defaultDoctorService")
	private DoctorService doctorService;

	@MockBean
	private DoctorModelAssembler doctorModelAssembler;

	@Autowired
	private ObjectMapper objectMapper;

	private Doctor doctor;
	private DoctorDTO doctorDTO;

	@BeforeEach
	public void setup()
	{
		doctor = new Doctor();
		doctor.setId(1L);
		doctor.setEmail(TEST_EMAIL);
		doctor.setDocumentNumber(DOCUMENT_NUMBER);
		doctor.setDocumentType(DOCUMENT_TYPE);
		doctor.setName(NAME);
		doctor.setBirthDate(new Date());
		doctor.setSpecialization(SPECIALIZATION);

		doctorDTO = new DoctorDTO();
		doctorDTO.setId(1L);
		doctorDTO.setEmail(TEST_EMAIL);
		doctorDTO.setDocumentNumber(DOCUMENT_NUMBER);
		doctorDTO.setDocumentType(DOCUMENT_TYPE);
		doctorDTO.setName(NAME);
		doctorDTO.setBirthDate(doctor.getBirthDate());
		doctorDTO.setSpecialization(SPECIALIZATION);
	}

	@Test
	void testGetDoctorById() throws Exception
	{
		when(doctorService.findDoctorById(1L)).thenReturn(Optional.of(doctor));
		when(doctorModelAssembler.toModel(doctor)).thenReturn(doctorDTO);

		mockMvc.perform(get("/doctors/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)))
				.andExpect(jsonPath("$.specialization", is(SPECIALIZATION)));
	}

	@Test
	void testGetDoctorByIdNotFound() throws Exception
	{
		when(doctorService.findDoctorById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/doctors/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testFindAllDoctors() throws Exception
	{
		when(doctorService.findAllDoctors()).thenReturn(Arrays.asList(doctor));
		when(doctorModelAssembler.toModel(doctor)).thenReturn(doctorDTO);

		mockMvc.perform(get("/doctors"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$[0].documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$[0].documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$[0].name", is(NAME)))
				.andExpect(jsonPath("$[0].specialization", is(SPECIALIZATION)));
	}

	@Test
	void testCreateDoctor() throws Exception
	{
		when(doctorService.saveDoctor(any(Doctor.class))).thenReturn(doctor);
		when(doctorModelAssembler.toDomain(any(DoctorDTO.class))).thenReturn(doctor);
		when(doctorModelAssembler.toModel(any(Doctor.class))).thenReturn(doctorDTO);

		mockMvc.perform(post("/doctors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(doctorDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)))
				.andExpect(jsonPath("$.specialization", is(SPECIALIZATION)));
	}

	@Test
	void testUpdateDoctor() throws Exception
	{
		when(doctorService.updateDoctor(any(Doctor.class))).thenReturn(doctor);
		when(doctorModelAssembler.toDomain(any(DoctorDTO.class))).thenReturn(doctor);
		when(doctorModelAssembler.toModel(any(Doctor.class))).thenReturn(doctorDTO);

		mockMvc.perform(put("/doctors")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(doctorDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)))
				.andExpect(jsonPath("$.documentNumber", is(DOCUMENT_NUMBER)))
				.andExpect(jsonPath("$.documentType", is(DOCUMENT_TYPE)))
				.andExpect(jsonPath("$.name", is(NAME)))
				.andExpect(jsonPath("$.specialization", is(SPECIALIZATION)));
	}

	@Test
	void testDeleteDoctor() throws Exception
	{
		mockMvc.perform(delete("/doctors/1"))
				.andExpect(status().isOk());

		Mockito.verify(doctorService, Mockito.times(1)).deleteDoctor(1L);
	}

	@Test
	void testPatchDoctor() throws Exception
	{
		when(doctorService.patchDoctor(eq(1L), any(Doctor.class))).thenReturn(doctor);
		when(doctorModelAssembler.toDomain(any(DoctorDTO.class))).thenReturn(doctor);
		when(doctorModelAssembler.toModel(any(Doctor.class))).thenReturn(doctorDTO);

		mockMvc.perform(patch("/doctors/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(doctorDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.email", is(TEST_EMAIL)));
	}
}
