package com.json.medicontact.domain.repository;

import com.json.medicontact.domain.model.Appointment;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomDoctorRepository
{
	@Query("SELECT a FROM Appointment a WHERE a.doctor.id = :id")
	List<Appointment> findAppointmentsByDoctorId(Long id);
}
