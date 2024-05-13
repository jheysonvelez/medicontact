package com.json.medicontact.service;

import com.json.medicontact.model.Doctor;

import java.util.Optional;


public interface DoctorService
{
	Optional<Doctor> findDoctorById(Long id);

}
