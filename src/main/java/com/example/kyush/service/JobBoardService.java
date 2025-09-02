package com.example.kyush.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.kyush.dao.JobBoard;
import com.example.kyush.dao.JobStatus;
import com.example.kyush.request.Application;

public interface JobBoardService {

	List<JobBoard> getAllJobs() throws Exception;

	Boolean sendJobApplication(MultipartFile fileData, Application applicationData) throws Exception;

	void deleteById(int jobApplicationId);

	List<Application> findAllJobApplication();

	List<JobStatus> findAllJobStatus();

	Application updateJobApplicationStatus(Application application, int userId) throws Exception;

}
