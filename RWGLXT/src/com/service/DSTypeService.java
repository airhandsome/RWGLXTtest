package com.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.DstypeDAO;
import com.dao.TypeDAO;
import com.domain.Dstype;
import com.domain.Type;

public class DSTypeService {
	DstypeDAO dstypedao = new DstypeDAO();
	
	public List<Map> convert(List<Dstype> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<Dstype> iter = list.iterator(); 
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Dstype dstype = new Dstype();
			dstype = iter.next();
			map.put("id",dstype.getDsTypeId());
			map.put("name",dstype.getName());
			result.add(map);
		}
		return result;
	}
	
	public List<Map> getDSType(){
		List <Dstype> u = dstypedao.findAll();
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
}
