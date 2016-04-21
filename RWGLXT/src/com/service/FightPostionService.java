package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.FightPostionDAO;
import com.domain.FightPostion;
import com.domain.Users;

public class FightPostionService {
	FightPostionDAO fpdao = new FightPostionDAO();
	

	public List<Map> convert(List<FightPostion> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<FightPostion> iter = list.iterator(); 
		FightPostionService fpservice = new FightPostionService();
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			FightPostion fp = new FightPostion();
			fp = iter.next();
			map.put("id", fp.getPostionId());
			map.put("name", fp.getName());
			map.put("department", fp.getDepartment());
			map.put("post", fp.getPost());
			result.add(map);
		}
		return result;
	}
	public List<Map> getFPbyOrder(String type){
		List<FightPostion> list = new ArrayList<FightPostion>();
		List<Map> result = new ArrayList<Map>();
		list = fpdao.getFPbyOrder(type);	
		if(list == null) return null;
		else  result = convert(list);
		return result;	
	}
	
	public List<Map> getFPbySearch(String strname){
		List<FightPostion> list = new ArrayList<FightPostion>();
		List<Map> result = new ArrayList<Map>();
		list = fpdao.getFPbySearch(strname);	
		if(list == null) return null;
		else  result = convert(list);
		return result;
	}
	
	public List<Map> getFP(){
		List<FightPostion> list = new ArrayList<FightPostion>();
		List<Map> result = new ArrayList<Map>();
		list = fpdao.findAll();	
		result = convert(list);
		return result;	
	}
	
	public Integer insert(String name, String department, String post){
		Integer fpId = null;
		fpId = fpdao.insert(name, department, post).getPostionId();
		return fpId;	
	}
	
	public void delete(Integer fpId){
		fpdao.delete(fpId);	
	}
	
	public void update(Integer id, String name, String department, String post){
		fpdao.update(id, name, department, post);
	}
}
