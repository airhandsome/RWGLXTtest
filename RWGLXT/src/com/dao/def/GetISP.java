package com.dao.def;

import java.sql.Timestamp;

import com.dao.IspDAO;
import com.domain.Isp;
import com.domain.def.ISPforCopy;

public class GetISP {

	public ISPforCopy queryISP(Integer ISP_id) {

		IspDAO ispdao = new IspDAO();
		Isp ispO = new Isp();
		ispO = ispdao.findById(ISP_id);
		ISPforCopy isp = new ISPforCopy();
		try {
			isp.setISP_id(ISP_id);
			isp.setName((String) ispO.getName());
			isp.setDescription((String) ispO.getDesription());
			isp.setPriority((Integer) ispO.getPriority());
			isp.setTriggertype((String) ispO.getTriggertype());
			isp.setIfdef((Integer) ispO.getIfdef());
			isp.setIfAuto((Integer) ispO.getIfAuto());
			isp.setState((String) ispO.getState());
			isp.setExcuteStarttime((Timestamp) ispO.getExcuteStarttime());
			isp.setExcuteEndtime((Timestamp) ispO.getExcuteEndtime());
			isp.setPlanStarttime((Timestamp) ispO.getPlanStarttime());
			isp.setPlanEndtime((Timestamp) ispO.getPlanEndtime());
			if (ispO.getEventTriggerByEvent() != null) {
				isp.setEvent((Integer) ispO.getEventTriggerByEvent()
						.getTriggerId());
			} else {
				isp.setEvent(null);
			}
			if (ispO.getUsers() != null) {
				isp.setUploader((Integer) ispO.getUsers().getUserid());
			} else {
				isp.setUploader(null);
			}
			if (ispO.getEventTriggerByEndevent() != null) {
				isp.setEndevent((Integer) ispO.getEventTriggerByEndevent()
						.getTriggerId());
			} else {
				isp.setEndevent(null);
			}
			isp.setTerminate((String) ispO.getTerminate());
			isp.setLayout(ispO.getLayout());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return isp;
	}
}
