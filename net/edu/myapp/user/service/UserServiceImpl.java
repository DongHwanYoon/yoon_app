package net.edu.myapp.user.service;

import net.edu.myapp.user.dao.UserDao;
import net.edu.myapp.user.dao.UserDaoImpl;
import net.edu.myapp.user.dto.UserDto;

public class UserServiceImpl implements UserService{
	
	@Override
	public int userRegister(UserDto userDto) {
		UserDao userDao = new UserDaoImpl();
		return userDao.userRegister(userDto);
	}
}
