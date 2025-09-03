package com.example.kyush.model;

//import java.io.Serial;
import java.io.Serializable;

import lombok.Data;

@Data
public class JwtResponse implements Serializable {

//	@Serial
	private static final long serialVersionUID = -8091879091924046844L;
	private final String jwtoken;
	private int userId;

	public JwtResponse(String jwttoken, int userId) {
		this.jwtoken = jwttoken;
		this.userId = userId;
		
	}



}