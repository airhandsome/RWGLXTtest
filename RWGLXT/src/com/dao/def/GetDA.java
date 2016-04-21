package com.dao.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.DaDAO;
import com.domain.Da;
import com.domain.def.DAforCopy;

public class GetDA {

	// //query da by ISP belong
	public List<DAforCopy> queryDAByBelong(Integer CA_id) {
		DaDAO dadao = new DaDAO();
		List<Da> dalist = new ArrayList<Da>();
		dalist = dadao.getDAbyCa(CA_id);
		List<DAforCopy> list = new ArrayList<DAforCopy>();
		for (int i = 0; i < dalist.size(); i++) {
			DAforCopy da = new DAforCopy();
			da.setDA_id((Integer) dalist.get(i).getDaId());
			da.setName((String) dalist.get(i).getName());
			da.setType((Integer) dalist.get(i).getType().getTypeId());
			da.setDescription((String) dalist.get(i).getDescription());
			da.setTriggertype((String) dalist.get(i).getTriggertype());
			if (dalist.get(i).getUsers() != null) {
				da.setOpeartor((Integer) dalist.get(i).getUsers().getUserid());
			} else {
				da.setOpeartor(null);
			}
			da.setPriority((Integer) dalist.get(i).getPriority());
			if (dalist.get(i).getFightPostion() != null) {
				da.setPostion((Integer) dalist.get(i).getFightPostion()
						.getPostionId());
			} else {
				da.setPostion(null);
			}
			da.setDefbelong((Integer) dalist.get(i).getDefbelong());
			if (dalist.get(i).getCa() != null) {
				da.setCAbelong((Integer) dalist.get(i).getCa().getCaId());
			} else {
				da.setCAbelong(null);
			}
			da.setIfdef((Integer) dalist.get(i).getIfdef());
			da.setIfAuto((Integer) dalist.get(i).getIfAuto());
			da.setState((String) dalist.get(i).getState());
			if (dalist.get(i).getDs() != null) {
				da.setPostDS((Integer) dalist.get(i).getDs().getDsId());
			} else {
				da.setPostDS(null);
			}
			da.setExcuteStart((Timestamp) dalist.get(i).getExcuteStart());
			da.setExcuteEnd((Timestamp) dalist.get(i).getExcuteEnd());
			da.setPlanStart((Timestamp) dalist.get(i).getPlanStart());
			da.setPlanDuration((Timestamp) dalist.get(i).getPlanDuration());
			da.setPlanEnd((Timestamp) dalist.get(i).getPlanEnd());
			da.setTerminate((String) dalist.get(i).getTerminate());
			if (dalist.get(i).getEventTriggerByEvent() != null) {
				da.setEvent((Integer) dalist.get(i).getEventTriggerByEvent()
						.getTriggerId());
			} else {
				da.setEvent(null);
			}
			if (dalist.get(i).getEventTriggerByEndEvent() != null) {
				da.setEndEvent((Integer) dalist.get(i)
						.getEventTriggerByEndEvent().getTriggerId());
			} else {
				da.setEndEvent(null);
			}
			da.setCode((String) dalist.get(i).getCode());
			list.add(da);
		}
		return list;
	}

	public DAforCopy queryDAByid(Integer DA_id) {
		DaDAO dadao = new DaDAO();
		Da daO = new Da();
		System.out.println("daid:"+DA_id);
		daO = dadao.findById(DA_id);
		DAforCopy da = new DAforCopy();

		da.setDA_id((Integer) daO.getDaId());
		da.setName((String) daO.getName());
		da.setType((Integer) daO.getType().getTypeId());
		da.setDescription((String) daO.getDescription());
		da.setTriggertype((String) daO.getTriggertype());
		if (daO.getUsers() != null) {
			da.setOpeartor((Integer) daO.getUsers().getUserid());
		} else {
			da.setOpeartor(null);
		}
		da.setPriority((Integer) daO.getPriority());
		da.setPostion((Integer) daO.getFightPostion().getPostionId());
		da.setDefbelong((Integer) daO.getDefbelong());
		if(daO.getCa()!=null){
			da.setCAbelong((Integer) daO.getCa().getCaId());
		}else{
			da.setCAbelong(null);
		}
		da.setIfdef((Integer) daO.getIfdef());
		da.setIfAuto((Integer) daO.getIfAuto());
		da.setState((String) daO.getState());
		if (daO.getDs() != null) {
			da.setPostDS((Integer) daO.getDs().getDsId());
		} else {
			da.setPostDS(null);
		}
		da.setExcuteStart((Timestamp) daO.getExcuteStart());
		da.setExcuteEnd((Timestamp) daO.getExcuteEnd());
		da.setPlanStart((Timestamp) daO.getPlanStart());
		da.setPlanDuration((Timestamp) daO.getPlanDuration());
		da.setPlanEnd((Timestamp) daO.getPlanEnd());
		da.setTerminate((String) daO.getTerminate());
		if (daO.getEventTriggerByEvent() != null) {
			da.setEvent((Integer) daO.getEventTriggerByEvent().getTriggerId());
		} else {
			da.setEvent(null);
		}
		if (daO.getEventTriggerByEndEvent() != null) {
			da.setEndEvent((Integer) daO.getEventTriggerByEndEvent()
					.getTriggerId());
		} else {
			da.setEndEvent(null);
		}
		da.setCode((String) daO.getCode());

		return da;
	}
}
