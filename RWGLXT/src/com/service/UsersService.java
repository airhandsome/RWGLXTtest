package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.UsersDAO;
import com.domain.Da;
import com.domain.Users;
import com.domain.extr.UserInfor;
import com.service.extr.UserInforTransService;

public class UsersService {
	UsersDAO usersdao = new UsersDAO();
	
	public List<Map> convert(List<Users> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<Users> iter = list.iterator(); 
		UsersService usersservice = new UsersService();
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Users users = new Users();
			users = iter.next();
			map.put("id", users.getUserid());
			map.put("username", users.getUsername());
			map.put("password", users.getPassword());
			map.put("role", users.getRole());
			map.put("post", users.getPost());
			map.put("department", users.getDepartment());
			result.add(map);
		}
		return result;
	}
	
	public Integer insert(String name, String password, String department, String role, String post, Integer positionId){
		Integer usersId = null;
		usersId = usersdao.insert(name, password, department, role, post, positionId).getUserid();
		return usersId;	
	}
	
	public void delete(Integer usersId){
		usersdao.delete(usersId);	
	}
	
	public List<Map> getUser(){
		List<Users> list = new ArrayList<Users>();
		List<UserInfor> list1 = new ArrayList<UserInfor>();
		UserInforTransService ut = new UserInforTransService();
		list = usersdao.findAll();	
		for(Users user:list){
			list1.add(ut.Transformation(user));
		}
		
		List<Map> result = new ArrayList<Map> ();
		Iterator<UserInfor> iter = list1.iterator(); 
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			UserInfor users = new UserInfor();
			users = iter.next();
			map.put("id", users.getUserid());
			map.put("username", users.getUsername());
			map.put("password", users.getPassword());
			map.put("role", users.getRole());
			map.put("post", users.getPost());
			map.put("department", users.getDepartment());
			map.put("positionId",users.getPositionId());
			map.put("positionName", users.getPositionName());
			result.add(map);
		}
		return result;
	}
	public List<Map> getUserbySearch(String strname){
		List<Users> list = new ArrayList<Users>();
		List<UserInfor> list1 = new ArrayList<UserInfor>();
		UserInforTransService ut = new UserInforTransService();
		list = usersdao.getUserbySearch(strname);
		if (list.size() == 0){
			return null;
		}
		for(Users user:list){
			list1.add(ut.Transformation(user));
		}
		
		List<Map> result = new ArrayList<Map> ();
		Iterator<UserInfor> iter = list1.iterator(); 
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			UserInfor users = new UserInfor();
			users = iter.next();
			map.put("id", users.getUserid());
			map.put("username", users.getUsername());
			map.put("password", users.getPassword());
			map.put("role", users.getRole());
			map.put("post", users.getPost());
			map.put("department", users.getDepartment());
			map.put("positionId",users.getPositionId());
			map.put("positionName", users.getPositionName());
			result.add(map);
		}
		return result;
	}
	
	public List<Map> getUserbyOrder(String type){
		List<Users> list = new ArrayList<Users>();
		List<UserInfor> list1 = new ArrayList<UserInfor>();
		UserInforTransService ut = new UserInforTransService();
		list = usersdao.getUserbyOrder(type);	
		for(Users user:list){
			list1.add(ut.Transformation(user));
		}
		
		List<Map> result = new ArrayList<Map> ();
		Iterator<UserInfor> iter = list1.iterator(); 
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			UserInfor users = new UserInfor();
			users = iter.next();
			map.put("id", users.getUserid());
			map.put("username", users.getUsername());
			map.put("password", users.getPassword());
			map.put("role", users.getRole());
			map.put("post", users.getPost());
			map.put("department", users.getDepartment());
			map.put("positionId",users.getPositionId());
			map.put("positionName", users.getPositionName());
			result.add(map);
		}
		return result;
	}
	public void update(Integer usersId, String name, String password, String department, String role, String post, Integer positionId){
		usersdao.update(usersId, name, password, department, role, post, positionId);	
	}
}
