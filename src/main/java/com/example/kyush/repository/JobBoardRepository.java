package com.example.kyush.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.kyush.dao.JobBoard;

@Repository("job_board")
public interface JobBoardRepository extends JpaRepository<JobBoard, Integer> {

}
