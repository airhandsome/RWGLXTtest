package com.service.execute;

import java.util.List;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.IspDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Isp;

public class ExecuteCAandISPService {
	public void executeFinishCA(Da da){
		DaDAO dadao = new DaDAO();
		if(da.getCa()!=null){
			Integer caid = da.getCa().getCaId();
			List<Da> dalist = dadao.getDAbyCa(caid);
			boolean finish = true;
			for(Da tpda : dalist){
				if(!tpda.getState().equals("Finish")){
					finish = false;
					break;
				}
			}
			if(finish){
				CaDAO cadao = new CaDAO();
				Ca tpca = cadao.findById(caid);
				cadao.changeState(tpca, "Finish");
				executeFinishISP(tpca);
			}
		}
	}
	
	public void executeFinishISP(Ca ca){
		if(ca.getIsp()!=null){
			CaDAO cadao = new CaDAO();
			Integer ispid = ca.getIsp().getIspId();
			List<Ca> calist = cadao.getCAbyISP(ispid);
			boolean finish = true;
			for(Ca tpca : calist){
				if(!tpca.getState().equals("Finish")){
					finish = false;
					break;
				}
			}
			if(finish){
				IspDAO ispdao = new IspDAO();
				Isp tpisp = ispdao.findById(ispid);
				ispdao.changeState(tpisp, "Finish");
			}
		}
	}
}
