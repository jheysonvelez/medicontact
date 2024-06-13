package com.json.medicontact.application.service;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;

import java.util.List;
import java.util.Optional;


public interface DoctorService
{
	Optional<Doctor> findDoctorById(Long id);

	List<Doctor> findAllDoctors();

	Doctor saveDoctor(Doctor doctor);

	Doctor updateDoctor(Doctor doctor);

	void deleteDoctor(Long id);

	Doctor patchDoctor(Long id, Doctor doctor);

	List<Appointment> getAppointmentsByDoctor(Long id);
}
