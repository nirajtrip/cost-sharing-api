package com.infoaa.sharing.etracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infoaa.sharing.etracker.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
}
