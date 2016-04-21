package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DaatrDAO;
import com.dao.DsDAO;
import com.dao.DsatrDAO;
import com.dao.DstypeDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Dstype;

public class SequenceService {
	
	SequenceDAO sequencedao = new SequenceDAO();
	DaDAO dadao = new DaDAO();
	CaDAO cadao = new CaDAO();
	DsDAO dsdao = new DsDAO();
	DaatrDAO daatrdao = new DaatrDAO();
	//SequenceService sequenceservice = new SequenceService();
	DAService daservice = new DAService();
	
	public List<Map> getDAPost(Integer daId){
		Object[] obj = new Object[2];
		List<Map> result = new ArrayList<Map>();
		obj = sequencedao.getDAPost(daId);
		if(obj != null){
			String postType = (String) obj[0];
			Integer post = (Integer) obj[1];
			if(postType.equals("DA")){
				Da da = dadao.findById(post);
				List <Da> DAlist = new ArrayList<Da>() ;
				DAlist.add(da);
				result = daservice.convert(DAlist);
			}
			else if((postType.equals("CA"))){
				Ca ca = cadao.findById(post);
				//toconvert
			}
			else if((postType.equals("DS"))){
				Ds ds = dsdao.findById(post);
				//toconvert
			}
			else
				return null;
			
			return result;
		}
		else
			return null;
	}
	
	public List<Map> getCAPost(Integer caId){
		Object[] obj = new Object[2];
		List<Map> result = new ArrayList<Map>();
		obj = sequencedao.getDAPost(caId);
		if(obj != null){
			String postType = (String) obj[0];
			Integer post = (Integer) obj[1];
			if(postType.equals("DA")){
				Da da = dadao.findById(post);
				List <Da> DAlist = new ArrayList<Da>() ;
				DAlist.add(da);
				result = daservice.convert(DAlist);
			}
			else if((postType.equals("CA"))){
				Ca ca = cadao.findById(post);
				//toconvert
			}
			else if((postType.equals("DS"))){
				Ds ds = dsdao.findById(post);
				//toconvert
			}
			else
				return null;
			
			return result;
		}
		else
			return null;
	}
	
	public Integer getDAPre(Integer daId){
		SequenceDAO seqdao = new SequenceDAO();
		return seqdao.getDAPre(daId);
	}
}
