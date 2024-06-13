package com.json.medicontact.infrastructure.web.mapper;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.infrastructure.web.dto.AppointmentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface AppointmentMapper
{
	@Mapping(target = "id", source = "appointment.id")
	@Mapping(target = "appointmentDate", source = "appointment.appointmentDate")
	@Mapping(target = "description", source = "appointment.description")
	@Mapping(target = "user", source = "appointment.user.email")
	@Mapping(target = "doctor", source = "appointment.doctor.email")
	AppointmentDTO appointmentToAppointmentDTO(Appointment appointment);

}
