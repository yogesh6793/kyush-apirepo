package com.example.kyush.serviceImpl;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.kyush.dao.Applicant;
import com.example.kyush.dao.JobApplication;
import com.example.kyush.dao.JobBoard;
import com.example.kyush.dao.JobStatus;
import com.example.kyush.repository.ApplicantRepository;
import com.example.kyush.repository.JobApplicationRepository;
import com.example.kyush.repository.JobBoardRepository;
import com.example.kyush.repository.JobStatusRepository;
import com.example.kyush.request.Application;
import com.example.kyush.service.ContactUsService;
import com.example.kyush.service.JobBoardService;

@Service
public class JobBoardServiceImpl implements JobBoardService{
	
	@Value("${location.resume}")
    private String resumeLocation;
	
	@Autowired
	private JobBoardRepository jobBoardService;
	
	@Autowired
	private ApplicantRepository applicantRepo;
	
	@Autowired
	private JobApplicationRepository jobApplicationRepo;
	
	@Autowired
	private JobBoardRepository jobBoardRepo;

	@Autowired
	private JobStatusRepository jobStatusRepo;
	
	@Autowired
	private ContactUsService contactUsService;
	
	@Override
	public List<JobBoard> getAllJobs() throws Exception {
		List<JobBoard> allJob = jobBoardService.findAll();
		return allJob;
	}

	@Override
	public Boolean sendJobApplication(MultipartFile fileData, Application applicationData) throws Exception {
		
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			
			File fileObject = convertMultiPartFileToFile(fileData);
			File savedFile = saveFileToLocation(fileObject, resumeLocation);
			
			Applicant applicant = new Applicant();
			applicant.setApplicantId(0);
			applicant.setApplicantName(applicationData.getName());
			
			LocalDate localDate = LocalDate.parse(applicationData.getBirthDate(), formatter);
			Date birthDate = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			LocalDate localDate1 = LocalDate.parse(applicationData.getStartDate(), formatter);
			Date startDate = Date.from(localDate1.atStartOfDay(ZoneId.systemDefault()).toInstant());
			
			applicant.setDob(birthDate);
			
			applicant.setAddress(applicationData.getAddress());
			applicant.setCity(applicationData.getCity());
			applicant.setState(applicationData.getState());
			applicant.setCountry(applicationData.getCountry());
			applicant.setPincode(String.valueOf(applicationData.getPincode()));
			applicant.setEmail(applicationData.getEmail());
			applicant.setPhone(applicationData.getPhone());
			applicant.setLinkedin(applicationData.getLinkedin());
			applicant.setHowHear(applicationData.getHowHear());
			applicant.setStartDate(startDate);
			applicant.setCurrentCtc(String.valueOf(applicationData.getCurrentCtc()));
			applicant.setExpectedCtc(String.valueOf(applicationData.getExpectedCtc()));
			applicant.setNoticePeriod(applicationData.getNoticePeriod());
			applicant.setResume(fileObject.getPath());
			applicant.setMessage(applicationData.getMessage());
			applicant.setDeclaration(applicationData.isDeclaration());
			applicant.setCreated_at(new Date());
			
			Applicant savedApplicant = applicantRepo.save(applicant);
			
			JobApplication jobApplication = new JobApplication();
			jobApplication.setJobApplicationId(0);
			jobApplication.setJobBoardId(applicationData.getJobBoardId());
			jobApplication.setApplicantId(savedApplicant.getApplicantId());
			jobApplication.setJobStatusId(1);
			jobApplication.setStatus(true);
			jobApplication.setCreatedBy(savedApplicant.getApplicantId());
			jobApplication.setCreatedAt(new Date());
			
			jobApplicationRepo.save(jobApplication);
			JobBoard jobBoard = jobBoardRepo.findById(applicationData.getJobBoardId()).get();
			HashMap<String, String> templateMap = new HashMap<>();
			templateMap.put("SUBJECT", "Application Received – "+jobBoard.getJobTitle());
			templateMap.put("POSITION_NAME", jobBoard.getJobTitle());
			//templateMap.put("TEMPLATE_LOCATION", "src/main/resources/templates/job-application-receipt.html");
			ClassPathResource resource = new ClassPathResource("templates/job-application-receipt.html");
			Path path = resource.getFile().toPath();
			contactUsService.sendEmail(templateMap, path, savedApplicant.getApplicantName(), savedApplicant.getEmail(), savedApplicant.getMessage());
			return true;
		} catch (Exception e) {
			return false;
		}
		
		
		
		
	}

	private File saveFileToLocation(File file, String uploadDir) throws IOException {
		File uploadDirFile = new File(uploadDir);

	    // Create the directory if it does not exist
	    if (!uploadDirFile.exists()) {
	        uploadDirFile.mkdirs();
	    }

	    File savedFile = new File(uploadDirFile, file.getName());

	    try (InputStream in = new FileInputStream(file); 
	         OutputStream out = new FileOutputStream(savedFile)) {
	        byte[] buffer = new byte[1024];
	        int bytesRead;
	        while ((bytesRead = in.read(buffer)) != -1) {
	            out.write(buffer, 0, bytesRead);
	        }
	    }

	    // Optionally, delete the temp file if not needed
	    file.delete();

	    return savedFile;
	}

	private File convertMultiPartFileToFile(MultipartFile file) {
		File convertedFile = new File(file.getOriginalFilename());

        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            System.out.println("Ecxception Caught");
        }
        return convertedFile;
	}

	@Override
	public void deleteById(int jobApplicationId) {
		jobApplicationRepo.deleteById(jobApplicationId);
		
	}

	@Override
	public List<Application> findAllJobApplication() {
		List<Application> applicationList = new ArrayList<>();
		List<JobApplication> allJobApplication = jobApplicationRepo.findAll();
		for (JobApplication jobApp : allJobApplication) {
			
			Applicant applicant = applicantRepo.findById(jobApp.getApplicantId()).get();
			JobBoard jobBoard = jobBoardRepo.findById(jobApp.getJobBoardId()).get();
			JobStatus jobStatus = jobStatusRepo.findById(jobApp.getJobStatusId()).get();
						
			Application application = new Application();
			application.setJobApplicationId(jobApp.getJobApplicationId());
			application.setJobBoardId(jobApp.getJobBoardId());
			application.setJobBoardTitle(jobBoard.getJobTitle());
			application.setJobBoardDescription(jobBoard.getJobDescription());
			application.setApplicantId(applicant.getApplicantId());
			application.setApplicantName(applicant.getApplicantName());
			application.setEmail(applicant.getEmail());
			application.setPhone(applicant.getPhone());
			application.setJobAppliedOn(jobApp.getCreatedAt());
			application.setJobStatusId(jobApp.getJobStatusId());
			application.setJobStatus(jobStatus.getJobStatus());
			application.setLinkedin(applicant.getLinkedin());
			
			applicationList.add(application);
			
		}
		
		return applicationList;
	}

	@Override
	public List<JobStatus> findAllJobStatus() {
		return jobStatusRepo.findAll();
	}

	@Override
	public Application updateJobApplicationStatus(Application application, int userId) throws Exception {
		JobApplication jobApplication = jobApplicationRepo.findById(application.getJobApplicationId()).get();
		jobApplication.setJobStatusId(application.getJobStatusId());
		jobApplication.setModifiedBy(userId);
		jobApplication.setModifiedAt(new Date());
		
		jobApplicationRepo.save(jobApplication);
		JobStatus jobStatus = jobStatusRepo.findById(application.getJobStatusId()).get();
		
		HashMap<String, String> templateMap = new HashMap<>();
		templateMap.put("SUBJECT", "Update on your job application – " + application.getJobBoardTitle());
		templateMap.put("JOB_STATUS", jobStatus.getJobStatus());
		templateMap.put("STATUS_MESSAGE", jobStatus.getMessage());
		templateMap.put("POSITION_NAME", application.getJobBoardTitle());
//		templateMap.put("TEMPLATE_LOCATION", "src/main/resources/templates/job-status-update.html");
		ClassPathResource resource = new ClassPathResource("templates/job-status-update.html");
		Path path = resource.getFile().toPath();
		contactUsService.sendEmail(templateMap, path, application.getApplicantName(), application.getEmail(), application.getJobStatus());
		
		return application;
	}

}
