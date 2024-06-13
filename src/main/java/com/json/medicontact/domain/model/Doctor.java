package com.json.medicontact.domain.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;


@Entity
@Table(name = "doctors")
public class Doctor extends User
{
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "specialization")
	private String specialization;

	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;

	public String getSpecialization()
	{
		return specialization;
	}

	public void setSpecialization(String specialization)
	{
		this.specialization = specialization;
	}

	@Override
	public List<Appointment> getAppointments()
	{
		return appointments;
	}

	@Override
	public void setAppointments(List<Appointment> appointments)
	{
		this.appointments = appointments;
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
		Doctor doctor = (Doctor) o;
		return Objects.equals(specialization, doctor.specialization);
	}
}
