package net.edu.myapp.login.service;

import net.edu.myapp.user.dto.UserDto;

public interface LoginService {
	public UserDto login(String userEmail, String userPassword);
}
