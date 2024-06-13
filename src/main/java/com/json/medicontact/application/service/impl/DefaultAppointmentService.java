package com.json.medicontact.application.service.impl;

import com.json.medicontact.application.service.AppointmentService;
import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.domain.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;


@Service
public class DefaultAppointmentService implements AppointmentService
{
	private final AppointmentRepository appointmentRepository;

	public DefaultAppointmentService(AppointmentRepository appointmentRepository)
	{
		this.appointmentRepository = appointmentRepository;
	}

	@Override
	public Appointment saveAppointment(Appointment appointment)
	{
		return appointmentRepository.save(appointment);
	}

	@Override
	public Appointment getAppointment(Long id)
	{
		return appointmentRepository.findById(id).orElse(null);
	}

	@Override
	public void deleteAppointment(Long id)
	{
		appointmentRepository.deleteById(id);
	}

	@Override
	public List<Appointment> getAppointmentsByUser(User user)
	{
		return appointmentRepository.findByUser(user);
	}

	@Override
	public List<Appointment> getAppointmentsByDoctor(Doctor doctor)
	{
		return appointmentRepository.findByDoctor(doctor);
	}

	@Override
	public List<Appointment> getAllAppointments()
	{
		return appointmentRepository.findAll();
	}

	@Override
	public Appointment updateAppointment(Long id, Appointment appointmentUpdated)
	{
		Optional<Appointment> existingAppointment = appointmentRepository.findById(id);
		if (existingAppointment.isPresent())
		{
			Appointment appointment = existingAppointment.get();
			appointment.setAppointmentDate(appointmentUpdated.getAppointmentDate());
			appointment.setDescription(appointmentUpdated.getDescription());
			return appointmentRepository.save(appointment);
		}

		throw new EntityNotFoundException("appointment to update not found");
	}
}
