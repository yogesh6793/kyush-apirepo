package com.example.kyush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.kyush.dao.JobBoard;
import com.example.kyush.dao.JobStatus;
import com.example.kyush.request.Application;
import com.example.kyush.service.JobBoardService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin("*")
@RequestMapping("/job")
public class JobBoardController {
	
	@Autowired
	JobBoardService jobBoardService;
	
	@GetMapping("/getJobs")
	public List<JobBoard> getAllJob() throws Exception{
		
		List<JobBoard> jobs = jobBoardService.getAllJobs();
		return jobs;
		
	}
	
	@PostMapping("/sendJobApplication")
	public Boolean sendJobApplication(@RequestParam(value="file",required = false) MultipartFile fileData, @RequestParam("application") String application) throws Exception{
		
		ObjectMapper mapper = new ObjectMapper();
		Application applicationData = mapper.readValue(application, Application.class);
		
		Boolean jobs = jobBoardService.sendJobApplication(fileData, applicationData);
		return jobs;
		
	}
	
	@DeleteMapping("/deleteJobApplication")
	public ResponseEntity<String> deleteUser(@RequestParam("jobApplicationId") int jobApplicationId) {
	    try {
	        // Example: call your service to delete by ID
	    	jobBoardService.deleteById(jobApplicationId);
	        return ResponseEntity.ok("Message deleted successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to delete user: " + e.getMessage());
	    }
	}
	
	@GetMapping("/allJobApplication")
	public List<Application> getAllJobApplication() throws Exception{
		
		List<Application> jobApplication=jobBoardService.findAllJobApplication();	
		
		return jobApplication;
	}
	
	@GetMapping("/allJobStatus")
	public List<JobStatus> getAllJobStatus() throws Exception{
		
		List<JobStatus> jobStatus=jobBoardService.findAllJobStatus();	
		
		return jobStatus;
	}
	
	@PutMapping("/updateJobApplicationStatus")
	public Application getUpdateJobApplication(@RequestBody() Application application, @RequestHeader("userId") int userId) throws Exception{
		
		Application jobApplication=jobBoardService.updateJobApplicationStatus(application, userId);	
		
		return jobApplication;
	}
}
