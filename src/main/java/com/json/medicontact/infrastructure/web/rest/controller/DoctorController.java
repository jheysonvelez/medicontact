package com.json.medicontact.infrastructure.web.rest.controller;

import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.infrastructure.web.assembler.DoctorModelAssembler;
import com.json.medicontact.infrastructure.web.dto.DoctorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/doctors")
@Tag(name = "Doctor Management", description = "Operations related to doctor management")
public class DoctorController
{
	private final DoctorService doctorService;
	private final DoctorModelAssembler doctorModelAssembler;

	public DoctorController(@Qualifier("defaultDoctorService") DoctorService doctorService,
			DoctorModelAssembler doctorModelAssembler)
	{
		this.doctorService = doctorService;
		this.doctorModelAssembler = doctorModelAssembler;
	}

	@GetMapping("/{doctorId}")
	@Operation(summary = "Get a Doctor by Id", description = "Returns a single doctor by it's Id")
	public ResponseEntity<DoctorDTO> getDoctorById(@PathVariable(value = "doctorId") Long id)
	{
		Optional<Doctor> doctor = doctorService.findDoctorById(id);
		if (doctor.isPresent())
		{
			DoctorDTO doctorDTO = doctorModelAssembler.toModel(doctor.get());
			return ResponseEntity.ok(doctorDTO);
		}
		else
		{
			return ResponseEntity.notFound().build();
		}
	}


	@GetMapping("")
	@Operation(summary = "Get all doctors", description = "find all doctors")
	public ResponseEntity<List<DoctorDTO>> findAllDoctors()
	{
		List<Doctor> doctors = doctorService.findAllDoctors();
		List<DoctorDTO> doctorDTOs = doctors.stream()
				.map(doctorModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(doctorDTOs);
	}

	@PostMapping("")
	@Operation(summary = "Create a new doctor")
	public ResponseEntity<DoctorDTO> createDoctor(@RequestBody DoctorDTO doctorDTO)
	{
		Doctor doctor = doctorModelAssembler.toDomain(doctorDTO);
		Doctor saveDoctor = doctorService.saveDoctor(doctor);
		return ResponseEntity.ok(doctorModelAssembler.toModel(saveDoctor));
	}

	@PutMapping("")
	@Operation(summary = "Update parts of an existing doctor")
	public ResponseEntity<DoctorDTO> updateDoctor(@RequestBody DoctorDTO doctorDTO)
	{
		Doctor doctor = doctorModelAssembler.toDomain(doctorDTO);
		Doctor updatedDoctor = doctorService.updateDoctor(doctor);
		return ResponseEntity.ok(doctorModelAssembler.toModel(updatedDoctor));
	}

	@DeleteMapping("/{doctorId}")
	@Operation(summary = "Delete a doctor by ID")
	public ResponseEntity<Void> removeDoctor(@PathVariable(value = "doctorId") Long id)
	{
		doctorService.deleteDoctor(id);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{doctorId}")
	@Operation(summary = "Partially update an existing doctor")
	public ResponseEntity<DoctorDTO> patchDoctor(@PathVariable(value = "doctorId") Long id, @RequestBody DoctorDTO doctorDTO)
	{
		Doctor patchedDoctor = doctorService.patchDoctor(id, doctorModelAssembler.toDomain(doctorDTO));
		return ResponseEntity.ok(doctorModelAssembler.toModel(patchedDoctor));
	}
}
