package com.inext.rpms.repository;


import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inext.rpms.entity.ClientInfo;

@Repository
public interface ClientDao extends JpaRepository<ClientInfo, String>{
	
	@Transactional
	@Modifying
	@Query(" select pwd from ClientInfo "
			+ " where email = :email ")
	public String findPwdById(
			@Param("email") String email);

}
