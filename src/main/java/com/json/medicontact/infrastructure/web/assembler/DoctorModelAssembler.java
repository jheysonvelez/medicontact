package com.json.medicontact.infrastructure.web.assembler;

import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.infrastructure.web.dto.DoctorDTO;
import com.json.medicontact.infrastructure.web.mapper.DoctorMapper;
import com.json.medicontact.infrastructure.web.rest.controller.DoctorController;
import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class DoctorModelAssembler extends RepresentationModelAssemblerSupport<Doctor, DoctorDTO>
{
	private final DoctorMapper doctorMapper;

	public DoctorModelAssembler(DoctorMapper doctorMapper)
	{
		super(DoctorController.class, DoctorDTO.class);
		this.doctorMapper = doctorMapper;
	}

	@NonNull
	@Override
	public DoctorDTO toModel(@NonNull Doctor doctor)
	{
		DoctorDTO doctorDTO = doctorMapper.doctorToDoctorDTO(doctor);
		doctorDTO.add(linkTo(methodOn(DoctorController.class).getDoctorById(doctorDTO.getId())).withSelfRel());

		doctorDTO.add(linkTo(methodOn(DoctorController.class).updateDoctor(null)).withRel("update"));
		doctorDTO.add(linkTo(methodOn(DoctorController.class).removeDoctor(doctorDTO.getId())).withRel("delete"));
		doctorDTO.add(linkTo(methodOn(DoctorController.class).findAllDoctors()).withRel("allUsers"));
		return doctorDTO;
	}

	public Doctor toDomain(DoctorDTO doctorDTO)
	{
		return doctorMapper.doctorDTOToDoctor(doctorDTO);
	}
}
