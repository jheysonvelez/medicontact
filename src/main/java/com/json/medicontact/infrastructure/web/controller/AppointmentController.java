package com.json.medicontact.infrastructure.web.controller;

import com.json.medicontact.application.service.AppointmentService;
import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.application.service.UserService;
import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.model.User;
import com.json.medicontact.infrastructure.web.assembler.AppointmentModelAssembler;
import com.json.medicontact.infrastructure.web.dto.AppointmentDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/appointments")
@Tag(name = "Appointment management", description = "Operations related to appointment management")
public class AppointmentController
{
	private final AppointmentService appointmentService;
	private final UserService userService;
	private final DoctorService doctorService;
	private final AppointmentModelAssembler appointmentModelAssembler;

	public AppointmentController(AppointmentService appointmentService, @Qualifier("defaultUserService") UserService userService,
			@Qualifier("defaultDoctorService") DoctorService doctorService,
			AppointmentModelAssembler appointmentModelAssembler)
	{
		this.appointmentService = appointmentService;
		this.userService = userService;
		this.doctorService = doctorService;
		this.appointmentModelAssembler = appointmentModelAssembler;
	}

	@GetMapping
	@Operation(summary = "get all appointments", description = "get all appointments")
	public ResponseEntity<List<AppointmentDTO>> findAllAppointments()
	{
		List<Appointment> appointments = appointmentService.getAllAppointments();
		List<AppointmentDTO> appointmentDTOs = appointments.stream()
				.map(appointmentModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(appointmentDTOs);
	}

	@GetMapping("/{appointmentId}")
	@Operation(summary = "find appointment by its ID")
	public ResponseEntity<AppointmentDTO> findAppointmentById(@PathVariable(value = "appointmentId") Long id)
	{
		Appointment appointment = appointmentService.getAppointment(id);
		return ResponseEntity.ok(appointmentModelAssembler.toModel(appointment));
	}

	@PostMapping
	@Operation(summary = "Create a new appointment")
	public ResponseEntity<AppointmentDTO> createAppointment(@RequestBody AppointmentDTO appointmentDTO)
	{
		Appointment appointment = appointmentModelAssembler.toDomain(appointmentDTO);
		Appointment savedAppointment = appointmentService.saveAppointment(appointment);
		return ResponseEntity.ok(appointmentModelAssembler.toModel(savedAppointment));
	}

	@DeleteMapping("/{appointmentId}")
	@Operation(summary = "Delete appointment by its ID")
	public ResponseEntity<Void> removeAppointment(@PathVariable(value = "appointmentId") Long id)
	{
		appointmentService.deleteAppointment(id);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/user/{userId}")
	@Operation(summary = "get all appointments by user id")
	public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByUser(@PathVariable(value = "userId") Long userId)
	{
		Optional<User> user = userService.findUserById(userId);
		if (user.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		List<Appointment> appointments = appointmentService.getAppointmentsByUser(user.get());
		List<AppointmentDTO> appointmentDTOs = appointments.stream()
				.map(appointmentModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(appointmentDTOs);
	}

	@GetMapping("/doctor/{doctorId}")
	@Operation(summary = "get all appointments by doctor id")
	public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByDoctor(@PathVariable(value = "doctorId") Long doctorId)
	{
		Optional<Doctor> doctor = doctorService.findDoctorById(doctorId);
		if (doctor.isEmpty())
		{
			return ResponseEntity.notFound().build();
		}
		List<Appointment> appointments = appointmentService.getAppointmentsByDoctor(doctor.get());
		List<AppointmentDTO> appointmentDTOs = appointments.stream()
				.map(appointmentModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(appointmentDTOs);
	}

	@GetMapping("/doctor/qdsl/{id}")
	@Operation(summary = "get all appointments by doctor id")
	public ResponseEntity<List<AppointmentDTO>> findAllAppointmentsByDoctorQueryDSL(
			@PathVariable(value = "id") Long id)
	{
		List<Appointment> appointmentsByDoctor = doctorService.getAppointmentsByDoctor(id);
		List<AppointmentDTO> appointmentDTOs = appointmentsByDoctor.stream()
				.map(appointmentModelAssembler::toModel)
				.toList();
		return ResponseEntity.ok(appointmentDTOs);
	}

	@PutMapping("/{id}")
	@Operation(summary = "update appointment")
	public ResponseEntity<Appointment> updateAppointment(@PathVariable Long id, @RequestBody AppointmentDTO updatedAppointment)
	{
		Appointment appointment = appointmentService.updateAppointment(id, appointmentModelAssembler.toDomain(updatedAppointment));
		return appointment != null ? ResponseEntity.ok(appointment) : ResponseEntity.notFound().build();
	}
}
