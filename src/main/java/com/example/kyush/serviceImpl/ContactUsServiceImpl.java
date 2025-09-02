package com.example.kyush.serviceImpl;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.kyush.dao.ContactUs;
import com.example.kyush.repository.ContactUsRepository;
import com.example.kyush.repository.UserRepository;
import com.example.kyush.service.ContactUsService;

@Service
public class ContactUsServiceImpl implements ContactUsService{
	
	@Value("${spring.mail.username}")
    private String USERNAME;
	
	@Value("${spring.mail.password}")
    private String PASSWORD;
	
	@Value("${spring.mail.properties.mail.smtp.auth}")
    private String AUTH;
	
	@Value("${spring.mail.properties.mail.smtp.starttls.enable}")
    private String STARTTLS_ENABLE;
	
	@Value("${spring.mail.host}")
    private String HOST;
	
	@Value("${spring.mail.port}")
    private String PORT;
	
	@Value("${spring.mail.properties.mail.smtp.ssl.trust}")
    private String SSL_TRUST;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private ContactUsRepository contactUsRepo;
	
	@Override
	public boolean sendMessageAndEmail(ContactUs contactUs) throws Exception {
		contactUs.setCreatedAt(new Date());
		ContactUs save = contactUsRepo.save(contactUs);
		HashMap<String, String> templateMap = new HashMap<>();
		templateMap.put("SUBJECT", "Thank You for Contacting KYush-IT Solutions");
		templateMap.put("TEMPLATE_LOCATION", "src/main/resources/templates/email-template.html");
		boolean sendEmail = sendEmail(templateMap, save.getName(), save.getEmail(), save.getMessage());
		
		if(sendEmail==true) {
			return true;
		}else {
			return false;
		}
		
	
	}
	
	@Override
	public boolean sendEmail(HashMap<String, String> templateMap, String name, String email, String content) throws Exception {
	    Properties properties = new Properties();
	    properties.put("mail.smtp.auth", AUTH);
	    properties.put("mail.smtp.starttls.enable", STARTTLS_ENABLE);
	    properties.put("mail.smtp.host", HOST);
	    properties.put("mail.smtp.port", PORT);
	    properties.put("mail.smtp.ssl.trust", SSL_TRUST);

	    Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
	        protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
	            return new javax.mail.PasswordAuthentication(USERNAME, PASSWORD);
	        }
	    });
	    String htmlTemplate = null;

	    try {
	        // Load the HTML template
	        htmlTemplate = new String(Files.readAllBytes(Paths.get(templateMap.get("TEMPLATE_LOCATION"))));
	        htmlTemplate = htmlTemplate.replace("{{name}}", name);
	        
	        if(templateMap.get("POSITION_NAME")!=null) {
	        	htmlTemplate = htmlTemplate.replace("{{positionName}}", templateMap.get("POSITION_NAME"));
	        }
	        if(templateMap.get("JOB_STATUS")!=null) {
	        	htmlTemplate = htmlTemplate.replace("{{jobStatus}}", templateMap.get("JOB_STATUS"));
	        }
	        if(templateMap.get("STATUS_MESSAGE")!=null) {
	        	htmlTemplate = htmlTemplate.replace("{{statusMessage}}", templateMap.get("STATUS_MESSAGE"));
	        }

	        Message message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(USERNAME));
	        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));
	        message.setSubject(templateMap.get("SUBJECT"));

	        // Set content type to HTML
	        message.setContent(htmlTemplate, "text/html; charset=utf-8");

	        Transport.send(message);
	        System.out.println("Sent HTML email successfully to " + email);
	        return true;

	    } catch (MessagingException | IOException e) {
	        e.printStackTrace();
	        throw new RuntimeException("Failed to send email", e);
	    }
	}


	@Override
	public List<ContactUs> findAllMessage() {
		return contactUsRepo.findAll();
	}

	@Override
	public void deleteById(int contactUsId) {
		contactUsRepo.deleteById(contactUsId);
		
	}

}
