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
@Table(name = "job_application")
@Data
public class JobApplication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_application_id")
	private int jobApplicationId;

	@Column(name = "job_board_id")
	private int jobBoardId;

	@Column(name = "applicant_id")
	private int applicantId;

	@Column(name = "job_status_id")
	private int jobStatusId;

	@Column(name = "status")
	private boolean status;

	@Column(name = "created_by")
	private int createdBy;

	@Column(name = "created_at")
	private Date createdAt;

	@Column(name = "modified_by")
	private Integer modifiedBy;

	@Column(name = "modified_at")
	private Date modifiedAt;
	

}
