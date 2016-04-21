package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.DaatrDAO;
import com.dao.DsDAO;
import com.dao.DsatrDAO;
import com.dao.EventTriggerDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Dstype;
import com.domain.EventTrigger;
import com.domain.Sequence;

public class DSService {
	private DsDAO dsdao = new DsDAO();
	
	public List<Map> convert(List<Ds> list){
		
		List<Map> result = new ArrayList<Map>();
		Iterator<Ds> iter = list.iterator(); 
		DsAtrService dsatrservice= new DsAtrService();
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Ds ds = new Ds();
			ds = iter.next();
			map.put("id", ds.getDsId());
			map.put("name", ds.getName());
			map.put("description", ds.getDescription());
			map.put("ifdef", ds.getIfdef());
			if(ds.getDstype()!=null)
				map.put("Type",ds.getDstype().getDsTypeId());
			else
				map.put("Type",null);
			map.put("dsBelong", ds.getDsbelong());
			map.put("code", ds.getCode());
			String logic = ds.getLogic();
			if(logic!=null&&logic.contains("#")){
				String[] t = logic.split("#");
				logic = t[t.length-1];
			}
			map.put("logic", logic);
			map.put("attr", dsatrservice.getDSAtr(ds.getDsId(),ds.getLogic()));
			
			if(ds.getDaByTrueExit()!=null){
				map.put("trueExit", ds.getDaByTrueExit().getDaId());
			}
			else{
				map.put("trueExit", null);
			}
			
			if(ds.getDaByFalseExit()!=null){
				map.put("falseExit", ds.getDaByFalseExit().getDaId());
			}
			else{
				map.put("falseExit", null);
			}
			
			if(ds.getDaByPreDa()!=null){
				map.put("preDA", ds.getDaByPreDa().getDaId());
			}
			else{
				map.put("preDA", null);
			}
			
			if(ds.getCa()!=null){
				map.put("CAbelong", ds.getCa().getCaId());
			}
			else{
				map.put("CAbelong", null);
			}
			
			result.add(map);
		}
		return result;
	}
	
	public Integer insert(Integer daByTrueExitId, Integer daByFalseExitId, Integer daByPreDaId, Integer caId, Integer dstypeId, String name, String description, String code, Integer ifdef, Integer dsbelong, String logic,
			String[] attribute){
		
				String l = "";
		for(String tp : attribute){
			l+=tp+"#";
		}
		logic = l+logic;
		Integer dsId = dsdao.insertTo(daByTrueExitId, daByFalseExitId, daByPreDaId, caId, dstypeId, name, description, code, ifdef, dsbelong, logic).getDsId();

		if(attribute!=null){
			DsAtrService dsatrservice = new DsAtrService();
				dsatrservice.insertAnalyse(attribute, dsId);
		}
		
		if(daByPreDaId!=null){
			SequenceDAO seqdao = new SequenceDAO();
			seqdao.insert("DA", daByPreDaId, "DS", dsId);
		}
		return dsId;
	}
	
	public void update(Integer dsId, Integer daByTrueExitId, Integer daByFalseExitId, Integer daByPreDaId, Integer caId, Integer dstypeId, String name, String description, String code, Integer ifdef, Integer dsbelong, String logic, String[] attribute){
		
		String l = "";
		if(attribute!=null){
			DsAtrService dsatrservice = new DsAtrService();
			dsatrservice.updateAnalyse(attribute, dsId);
			for(String a:attribute){
				l = l+a+"#";
			}
		}
		logic = l + logic;
		dsdao.update(dsId, daByTrueExitId, daByFalseExitId, daByPreDaId, caId, dstypeId, name, description, code, ifdef, dsbelong, logic);
		
		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> seq = seqdao.queryByPost("DS", dsId);
		if(seq!=null&&seq.size()>0&&daByPreDaId!=null)
			seqdao.update(seq.get(0).getSequenceId(), "DA", daByPreDaId, "DS", dsId);
	
	}
	
	public int delete (Integer dsId){
		Ds ds = new Ds();
		ds = dsdao.findById(dsId);
		if(ds!=null){
			/*
			dsdao.delete(dsId);*/
			if(ds.getDaByTrueExit()==null&&ds.getDaByFalseExit()==null){
				SequenceDAO seqdao = new SequenceDAO();
				List<Sequence> postseq = seqdao.queryByPost("DS", dsId);
				if(postseq!=null&&postseq.size()>0){
					seqdao.delete(postseq.get(0).getSequenceId());
				}
				dsdao.delete(dsId);
				return 1;
			}
			else
				return 0;
		}
		return 0;
	}
	
	public List<Map> getDSbyCA(Integer caId){
		List <Ds> u = dsdao.getDSbyCa(caId);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getDSbyType(Integer dsTypeId){
		List <Ds> u = dsdao.getDSbyType(dsTypeId);
		List<Map> result = new ArrayList<Map> ();
		if (u==null) return null;
		else{
		result = this.convert(u);
		return result;
		}
	}
	
	public List<Map> getDSbyId(Integer dsId){
		List <Ds> u = new ArrayList<Ds>();
		Ds ds = dsdao.findById(dsId);
		u.add(ds);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
}
