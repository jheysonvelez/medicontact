package com.json.medicontact.application.service.impl;



import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.repository.DoctorRepository;
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


class DefaultDoctorServiceTest
{
	private DoctorRepository doctorRepository;
	private DefaultDoctorService doctorService;

	@BeforeEach
	void setUp()
	{
		doctorRepository = mock(DoctorRepository.class);
		UserRepository userRepository = mock(UserRepository.class);
		doctorService = new DefaultDoctorService(doctorRepository, userRepository);
	}

	@Test
	void testFindDoctorById_shouldFindDoctor()
	{
		// Given
		Long doctorId = 1L;
		Doctor doctor = new Doctor();
		doctor.setId(doctorId);
		when(doctorRepository.findById(doctorId)).thenReturn(Optional.of(doctor));

		// When
		Optional<Doctor> foundDoctor = doctorService.findDoctorById(doctorId);

		// Then
		assertNotNull(foundDoctor.get());
		assertEquals(doctorId, foundDoctor.get().getId());
	}

	@Test
	void testFindDoctorById_shouldReturnEmpty()
	{
		// Given
		Long doctorId = 1L;
		when(doctorRepository.findById(doctorId)).thenReturn(Optional.empty());

		// When/Then
		Optional<Doctor> doctorById = doctorService.findDoctorById(doctorId);

		assertEquals(Optional.empty(), doctorById);
	}

	@Test
	void testSaveDoctor_shouldSaveDoctor()
	{
		// Given
		Doctor Doctor = new Doctor();
		when(doctorRepository.save(Doctor)).thenReturn(Doctor);

		// When
		Doctor saveddoctor = doctorService.saveDoctor(Doctor);

		// Then
		assertNotNull(saveddoctor);
		verify(doctorRepository, times(1)).save(Doctor);
	}

	@Test
	void testUpdateDoctor_shouldUpdateDoctor()
	{
		// Given
		Doctor Doctor = new Doctor();
		Doctor.setId(1L);
		when(doctorRepository.existsById(Doctor.getId())).thenReturn(true);
		when(doctorRepository.save(Doctor)).thenReturn(Doctor);

		// When
		Doctor updateddoctor = doctorService.updateDoctor(Doctor);

		// Then
		assertNotNull(updateddoctor);
		verify(doctorRepository, times(1)).save(Doctor);
	}

	@Test
	void testUpdateDoctor_shouldThrowNotFoundException()
	{
		// Given
		Doctor Doctor = new Doctor();
		Doctor.setId(1L);
		when(doctorRepository.existsById(Doctor.getId())).thenReturn(false);

		// When/Then
		assertThrows(EntityNotFoundException.class, () -> doctorService.updateDoctor(Doctor));
	}

	@Test
	void testDeleteDoctor_shouldDeleteDoctor()
	{
		// Given
		Long doctorId = 1L;
		when(doctorRepository.existsById(doctorId)).thenReturn(true);

		// When
		doctorService.deleteDoctor(doctorId);

		// Then
		verify(doctorRepository, times(1)).deleteById(doctorId);
	}

	@Test
	void testDeleteDoctor_shouldThrowNoSuchElementException()
	{
		// Given
		Long doctorId = 1L;
		when(doctorRepository.existsById(doctorId)).thenReturn(false);

		// When/Then
		assertThrows(EntityNotFoundException.class, () -> doctorService.deleteDoctor(doctorId));
	}

	@Test
	void testFindAllDoctors()
	{
		// Given
		List<Doctor> doctors = Arrays.asList(new Doctor(), new Doctor());
		when(doctorRepository.findAll()).thenReturn(doctors);

		// When
		List<Doctor> foundDctors = doctorService.findAllDoctors();

		// Then
		assertNotNull(foundDctors);
		assertEquals(2, foundDctors.size());
	}
}
