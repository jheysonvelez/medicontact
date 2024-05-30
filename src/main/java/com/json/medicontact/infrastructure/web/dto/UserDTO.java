package com.json.medicontact.infrastructure.web.dto;

import org.springframework.hateoas.RepresentationModel;

import javax.validation.constraints.Email;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;


public class UserDTO extends RepresentationModel<UserDTO>
{
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

	@Override
	public String toString() {
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
	public int hashCode() {
		return Objects.hash(id, email, documentNumber, documentType, birthDate, name);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		UserDTO user = (UserDTO) o;
		return
				Objects.equals(email, user.email) &&
						Objects.equals(documentNumber, user.documentNumber) &&
						Objects.equals(documentType, user.documentType) &&
						Objects.equals(birthDate, user.birthDate);
	}
}
