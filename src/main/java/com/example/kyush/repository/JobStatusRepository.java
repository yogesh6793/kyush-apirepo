package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.JobStatus;

@Repository("job_status")
public interface JobStatusRepository extends JpaRepository<JobStatus, Integer>{

}
