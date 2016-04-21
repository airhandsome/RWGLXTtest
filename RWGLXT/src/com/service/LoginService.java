package com.service;

import com.dao.UsersDAO;
import com.domain.Users;

public class LoginService {

	public Users login(String username, String password){
		UsersDAO usersdao = new UsersDAO();
		return usersdao.login(username, password);
	}
}
