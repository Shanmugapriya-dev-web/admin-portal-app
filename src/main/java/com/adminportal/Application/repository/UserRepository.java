package com.adminportal.Application.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.adminportal.Application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
	public Optional<User> findByUserNameAndPassword(String userName,String password);
	
	@Query(value = "select user_name from ts_user_master where active_ind=1 and user_id=:userId;",nativeQuery = true)
	public String getUserName(int userId);
}
