package com.mySlipp.domain;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<userInfo, Long>{
	userInfo findByEmail(String email);
}
