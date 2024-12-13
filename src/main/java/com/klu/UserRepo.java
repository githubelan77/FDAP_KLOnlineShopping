package com.klu;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, Long> {
	public Optional findById(Long uid);

	public User findByuname(String uname); 
}
