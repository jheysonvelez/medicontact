package com.json.medicontact.domain.repository;

import com.json.medicontact.domain.model.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User, Long>
{
}
