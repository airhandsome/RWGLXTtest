package com.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.domain.Da;
import com.domain.Ds;

public class GraphService {
	
	public List<Da> getRoot(List<Da> da, List<Ds> ds){
		LinkedList<Da> result = new LinkedList();
		List<Da> temp = new LinkedList();
		temp.addAll(da);
		for(int i = 0;i<ds.size();i++){
			result.add(ds.get(i).getDaByTrueExit());
			temp.remove(ds.get(i).getDaByTrueExit());
		}
		for(int i = 0;i<temp.size();i++){
			result.offerFirst(temp.get(i));
		}
		return result;	
	}
	
}
