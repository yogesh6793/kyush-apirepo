package com.example.kyush.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.User;

@Repository("users")
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query(value="select * from users where admin_flag=1",nativeQuery = true)
	List<User> findAllAdminUser();

	@Query(value="select * from users",nativeQuery = true)
	List<User> findAllUsers();

	@Query(value = "select * from users where user_name=:userName", nativeQuery = true)
	User findByUserName(@Param("userName") String userName);

}
