package com.json.medicontact.controller;

import com.json.medicontact.model.Doctor;
import com.json.medicontact.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/")
public class LandingController
{
	@Autowired
	DoctorRepository doctorRepository;

	@GetMapping
	public String hello() {
		return "Hola tonota te quiero";
	}
}
