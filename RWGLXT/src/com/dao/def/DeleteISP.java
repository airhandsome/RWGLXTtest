package com.dao.def;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;


public class DeleteISP {
	public void deleteByISPid(Integer isp_id){
		IspDAO ispdao = new IspDAO();
		ispdao.delete(isp_id);
	}
	
	public void deleteSeqByCAid(Integer ca_id){
		SequenceDAO seqdao = new SequenceDAO();
		seqdao.deleteByCa(ca_id);
	}
	
	public void deleteSeqByDAid(Integer da_id){
		SequenceDAO seqdao = new SequenceDAO();
		seqdao.deleteByDa(da_id);
	}
	
	public void deleteTriggerByid(Integer trigger_id){
		EventTriggerDAO etd = new EventTriggerDAO();
		etd.delete(trigger_id);
	
	}
	
	public void deleteCaById(Integer ca_id){
		CaDAO cadao = new CaDAO();
		cadao.delete(ca_id);
	}
	
	public void deleteDaById(Integer da_id){
		DaDAO dadao = new DaDAO();
		dadao.delete(da_id);
	}
	
	public void deleteDsById(Integer ds_id){
		DsDAO dsdao = new DsDAO();
		dsdao.delete(ds_id);
	}
}
