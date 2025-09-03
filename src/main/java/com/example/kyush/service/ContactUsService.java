package com.example.kyush.service;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;

import com.example.kyush.dao.ContactUs;

public interface ContactUsService {

	boolean sendMessageAndEmail(ContactUs contactUs) throws Exception;
	
	boolean sendEmail(HashMap<String, String> templateMap, Path path, String name, String email, String content) throws Exception;

	List<ContactUs> findAllMessage();

	void deleteById(int contactUsId);

}
