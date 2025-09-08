package com.example.demo.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.entity.Users;

public interface UsersRepository extends JpaRepository<Users,Integer>{
	
	@Query("SELECT u FROM Users u WHERE u.Id = :userId")
	public Users findByUserId(@Param("userId")Integer userId);
	public Users findByLoginId(String loginId);
	public Users save(Users loginUser);
}
