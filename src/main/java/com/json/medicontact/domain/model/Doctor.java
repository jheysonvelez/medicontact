package com.json.medicontact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "doctors")
public class Doctor extends User
{
	@NotNull
	@Size(min = 3, max = 100)
	@Column(name = "specialization")
	private String specialization;

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
		Doctor doctor = (Doctor) o;
		return Objects.equals(specialization, doctor.specialization);
	}
}
