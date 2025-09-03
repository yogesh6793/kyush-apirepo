package com.example.kyush.controller;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.kyush.configuration.JwtTokenUtil;
import com.example.kyush.dao.Role;
import com.example.kyush.dao.User;
import com.example.kyush.model.JwtRequest;
import com.example.kyush.model.JwtResponse;
import com.example.kyush.service.UserService;


@RestController
@CrossOrigin("*")
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	UserService userService;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() throws Exception{
		
		List<User> userList=userService.findAllUsers();	
		
		return userList;
		
	}
	
	@GetMapping("/testApi")
	public String test() throws Exception{
		
		
		return "Api working correctly";
		
	}
	
	@PostMapping("/addUpdateUser")
	public User addUpdateUser(@RequestBody User user, @RequestHeader("userId") Integer userId) throws Exception{
		
		User result = userService.saveUpdateUser(user, userId);
		return result;
		
	}
	
//	@PostMapping("/login")
//	public User userLogin(@RequestBody User user) throws Exception{
//		
//		
//		return null;
//		
//	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest){

		try {
			//logger.info("inside authenticate method.....");
			final UserDetails userDetails = jwtInMemoryUserDetailsService
					.loadUserByUsername(authenticationRequest.getUsername());

			//boolean passwordMatcher = passwordEncoder.matches(authenticationRequest.getPassword(),
			//		userDetails.getPassword());
			
			String password1 = authenticationRequest.getPassword();
			String password2 = userDetails.getPassword();
			
			

//			if (!passwordMatcher) {
//				throw new UsernameNotFoundException("Invalid userName or Password ");
//			}
			
			if (!password1.equals(password2)) {
				return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
			}

			final String token = jwtTokenUtil.generateToken(userDetails);
			final String finalToken = "Bearer " + token;

			User user = userService.findByUserName(userDetails.getUsername());		

			return ResponseEntity.ok(new JwtResponse(finalToken, user.getUserId()));
		} catch (UsernameNotFoundException e) {
	        return ResponseEntity
	                .status(HttpStatus.UNAUTHORIZED)
	                .body("Invalid username or password");
	    } catch (Exception e) {
	        return ResponseEntity
	                .status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("An error occurred during authentication");
	    }
	}
	
	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);

		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteUser(@RequestParam("userId") int userId) {
	    try {
	        // Example: call your service to delete by ID
	        userService.deleteUserById(userId);
	        return ResponseEntity.ok("User deleted successfully.");
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                             .body("Failed to delete user: " + e.getMessage());
	    }
	}

	
	@GetMapping("/roles")
	public List<Role> getAllRoles() throws Exception{
		
		List<Role> roleList=userService.findAllRole();	
		
		return roleList;
		
	}

}
