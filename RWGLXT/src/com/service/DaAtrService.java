package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.DaatrDAO;
import com.domain.Daatr;

public class DaAtrService {
	private DaatrDAO daatrdao = new DaatrDAO();
	
	public Integer[] getDAAtr(Integer daId){
		List<Daatr> list = new ArrayList<Daatr>(); 
		list = daatrdao.getDAAtr(daId);
		Integer[] attribute = new Integer[list.size()];
		for(int i = 0; i<list.size(); i++){
			attribute[i] = list.get(i).getId().getAtrribute().getAtrId();
		}
		return attribute;
	}
}
