package com.example.kyush.serviceImpl;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.kyush.dao.User;
import com.example.kyush.service.UserService;



@Service
public class JwtUserDetailsService implements UserDetailsService{

	private static final Logger logger = LoggerFactory.getLogger(JwtUserDetailsService.class);

	@Autowired
	@Lazy
	UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userService.findByUserName(username);
		if (user == null) {
			throw new UsernameNotFoundException("User doesn't exist");
		}
		if (!user.getStatus()) {
			throw new UsernameNotFoundException("User is Inactive");
		}
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				new ArrayList<>());
	}

}

