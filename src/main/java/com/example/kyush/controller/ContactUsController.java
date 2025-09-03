package com.example.kyush.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kyush.dao.ContactUs;
import com.example.kyush.service.ContactUsService;

@RestController
@CrossOrigin("*")
@RequestMapping("/contact")
public class ContactUsController {
	
	@Autowired
	ContactUsService contactUsService;
	
	@PostMapping("/sendMessage")
	public boolean sendMessage(@RequestBody ContactUs contactUs) throws Exception{
		
		boolean result = contactUsService.sendMessageAndEmail(contactUs);
		return result;
	}
	
	@GetMapping("/messages")
	public List<ContactUs> getAllMessage() throws Exception{
		
		List<ContactUs> messageList=contactUsService.findAllMessage();	
		
		return messageList;
	}
	
	@DeleteMapping("/deleteMessage")
	public ResponseEntity<String> deleteUser(@RequestParam("contactUsId") int contactUsId) {
	    try {
	        // Example: call your service to delete by ID
	    	contactUsService.deleteById(contactUsId);
	        return ResponseEntity.ok("Message deleted successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to delete user: " + e.getMessage());
	    }
	}

}
