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
@Table(name = "applicant")
@Data
public class Applicant {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "applicant_id")
	private int applicantId;

	@Column(name = "applicant_name")
	private String applicantName;

	@Column(name = "dob")
	private Date dob;

	@Column(name = "address")
	private String address;

	@Column(name = "city")
	private String city;

	@Column(name = "state")
	private String state;

	@Column(name = "country")
	private String country;

	@Column(name = "pincode")
	private String pincode;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "linkedin")
	private String linkedin;

	@Column(name = "how_hear")
	private String howHear;

	@Column(name = "start_date")
	private Date startDate;

	@Column(name = "current_ctc")
	private String currentCtc;

	@Column(name = "expected_ctc")
	private String expectedCtc;

	@Column(name = "notice_period")
	private String noticePeriod;

	@Column(name = "resume")
	private String resume;

	@Column(name = "message")
	private String message;
	
	@Column(name = "declaration")
	private boolean declaration;

	@Column(name = "created_at")
	private Date created_at;
}
