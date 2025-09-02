package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.Applicant;

@Repository("applicant")
public interface ApplicantRepository extends JpaRepository<Applicant, Integer> {

}
