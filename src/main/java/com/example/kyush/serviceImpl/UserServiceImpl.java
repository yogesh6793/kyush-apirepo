package com.example.kyush.serviceImpl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.kyush.dao.Role;
import com.example.kyush.dao.User;
import com.example.kyush.repository.RoleRepository;
import com.example.kyush.repository.UserRepository;
import com.example.kyush.service.UserService;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleRepository roleRepo;

	@Override
	public List<User> findAllUsers() throws Exception {
		List<User> allUsers = new ArrayList<>();
		List<User> all = userRepo.findAll();
		for (User user : all) {
			Optional<Role> role = roleRepo.findById(user.getRoleId());
			if(role.isEmpty() || !role.isPresent()) {
				user.setRoleName("NA");
			}else {
				user.setRoleName(role.get().getRoleName());
			}
			allUsers.add(user);
		}
		
		return allUsers;
	}

	@Override
	public User saveUpdateUser(User user, Integer userId) throws Exception {
		if(user.getUserId()>0) {
			//update
			user.setModifiedBy(userId);
			user.setModifiedAt(new Date());
		}else {
			//create
			user.setCreatedBy(userId);
			user.setCreatedAt(new Date());
		}
		
		User save = userRepo.save(user);
		return save;
	}

	@Override
	public void deleteUserById(int userId) {
		userRepo.deleteById(userId);
	}

	@Override
	public List<Role> findAllRole() {
		
		return roleRepo.findAll();
	}

	@Override
	public User findByUserName(String userName) {
		User user = userRepo.findByUserName(userName);
		return user;
	}
	

}
