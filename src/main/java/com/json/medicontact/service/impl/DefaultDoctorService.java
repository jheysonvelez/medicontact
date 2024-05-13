package com.json.medicontact.service.impl;

import com.json.medicontact.model.Doctor;
import com.json.medicontact.repository.DoctorRepository;
import com.json.medicontact.service.DoctorService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DefaultDoctorService implements DoctorService
{
	private final DoctorRepository doctorRepository;

	public DefaultDoctorService(DoctorRepository doctorRepository)
	{
		this.doctorRepository = doctorRepository;
	}

	@Override
	public Optional<Doctor> findDoctorById(Long id)
	{
		return doctorRepository.findById(id);
	}
}
