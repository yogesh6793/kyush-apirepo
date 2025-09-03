package com.example.kyush.dao;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "job_status")
@Data
public class JobStatus {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_status_id")
	private int jobStatusId;
	
	@Column(name = "job_status")
	private String jobStatus;
	
	@Column(name = "message")
	private String message;

}
