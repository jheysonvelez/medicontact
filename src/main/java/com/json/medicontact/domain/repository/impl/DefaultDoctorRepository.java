package com.json.medicontact.domain.repository.impl;

import com.json.medicontact.domain.model.Appointment;
import com.json.medicontact.domain.model.QAppointment;
import com.json.medicontact.domain.repository.CustomDoctorRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


@Repository
public class DefaultDoctorRepository implements CustomDoctorRepository
{
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Appointment> findAppointmentsByDoctorId(Long id)
	{
		JPAQueryFactory queryFactory = new JPAQueryFactory(em);
		QAppointment appointment = QAppointment.appointment;

		return queryFactory.selectFrom(appointment)
				.where(appointment.doctor.id.eq(id))
				.fetch();
	}

}
