package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.JobApplication;

@Repository("job_application")
public interface JobApplicationRepository extends JpaRepository<JobApplication, Integer> {

}
