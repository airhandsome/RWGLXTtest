package com.service.execute;

import java.util.List;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.IspDAO;
import com.domain.Isp;

public class TriggerISPService {
	public void TriggerEventISP(Integer event){
		IspDAO ispdao = new IspDAO();
		List<Isp> list = ispdao.getEventISP(event);
		ispdao.updateEventISP(list);
		for(int i=0;i<list.size();i++){
			executeFollowCA(list.get(i).getIspId(), "TriggerPending");
		}
	}
	
	public void executeFollowCA(Integer ispId, String state){
		CaDAO cadao = new CaDAO();
		cadao.changeStateByISP(ispId, state);
	}
	
	public void executeFollowDA(Integer caId, String state){
		DaDAO dadao = new DaDAO();
		dadao.changeStateByCA(caId, state);
	}
}
