package com.elektra.domain_service.repositories;

import com.elektra.domain_service.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
