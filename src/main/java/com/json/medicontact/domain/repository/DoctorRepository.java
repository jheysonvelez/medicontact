package com.json.medicontact.domain.repository;

import com.json.medicontact.domain.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Long>
{
}
