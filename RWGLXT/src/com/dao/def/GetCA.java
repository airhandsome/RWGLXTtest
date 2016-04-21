package com.dao.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.CaDAO;
import com.domain.Ca;
import com.domain.def.CAforCopy;

public class GetCA {

	// query ca by ISP belong
	public List<CAforCopy> queryCAByBelong(Integer ISP_id) {
		CaDAO cadao = new CaDAO();
		List<Ca> calist = new ArrayList<Ca>();
		calist = cadao.getCAbyISP(ISP_id);
		List<CAforCopy> list = new ArrayList<CAforCopy>();

		for (int i = 0; i < calist.size(); i++) {
			CAforCopy ca = new CAforCopy();
			ca.setCA_id((Integer) calist.get(i).getCaId());
			ca.setName((String) calist.get(i).getName());
			ca.setType((Integer) calist.get(i).getType().getTypeId());
			ca.setDescription((String) calist.get(i).getDescription());
			ca.setTriggertype((String) calist.get(i).getTriggertype());
			ca.setPriority((Integer) calist.get(i).getPriority());
			ca.setDefbelong((Integer) calist.get(i).getDefbelong());
			ca.setISPbelong((Integer) calist.get(i).getIsp().getIspId());
			ca.setIfdef((Integer) calist.get(i).getIfdef());
			ca.setIfAuto((Integer) calist.get(i).getIfAuto());
			ca.setState((String) calist.get(i).getState());
			ca.setExcuteStarttime((Timestamp) calist.get(i)
					.getExcuteStarttime());
			ca.setExcuteEndtime((Timestamp) calist.get(i).getExcuteEndtime());
			ca.setPlanStarttime((Timestamp) calist.get(i).getPlanStarttime());
			ca.setPlanEndtime((Timestamp) calist.get(i).getPlanEndtime());
			ca.setTerminate((String) calist.get(i).getTerminate());
			if (calist.get(i).getEventTriggerByEvent() != null) {
				ca.setEvent((Integer) calist.get(i).getEventTriggerByEvent()
						.getTriggerId());
			} else {
				ca.setEvent(null);
			}
			if (calist.get(i).getUsers() != null) {
				ca.setUploader((Integer) calist.get(i).getUsers().getUserid());
			} else {
				ca.setUploader(null);
			}
			if (calist.get(i).getEventTriggerByEndevent() != null) {
				ca.setEndevent((Integer) calist.get(i)
						.getEventTriggerByEndevent().getTriggerId());
			} else {
				ca.setEndevent(null);
			}
			list.add(ca);
		}

		return list;
	}

	public CAforCopy queryCAbyid(Integer ca_id) {
		CaDAO cadao = new CaDAO();
		Ca caO = new Ca();
		caO = cadao.findById(ca_id);
		CAforCopy ca = new CAforCopy();

		ca.setCA_id((Integer) caO.getCaId());
		ca.setName((String) caO.getName());
		ca.setType((Integer) caO.getType().getTypeId());
		ca.setDescription((String) caO.getDescription());
		ca.setTriggertype((String) caO.getTriggertype());
		ca.setPriority((Integer) caO.getPriority());
		ca.setDefbelong((Integer) caO.getDefbelong());
		if(caO.getIsp()!=null){
		    ca.setISPbelong((Integer) caO.getIsp().getIspId());
		}else{
			ca.setISPbelong(null);
		}
		ca.setIfdef((Integer) caO.getIfdef());
		ca.setIfAuto((Integer) caO.getIfAuto());
		ca.setState((String) caO.getState());
		ca.setExcuteStarttime((Timestamp) caO.getExcuteStarttime());
		ca.setExcuteEndtime((Timestamp) caO.getExcuteEndtime());
		ca.setPlanStarttime((Timestamp) caO.getPlanStarttime());
		ca.setPlanEndtime((Timestamp) caO.getPlanEndtime());
		ca.setTerminate((String) caO.getTerminate());
		if(caO.getEventTriggerByEvent()!=null){
		    ca.setEvent((Integer) caO.getEventTriggerByEvent().getTriggerId());
		}else{
			ca.setEvent(null);
		}
		if(caO.getUsers()!=null){
			ca.setUploader((Integer) caO.getUsers().getUserid());
		}
		if(caO.getEventTriggerByEndevent()!=null){
		    ca.setEndevent((Integer) caO.getEventTriggerByEndevent().getTriggerId());
		}else{
			ca.setEndevent(null);
		}
		return ca;
	}
}
