package com.json.medicontact.application.service.impl;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.domain.repository.AppointmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.persistence.EntityNotFoundException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


class DefaultAppointmentServiceTest
{
	private AppointmentRepository appointmentRepository;
	private DefaultAppointmentService appointmentService;

	private Appointment appointment;
	private User user;
	private Doctor doctor;

	@BeforeEach
	void setUp() {
		appointmentRepository = mock(AppointmentRepository.class);
		appointmentService = new DefaultAppointmentService(appointmentRepository);

		user = new User();
		user.setId(1L);
		user.setName("Test User");

		doctor = new Doctor();
		doctor.setId(1L);
		doctor.setName("Test Doctor");

		appointment = new Appointment();
		appointment.setId(1L);
		appointment.setUser(user);
		appointment.setDoctor(doctor);
		appointment.setAppointmentDate(new Date());
		appointment.setDescription("Test Description");
	}

	@Test
	void testSaveAppointment_shouldSaveAppointment() {
		//Given
		when(appointmentRepository.save(appointment)).thenReturn(appointment);

		//When
		Appointment savedAppointment = appointmentService.saveAppointment(appointment);

		//Then
		assertNotNull(savedAppointment);
		verify(appointmentRepository, times(1)).save(appointment);
	}

	@Test
	void testGetAppointment_shouldReturnAppointment() {
		//Given
		Long appointmentId = 1L;
		when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.of(appointment));

		//When
		Appointment foundAppointment = appointmentService.getAppointment(appointmentId);

		//Then
		assertNotNull(foundAppointment);
		assertEquals(appointmentId, foundAppointment.getId());
	}

	@Test
	void testGetAppointment_shouldReturnNull() {
		//Given
		Long appointmentId = 1L;
		when(appointmentRepository.findById(appointmentId)).thenReturn(Optional.empty());

		//When
		Appointment foundAppointment = appointmentService.getAppointment(appointmentId);

		//Then
		assertNull(foundAppointment);
	}

	@Test
	void testDeleteAppointment_shouldDeleteAppointment() {
		//Given
		Long appointmentId = 1L;
		doNothing().when(appointmentRepository).deleteById(appointmentId);

		//When
		appointmentService.deleteAppointment(appointmentId);

		//Then
		verify(appointmentRepository, times(1)).deleteById(appointmentId);
	}

	@Test
	void testGetAppointmentsByUser_shouldReturnAppointments() {
		//Given
		when(appointmentRepository.findByUser(user)).thenReturn(Collections.singletonList(appointment));

		//When
		List<Appointment> appointments = appointmentService.getAppointmentsByUser(user);

		//Then
		assertNotNull(appointments);
		assertFalse(appointments.isEmpty());
		assertEquals(1, appointments.size());
	}

	@Test
	void testGetAppointmentsByDoctor_shouldReturnAppointments() {
		//Given
		when(appointmentRepository.findByDoctor(doctor)).thenReturn(Collections.singletonList(appointment));

		//When
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor);

		//Then
		assertNotNull(appointments);
		assertFalse(appointments.isEmpty());
		assertEquals(1, appointments.size());
	}

	@Test
	void testGetAllAppointments_shouldReturnAllAppointments() {
		//Given
		when(appointmentRepository.findAll()).thenReturn(Collections.singletonList(appointment));

		//When
		List<Appointment> appointments = appointmentService.getAllAppointments();

		//Then
		assertNotNull(appointments);
		assertFalse(appointments.isEmpty());
		assertEquals(1, appointments.size());
	}

	@Test
	void testUpdateAppointment_shouldUpdateAppointment() {
		//Given
		Appointment updatedAppointment = new Appointment();
		updatedAppointment.setAppointmentDate(new Date());
		updatedAppointment.setDescription("Updated Description");

		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.of(appointment));
		when(appointmentRepository.save(any(Appointment.class))).thenReturn(appointment);

		//When
		Appointment result = appointmentService.updateAppointment(1L, updatedAppointment);

		//Then
		assertNotNull(result);
		assertEquals(updatedAppointment.getDescription(), result.getDescription());
		verify(appointmentRepository, times(1)).findById(1L);
		verify(appointmentRepository, times(1)).save(appointment);
	}

	@Test
	void testUpdateAppointment_shouldThrowEntityNotFoundException() {
		//Given
		Appointment updatedAppointment = new Appointment();
		updatedAppointment.setAppointmentDate(new Date());
		updatedAppointment.setDescription("Updated Description");

		when(appointmentRepository.findById(anyLong())).thenReturn(Optional.empty());

		//When/Then
		EntityNotFoundException thrown = assertThrows(EntityNotFoundException.class, () -> {
			appointmentService.updateAppointment(1L, updatedAppointment);
		});

		assertEquals("appointment to update not found", thrown.getMessage());
		verify(appointmentRepository, times(1)).findById(1L);
		verify(appointmentRepository, never()).save(any(Appointment.class));
	}
}
