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
@Table(name = "job_board")
@Data
public class JobBoard {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "job_board_id")
	private int jobBoardId;

	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "job_description")
	private String jobDescription;

	@Column(name = "location")
	private String location;

	@Column(name = "experience")
	private String experience;

	@Column(name = "qualification")
	private String qualification;

	@Column(name = "skills")
	private String skills;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "end_date")
	private Date endDate;

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
