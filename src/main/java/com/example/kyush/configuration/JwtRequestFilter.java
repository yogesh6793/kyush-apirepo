package com.example.kyush.configuration;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.kyush.serviceImpl.JwtUserDetailsService;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtRequestFilter extends OncePerRequestFilter{

	private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);
	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		logger.info("inside doFilterInternal method.....");
		final String requestTokenHeader = request.getHeader("Authorization");		
		final String userId = request.getHeader("userId");
		final String action=request.getRequestURI().toString();
		logger.info("checking request URI....."+action);
		System.out.println(request.getRequestURI());
		String userName = null;
		String jwtToken = null;
		// JWT Token is in the form"Bearer token". Remove Bearer word and get Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			logger.info("inside requestTokenHeader != null  method.....");
			jwtToken = requestTokenHeader.substring(7);

			try {

				// fetching user from token
				userName = jwtTokenUtil.getUsernameFromToken(jwtToken);

			}catch (IllegalArgumentException e) {
				//				errmsg="Jwt not found";
				//				anyError=true;
				throw new UsernameNotFoundException("JWT Token has expired ");

			} catch (ExpiredJwtException e) {
				//				errmsg="Jwt Expired";
				//				anyError=true;
				throw new UsernameNotFoundException("JWT Token has expired ");

			}


		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.warn("Once we get the token validate it.....");
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(userName);
			logger.warn("userdetails fetched.....");
			// if token is valid configure Spring Security to manually set authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				logger.warn("validate token.....");
				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				usernamePasswordAuthenticationToken
				.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				// After setting the Authentication in the context, we specify
				// that the current user is authenticated. So it passes the
				// Spring Security Configurations successfully.
				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
				// we are fetching role, actions etc for particular user

			} else {
				throw new UsernameNotFoundException("JWT Token has expired ");
			}
		}
		chain.doFilter(request, response);
	}
}
