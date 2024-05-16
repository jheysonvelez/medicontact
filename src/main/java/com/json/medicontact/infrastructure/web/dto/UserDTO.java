package com.json.medicontact.infrastructure.web.dto;

import java.util.Date;


public class UserDTO
{
	private Long id;
	private String documentNumber;
	private String documentType;
	private Date birthDate;
	private String name;
	private String email;

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
}
