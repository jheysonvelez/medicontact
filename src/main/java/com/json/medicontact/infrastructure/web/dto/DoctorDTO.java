package com.json.medicontact.infrastructure.web.dto;


import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


public class DoctorDTO extends RepresentationModel<DoctorDTO>
{
	@Size(min = 3, max = 100)
	@Column(name = "specialization")
	private String specialization;
	private Long id;
	@Email
	@Size(min = 3, max = 50)
	private String email;
	@Size(min = 3, max = 25)
	@Pattern(regexp = "\\d+", message = "Document number only contain digits")
	private String documentNumber;
	@Size(min = 3, max = 25)
	private String documentType;
	@Past
	private Date birthDate;
	@Size(min = 2, max = 100)
	private String name;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getDocumentNumber()
	{
		return documentNumber;
	}

	public void setDocumentNumber(String documentNumber)
	{
		this.documentNumber = documentNumber;
	}

	public String getDocumentType()
	{
		return documentType;
	}

	public void setDocumentType(String documentType)
	{
		this.documentType = documentType;
	}

	public Date getBirthDate()
	{
		return birthDate;
	}

	public void setBirthDate(Date birthDate)
	{
		this.birthDate = birthDate;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getSpecialization()
	{
		return specialization;
	}

	public void setSpecialization(String specialization)
	{
		this.specialization = specialization;
	}

	@Override
	public String toString()
	{
		return "Doctor{" +
				"specialization='" + specialization + '\'' +
				", id=" + getId() +
				", email='" + getEmail() + '\'' +
				", documentNumber='" + getDocumentNumber() + '\'' +
				", documentType='" + getDocumentType() + '\'' +
				", birthDate=" + getBirthDate() +
				", name='" + getName() + '\'' +
				'}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(super.hashCode(), specialization);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		if (!super.equals(o))
			return false;
		DoctorDTO doctor = (DoctorDTO) o;
		return Objects.equals(specialization, doctor.specialization);
	}
}
