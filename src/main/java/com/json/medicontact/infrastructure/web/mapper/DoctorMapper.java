package com.json.medicontact.infrastructure.web.mapper;

import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.infrastructure.web.dto.DoctorDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(componentModel = "spring")
public interface DoctorMapper
{
	@Mapping(target = "id", source = "doctor.id")
	@Mapping(target = "documentNumber", source = "doctor.documentNumber")
	@Mapping(target = "documentType", source = "doctor.documentType")
	@Mapping(target = "birthDate", source = "doctor.birthDate")
	@Mapping(target = "name", source = "doctor.name")
	@Mapping(target = "email", source = "doctor.email")
	@Mapping(target = "specialization", source = "doctor.specialization")
	DoctorDTO doctorToDoctorDTO(Doctor doctor);

	@Mapping(target = "id", source = "doctor.id")
	@Mapping(target = "documentNumber", source = "doctor.documentNumber")
	@Mapping(target = "documentType", source = "doctor.documentType")
	@Mapping(target = "birthDate", source = "doctor.birthDate")
	@Mapping(target = "name", source = "doctor.name")
	@Mapping(target = "email", source = "doctor.email")
	@Mapping(target = "specialization", source = "doctor.specialization")
	Doctor doctorDTOToDoctor(DoctorDTO doctor);
}
