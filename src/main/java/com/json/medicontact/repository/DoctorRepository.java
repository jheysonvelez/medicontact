package com.json.medicontact.repository;

import com.json.medicontact.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DoctorRepository extends JpaRepository<Doctor, Long>
{

}
