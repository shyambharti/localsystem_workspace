package com.ibm.user.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.user.auth.domain.User;
import java.lang.String;


public interface UserRepository extends JpaRepository<User, Integer>{

	
	User findByUsername(String username);

}
