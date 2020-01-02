package net.edu.myapp.login.service;

import net.edu.myapp.login.dao.LoginDao;
import net.edu.myapp.login.dao.LoginDaoImpl;
import net.edu.myapp.user.dto.UserDto;

public class LoginServiceImpl implements LoginService{
	
	@Override
	public UserDto login(String userEmail, String userPassword) {
		LoginDao loginDao = new LoginDaoImpl();
		UserDto userDto = loginDao.login(userEmail);
		
		if( userDto != null && userDto.getUserPassword().equals(userPassword)) {
			return userDto;
		}else {
			return null;
		}
	}
}
