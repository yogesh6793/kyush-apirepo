package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.Role;

@Repository("role")
public interface RoleRepository extends JpaRepository<Role, Integer> {

}
