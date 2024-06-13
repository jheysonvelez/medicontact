package com.json.medicontact.infrastructure.web.assembler;

import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.infrastructure.web.controller.AppointmentController;
import com.json.medicontact.infrastructure.web.dto.AppointmentDTO;
import com.json.medicontact.infrastructure.web.mapper.AppointmentMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;


@Component
public class AppointmentModelAssembler extends RepresentationModelAssemblerSupport<Appointment, AppointmentDTO>
{
	private final AppointmentMapper appointmentMapper;
	private final UserService userService;
	private final DoctorService doctorService;

	public AppointmentModelAssembler(AppointmentMapper appointmentMapper, @Qualifier("defaultUserService") UserService userService,
			DoctorService doctorService)
	{
		super(AppointmentController.class, AppointmentDTO.class);
		this.appointmentMapper = appointmentMapper;
		this.userService = userService;
		this.doctorService = doctorService;
	}

	@NonNull
	@Override
	public AppointmentDTO toModel(@NonNull Appointment appointment)
	{
		return appointmentMapper.appointmentToAppointmentDTO(appointment);
	}

	public Appointment toDomain(AppointmentDTO appointmentDTO)
	{
		Appointment appointment = new Appointment();
		appointment.setId(appointmentDTO.getId());
		appointment.setDescription(appointmentDTO.getDescription());
		appointment.setAppointmentDate(appointmentDTO.getAppointmentDate());
		appointment.setUser(userService.findUserById(Long.valueOf(appointmentDTO.getUser())).orElseThrow());
		appointment.setDoctor(doctorService.findDoctorById(Long.valueOf(appointmentDTO.getDoctor())).orElseThrow());
		return appointment;
	}
}
