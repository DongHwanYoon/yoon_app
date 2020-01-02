package net.edu.myapp.login.dao;

import net.edu.myapp.user.dto.UserDto;

public interface LoginDao {
	public UserDto login(String userEmail);
}
