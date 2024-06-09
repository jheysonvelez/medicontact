package com.json.medicontact.application.service.impl;

import com.json.medicontact.application.service.DoctorService;
import com.json.medicontact.domain.model.Doctor;
import com.json.medicontact.domain.repository.DoctorRepository;
import com.json.medicontact.domain.repository.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


@Service
@Qualifier("defaultDoctorService")
public class DefaultDoctorService extends DefaultUserService implements DoctorService
{
	private final DoctorRepository doctorRepository;

	public DefaultDoctorService(DoctorRepository doctorRepository, UserRepository userRepository)
	{
		super(userRepository);
		this.doctorRepository = doctorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Doctor> findDoctorById(Long id)
	{
		return doctorRepository.findById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Doctor> findAllDoctors()
	{
		return doctorRepository.findAll();
	}

	@Override
	@Transactional
	public Doctor saveDoctor(Doctor doctor)
	{
		return doctorRepository.save(doctor);
	}

	@Override
	@Transactional
	public Doctor updateDoctor(Doctor doctor)
	{
		validateDoctorExistsById(doctor.getId());
		return doctorRepository.save(doctor);
	}

	@Override
	@Transactional
	public void deleteDoctor(Long id)
	{
		validateDoctorExistsById(id);
		doctorRepository.deleteById(id);
	}

	@Override
	@Transactional
	public Doctor patchDoctor(Long id, Doctor doctor)
	{
		Doctor doctorExisting = doctorRepository.findById(id).orElseThrow(EntityNotFoundException::new);
		copyNonNullProperties(doctor, doctorExisting);
		return doctor;
	}

	public void copyNonNullProperties(Doctor source, Doctor target)
	{
		super.copyNonNullProperties(source, target);
		if (Objects.nonNull(source.getSpecialization()))
		{
			target.setName(source.getSpecialization());
		}
	}

	protected void validateDoctorExistsById(Long id)
	{
		if (!doctorRepository.existsById(id))
		{
			throw new EntityNotFoundException("Doctor with id " + id + " not found");
		}
	}
}
