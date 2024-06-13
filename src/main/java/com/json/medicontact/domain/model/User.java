package com.json.medicontact.domain.model;




import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User
{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;
	@NotNull
	@Email
	@Size(min = 3, max = 50)
	@Column(name = "email")
	private String email;
	@NotNull
	@Size(min = 3, max = 25)
	@Pattern(regexp = "\\d+", message = "Document number only contain digits")
	@Column(name = "document_number")
	private String documentNumber;
	@NotNull
	@Size(min = 3, max = 25)
	@Column(name = "document_type")
	private String documentType;
	@NotNull
	@Past
	@Column(name = "birth_date")
	private Date birthDate;
	@NotNull
	@Size(min = 2, max = 100)
	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Appointment> appointments;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
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

	public List<Appointment> getAppointments()
	{
		return appointments;
	}

	public void setAppointments(List<Appointment> appointments)
	{
		this.appointments = appointments;
	}

	@Override
	public String toString()
	{
		return "User{" +
				"id=" + id +
				", email='" + email + '\'' +
				", documentNumber='" + documentNumber + '\'' +
				", documentType='" + documentType + '\'' +
				", birthDate=" + birthDate +
				", name='" + name + '\'' +
				'}';
	}

	@Override
	public int hashCode()
	{
		return Objects.hash(id, email, documentNumber, documentType, birthDate, name);
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User) o;
		return
				Objects.equals(email, user.email) &&
						Objects.equals(documentNumber, user.documentNumber) &&
						Objects.equals(documentType, user.documentType) &&
						Objects.equals(birthDate, user.birthDate);
	}
}
