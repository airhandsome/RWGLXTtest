package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.SystemsDAO;
import com.domain.Systems;

public class SystemService {
	
	private SystemsDAO sysdao = new SystemsDAO();
	
	public List<Map> convert(List<Systems> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<Systems> iter = list.iterator(); 
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Systems sys = new Systems();
			sys = iter.next();
			map.put("id",sys.getSystemId());
			map.put("name",sys.getName());
			map.put("parent", sys.getParent());
			map.put("level", sys.getLevel());
			result.add(map);
		}
		return result;
	}
	
	public List<Map> getSystem(){
		List <Systems> u = new ArrayList<Systems>(); 
		u =(List<Systems>)sysdao.findAll();
		System.out.println("safsafafaf"+u.size());
		List<Systems> y = new ArrayList<Systems>();
		for(int i=0;i<u.size();i++){
			if(u.get(i)!=null)
			if(u.get(i).getParent().intValue()==-1)
				y.add(u.get(i));
		}
		System.out.println(y.size());
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(y);
		return result;
	}
	
	public List<Map> getSystembyParent(Integer parent){
		List<Systems> u = new ArrayList<Systems>();
		u = (List<Systems>)sysdao.querySysbyParent(parent);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}
	public List<Map> getSystembySearch(String strname){
		List<Systems> u = new ArrayList<Systems>();
		u = (List<Systems>)sysdao.querySysbySearch(strname);
		List<Map> result = new ArrayList<Map>();
		if (u.size() == 0) return null;
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getSystembyOrder(String type){
		List<Systems> u = new ArrayList<Systems>();
		u = (List<Systems>)sysdao.querySysbySearch(type);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}
	public void insertSys(String sysname,Integer parent){
		sysdao.insertSys(sysname,parent,null);
	}
	
	public void updateSys(Integer sysId, String sysname, Integer parent){
		sysdao.updateSys(sysId, sysname, parent, null);
	}
}
