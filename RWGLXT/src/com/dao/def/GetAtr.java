package com.dao.def;

import java.util.ArrayList;
import java.util.List;

import com.dao.DaatrDAO;
import com.dao.DsatrDAO;
import com.dao.TriggerAtrDAO;
import com.domain.Daatr;
import com.domain.Dsatr;
import com.domain.TriggerAtr;
import com.domain.def.DAAtrforCopy;
import com.domain.def.DSAtrforCopy;
import com.domain.def.TriggerAtrforCopy;

public class GetAtr {
	public List<DAAtrforCopy> queryDAAtr(Integer DA_id) {
		DaatrDAO daatrdao = new DaatrDAO();
		List<Daatr> daatrO = new ArrayList<Daatr>();
		List<DAAtrforCopy> list = new ArrayList<DAAtrforCopy>();
		daatrO = daatrdao.getDAAtr(DA_id);

		for (int i = 0; i < daatrO.size(); i++) {
			DAAtrforCopy daatr = new DAAtrforCopy();
			daatr.setAtr_id((Integer) daatrO.get(i).getId().getAtrribute()
					.getAtrId());
			daatr.setDA_id(DA_id);
			daatr.setValue((Float) daatrO.get(i).getValue());
			list.add(daatr);
		}
		return list;
	}

	public List<DSAtrforCopy> queryDSAtr(Integer DS_id) {
		DsatrDAO dsatrdao = new DsatrDAO();
		List<Dsatr> dsatrO = new ArrayList<Dsatr>();
		List<DSAtrforCopy> list = new ArrayList<DSAtrforCopy>();
		dsatrO = dsatrdao.getDSAtr(DS_id);

		for (int i = 0; i < dsatrO.size(); i++) {
			DSAtrforCopy dsa = new DSAtrforCopy();
			dsa.setAtr_id((Integer) dsatrO.get(i).getId().getAtrribute()
					.getAtrId());
			dsa.setDS_id(DS_id);
			dsa.setSymbol((Integer) dsatrO.get(i).getSymbol());
			dsa.setValue((Float) dsatrO.get(i).getValue());
			list.add(dsa);
		}
		return list;
	}

	public List<TriggerAtrforCopy> queryTriggerAtr(Integer trigger_id) {
		TriggerAtrDAO etd = new TriggerAtrDAO();
		List<TriggerAtr> listO = new ArrayList<TriggerAtr>();
		List<TriggerAtrforCopy> list = new ArrayList<TriggerAtrforCopy>();
		listO = etd.getTriggerAtr(trigger_id);
		
		for (int i = 0; i < listO.size(); i++) {
			TriggerAtrforCopy triggeratr = new TriggerAtrforCopy();
			triggeratr.setAtr_id((Integer) listO.get(i).getId().getAtrribute()
					.getAtrId());
			triggeratr.setTrigger_id(trigger_id);
			triggeratr.setSymbol((Integer) listO.get(i).getSymbol());
			triggeratr.setValue((Float) listO.get(i).getValue());
			list.add(triggeratr);
		}
		return list;
	}
}
