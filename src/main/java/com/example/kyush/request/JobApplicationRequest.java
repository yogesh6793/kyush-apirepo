package com.example.kyush.request;

import java.io.File;

import lombok.Data;

@Data
public class JobApplicationRequest {

	private File file;

	private Application application;

}
