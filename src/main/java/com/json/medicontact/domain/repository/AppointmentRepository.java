package com.json.medicontact.domain.repository;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AppointmentRepository extends JpaRepository<Appointment, Long>
{
	List<Appointment> findByUser(User user);

	List<Appointment> findByDoctor(Doctor doctor);
}
