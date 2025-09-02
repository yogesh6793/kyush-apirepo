package com.example.kyush.service;

import java.util.List;

import com.example.kyush.dao.Role;
import com.example.kyush.dao.User;

public interface UserService {

	List<User> findAllUsers() throws Exception;

	User saveUpdateUser(User user, Integer userId) throws Exception;

	void deleteUserById(int userId);

	List<Role> findAllRole();

	User findByUserName(String username);

}
