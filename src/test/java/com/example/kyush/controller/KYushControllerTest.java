package com.example.kyush.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.kyush.dao.ContactUs;
import com.example.kyush.service.ContactUsService;

@ExtendWith(MockitoExtension.class)
public class KYushControllerTest {

	@Mock
	ContactUsService contactUsService;

	@InjectMocks
	ContactUsController contactUsController;

	@Test
	void testSendMessage() throws Exception {
		ContactUs cu = new ContactUs();
		cu.setContactUsId(1);
		cu.setName("ABC");

		Boolean expectedResponse;

		expectedResponse = true;

		when(contactUsService.sendMessageAndEmail(cu)).thenReturn(expectedResponse);

		Boolean actualResponse = contactUsController.sendMessage(cu);
		assertEquals(expectedResponse, actualResponse);

	}

}
