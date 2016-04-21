package com.service.execute;

import java.sql.Timestamp;
import java.util.List;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.IspDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Isp;

public class TimeExecute {
	public boolean checkTime(){
		boolean result1 = checkISPbyTime();
		boolean result2 = checkCAbyTime();
		boolean result3 = checkDAbyTime();

		return result1||result2||result3;
	}
	public boolean checkISPbyTime(){
		boolean result = false;
		IspDAO ispdao = new IspDAO();
		List<Isp> isplist = null;
		//check the isps which have reach the time
		isplist = ispdao.getISPbyTime();
		if(isplist!=null){
			//if isps are exist, change the state
			for(Isp tpisp : isplist){
				tpisp.setExcuteStarttime(new Timestamp(System.currentTimeMillis()));
				ispdao.changeState(tpisp, "Executing");
			}
			CaDAO cadao = new CaDAO();
			for (Isp isp : isplist) {
				cadao.changeStateByISP(isp.getIspId(), "TriggerPending");
			}
//			for(Isp tpisp:isplist){
//				if(tpisp.getIfAuto()==0)
//					ispdao.changeState(tpisp, "AffirmPending");
//				else
//					ispdao.changeState(tpisp, "Executing");
//			}
			result = true; 
		}
		return result;
	}
	
	public boolean checkCAbyTime(){
		boolean result = false;
		CaDAO cadao = new CaDAO();
		List<Ca> calist = null;
		calist = cadao.getCAbyTime();
		if(calist!=null){
			DaDAO dadao = new DaDAO();
			for(Ca tpca: calist){
				tpca.setExcuteStarttime(new Timestamp(System.currentTimeMillis()));
				cadao.changeState(tpca, "Executing");
				dadao.changeStateByCA(tpca.getCaId(), "TriggerPending");
//				if(tpca.getIfAuto()==0)
//					cadao.changeState(tpca, "AffirmPending");
//				else
//					cadao.changeState(tpca, "Executing");
			}
			result = true;
	    }
		return result;
	}
	
	public boolean checkDAbyTime(){
		boolean result = false;
		DaDAO dadao = new DaDAO();
		List<Da> dalist = null;
		dalist = dadao.getDAbyTime();
		System.out.println(dalist.size());
		if(dalist!=null){
			for(Da tpda : dalist){
				if(tpda.getIfAuto()==0){
					dadao.changeState(tpda, "AffirmPending");
					System.out.println("time da affirm");
				}
				else {
					tpda.setExcuteStart(new Timestamp(System.currentTimeMillis()));
					System.out.println("time da executing");
					dadao.changeState(tpda, "Executing");
				}
			}
			result = true;
		}
		return result;
	}
}
