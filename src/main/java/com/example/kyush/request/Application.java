package com.example.kyush.request;

import java.util.Date;

import lombok.Data;

@Data
public class Application {

	private Integer jobApplicationId;
	private Date jobAppliedOn;
	private int applicantId;
	private String applicantName;
    private int jobBoardId;
    private String jobBoardTitle;
    private String jobBoardDescription;
    private String jobBoardLocation;
    private String jobBoardExperience;
    private String jobBoardQualification;
    private String jobBoardSkills;
    private int jobStatusId;
    private String jobStatus;
    
    private String name;
    private String birthDate;
    private String address;
    private String state;
    private String city;
    private String country;
    private int pincode;
    private String email;
    private String phone;
    private String linkedin;
    private String howHear;
    private String startDate;
    private double currentCtc;
    private double expectedCtc;
    private String noticePeriod;
    private String resume;
    private String message;
    private boolean declaration;
    
}
