package com.service.extr;

import com.domain.Users;
import com.domain.extr.UserInfor;

public class UserInforTransService {
	public UserInfor Transformation(Users user){
		UserInfor userinfor = new UserInfor();
		userinfor.setUserid(user.getUserid());
		userinfor.setPassword(user.getPassword());
		userinfor.setDepartment(user.getDepartment());
		if(user.getFightPostion()!=null){
			userinfor.setPositionId(user.getFightPostion().getPostionId());
			userinfor.setPositionName(user.getFightPostion().getName());
		}
		userinfor.setPost(user.getPost());
		userinfor.setRole(user.getRole());
		userinfor.setUsername(user.getUsername());
		return userinfor;
	}
}
