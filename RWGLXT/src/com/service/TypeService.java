package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.TypeDAO;
import com.domain.Systems;
import com.domain.Type;

public class TypeService {
	TypeDAO typedao = new TypeDAO();
	
	public List<Map> convert(List<Type> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<Type> iter = list.iterator(); 
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Type type = new Type();
			type = iter.next();
			map.put("id",type.getTypeId());
			map.put("name",type.getName());
			result.add(map);
		}
		return result;
	}
	
	public List<Map> getType(){
		List <Type> u = typedao.findAll();
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
}

