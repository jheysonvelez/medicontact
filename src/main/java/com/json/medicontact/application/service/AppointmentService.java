package com.json.medicontact.application.service;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;

import java.util.List;


public interface AppointmentService
{
	public Appointment saveAppointment(Appointment appointment);

	public Appointment getAppointment(Long id);

	public void deleteAppointment(Long id);

	public List<Appointment> getAppointmentsByUser(User user);

	public List<Appointment> getAppointmentsByDoctor(Doctor doctor);

	public List<Appointment> getAllAppointments();

	public Appointment updateAppointment(Long id, Appointment appointment);
}
