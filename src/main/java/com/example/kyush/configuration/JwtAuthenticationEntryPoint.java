package com.example.kyush.configuration;

import java.io.IOException;
import java.io.Serial;
import java.io.Serializable;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint, Serializable{

	@Serial
	private static final long serialVersionUID = -7858869558953243875L;

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException {
        System.out.println("user request "+request.getRequestURI());
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized User");
	}

}
