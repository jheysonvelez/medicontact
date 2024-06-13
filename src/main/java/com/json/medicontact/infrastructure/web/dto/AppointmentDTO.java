package com.json.medicontact.infrastructure.web.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;


public class AppointmentDTO extends RepresentationModel<AppointmentDTO>
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@NotNull
	@Column(name = "appointment_date")
	private Date appointmentDate;

	@Size(min = 0, max = 255)
	@Column(name = "description")
	private String description;

	@NotNull
	private String user;

	@NotNull
	private String doctor;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Date getAppointmentDate()
	{
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate)
	{
		this.appointmentDate = appointmentDate;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getUser()
	{
		return user;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public String getDoctor()
	{
		return doctor;
	}

	public void setDoctor(String doctor)
	{
		this.doctor = doctor;
	}
}
