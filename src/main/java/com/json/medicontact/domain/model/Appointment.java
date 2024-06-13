package com.json.medicontact.domain.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name = "appointment")
public class Appointment
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

	@ManyToOne
	@NotNull
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@ManyToOne
	@NotNull
	@JoinColumn(name = "doctor_id", nullable = false)
	private Doctor doctor;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public @NotNull Date getAppointmentDate()
	{
		return appointmentDate;
	}

	public void setAppointmentDate(@NotNull Date appointmentDate)
	{
		this.appointmentDate = appointmentDate;
	}

	public @Size(min = 0, max = 255) String getDescription()
	{
		return description;
	}

	public void setDescription(@Size(min = 0, max = 255) String description)
	{
		this.description = description;
	}

	public @NotNull User getUser()
	{
		return user;
	}

	public void setUser(@NotNull User user)
	{
		this.user = user;
	}

	public @NotNull Doctor getDoctor()
	{
		return doctor;
	}

	public void setDoctor(@NotNull Doctor doctor)
	{
		this.doctor = doctor;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Appointment that = (Appointment) o;
		return Objects.equals(id, that.id) &&
				Objects.equals(appointmentDate, that.appointmentDate) &&
				Objects.equals(doctor, that.doctor) &&
				Objects.equals(user, that.user);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, appointmentDate, doctor, user);
	}

	@Override
	public String toString() {
		return "Appointment{" +
				"id=" + id +
				", appointmentDate=" + appointmentDate +
				", description='" + description + '\'' +
				", user=" + user + '\'' +
				", doctor=" + doctor +
				'}';
	}
}
