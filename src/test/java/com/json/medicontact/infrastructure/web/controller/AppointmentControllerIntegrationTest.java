package com.json.medicontact.infrastructure.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.json.medicontact.application.service.AppointmentService;
import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.assembler.AppointmentModelAssembler;
import com.json.medicontact.infrastructure.web.dto.AppointmentDTO;
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

import java.util.Collections;
import java.util.Date;
import java.util.Optional;

import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AppointmentController.class)
@AutoConfigureMockMvc
class AppointmentControllerIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private AppointmentService appointmentService;

	@MockBean
	@Qualifier("defaultUserService")
	private UserService userService;

	@MockBean
	@Qualifier("defaultDoctorService")
	private DoctorService doctorService;

	@MockBean
	private AppointmentModelAssembler appointmentModelAssembler;

	@Autowired
	private ObjectMapper objectMapper;

	private Appointment appointment;
	private AppointmentDTO appointmentDTO;

	@BeforeEach
	public void setup() {
		User user = new User();
		user.setId(1L);
		user.setName("Test User");

		Doctor doctor = new Doctor();
		doctor.setId(1L);
		doctor.setName("Test Doctor");

		appointment = new Appointment();
		appointment.setId(1L);
		appointment.setAppointmentDate(new Date());
		appointment.setDescription("Test Description");
		appointment.setUser(user);
		appointment.setDoctor(doctor);

		appointmentDTO = new AppointmentDTO();
		appointmentDTO.setId(1L);
		appointmentDTO.setAppointmentDate(appointment.getAppointmentDate());
		appointmentDTO.setDescription("Test Description");
		appointmentDTO.setUser("Test User");
		appointmentDTO.setDoctor("Test Doctor");
	}

	@Test
	void testFindAllAppointments() throws Exception {
		when(appointmentService.getAllAppointments()).thenReturn(Collections.singletonList(appointment));
		when(appointmentModelAssembler.toModel(appointment)).thenReturn(appointmentDTO);

		mockMvc.perform(get("/appointments"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].description", is("Test Description")));
	}

	@Test
	void testFindAppointmentById() throws Exception {
		when(appointmentService.getAppointment(1L)).thenReturn(appointment);
		when(appointmentModelAssembler.toModel(appointment)).thenReturn(appointmentDTO);

		mockMvc.perform(get("/appointments/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is("Test Description")));
	}

	@Test
	void testCreateAppointment() throws Exception {
		when(appointmentService.saveAppointment(any(Appointment.class))).thenReturn(appointment);
		when(appointmentModelAssembler.toDomain(any(AppointmentDTO.class))).thenReturn(appointment);
		when(appointmentModelAssembler.toModel(any(Appointment.class))).thenReturn(appointmentDTO);

		mockMvc.perform(post("/appointments")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(appointmentDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is("Test Description")));
	}

	@Test
	void testRemoveAppointment() throws Exception {
		mockMvc.perform(delete("/appointments/1"))
				.andExpect(status().isOk());

		Mockito.verify(appointmentService, Mockito.times(1)).deleteAppointment(1L);
	}

	@Test
	void testFindAllAppointmentsByUser() throws Exception {
		when(userService.findUserById(1L)).thenReturn(Optional.of(appointment.getUser()));
		when(appointmentService.getAppointmentsByUser(any(User.class))).thenReturn(Collections.singletonList(appointment));
		when(appointmentModelAssembler.toModel(appointment)).thenReturn(appointmentDTO);

		mockMvc.perform(get("/appointments/user/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].description", is("Test Description")));
	}

	@Test
	void testFindAllAppointmentsByUserNotFound() throws Exception {
		when(userService.findUserById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/appointments/user/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testFindAllAppointmentsByDoctor() throws Exception {
		when(doctorService.findDoctorById(1L)).thenReturn(Optional.of(appointment.getDoctor()));
		when(appointmentService.getAppointmentsByDoctor(any(Doctor.class))).thenReturn(Collections.singletonList(appointment));
		when(appointmentModelAssembler.toModel(appointment)).thenReturn(appointmentDTO);

		mockMvc.perform(get("/appointments/doctor/1"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].description", is("Test Description")));
	}

	@Test
	void testFindAllAppointmentsByDoctorNotFound() throws Exception {
		when(doctorService.findDoctorById(1L)).thenReturn(Optional.empty());

		mockMvc.perform(get("/appointments/doctor/1"))
				.andExpect(status().isNotFound());
	}

	@Test
	void testUpdateAppointment() throws Exception {
		when(appointmentService.updateAppointment(eq(1L), any(Appointment.class))).thenReturn(appointment);
		when(appointmentModelAssembler.toDomain(any(AppointmentDTO.class))).thenReturn(appointment);
		when(appointmentModelAssembler.toModel(any(Appointment.class))).thenReturn(appointmentDTO);

		mockMvc.perform(put("/appointments/1")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(appointmentDTO)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.description", is("Test Description")));
	}
}
