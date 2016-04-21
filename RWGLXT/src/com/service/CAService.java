package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.EventTriggerDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.Sequence;
import com.domain.Type;
import com.domain.Users;
import com.service.def.Insert;

public class CAService {
	private CaDAO cadao = new CaDAO();

	public List<Map> convert(List<Ca> list) {

		List<Map> result = new ArrayList<Map>();
		Iterator<Ca> iter = list.iterator();

		while (iter.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Ca ca = new Ca();
			ca = iter.next();
			map.put("id", ca.getCaId());
			map.put("name", ca.getName());
			map.put("description", ca.getDescription());
			map.put("triggertype", ca.getTriggertype());
			map.put("priority", ca.getPriority());
			map.put("state", ca.getState());
			map.put("ifAuto", ca.getIfAuto());
			map.put("ifdef", ca.getIfdef());
			map.put("Type", ca.getType().getTypeId());

			if (ca.getUsers() != null) {
				map.put("userId", ca.getUsers().getUserid());
				map.put("userName", ca.getUsers().getUserid());
			} else {
				map.put("userId", null);
				map.put("userName", null);
			}

			if (ca.getEventTriggerByEvent() != null)
				map.put("eventTriggerByEvent", ca.getEventTriggerByEvent()
						.getTriggerId());
			else
				map.put("eventTriggerByEvent", null);

			if (ca.getIsp() != null)
				map.put("ispId", ca.getIsp().getIspId());
			else
				map.put("ispId", null);

			if (ca.getEventTriggerByEndevent() != null)
				map.put("eventTriggerByEndEvent", ca
						.getEventTriggerByEndevent().getTriggerId());
			else
				map.put("eventTriggerByEndEvent", null);

			if (ca.getDefbelong() != null)
				map.put("defBelong", ca.getDefbelong());
			else
				map.put("defBelong", null);

			if (ca.getPlanStarttime() != null)
				map.put("planStart", ca.getPlanStarttime().toString());
			else
				map.put("planStart", "");

			if (ca.getPlanEndtime() != null)
				map.put("planEnd", ca.getPlanEndtime().toString());
			else
				map.put("planEnd", "");

			if (ca.getTerminate() != null)
				map.put("terminate", ca.getTerminate());
			else
				map.put("terminate", null);

			if (ca.getExcuteStarttime() != null)
				map.put("excuteStart", ca.getExcuteStarttime().toString());
			else
				map.put("excuteStart", "");

			if (ca.getExcuteEndtime() != null)
				map.put("excuteEnd", ca.getExcuteEndtime().toString());
			else
				map.put("excuteEnd", "");

			result.add(map);
		}
		return result;
	}

	public Integer insert(Integer ispId, Integer usersId, Integer typeId,
			String name, String description, String triggertype,
			Integer priority, Integer defbelong, Integer ifAuto, Integer ifdef,
			String state, Timestamp planStarttime, Timestamp planEndtime,
			Timestamp excuteStarttime, Timestamp excuteEndtime,
			String terminate, String eventlogic, String eventcode,
			String[] eventatr, String endeventlogic, String endeventcode,
			String[] endeventatr, Integer preDA, Integer preCA) {

		Integer eventtriggerId = null;
		Integer endeventtriggerId = null;
		Integer caId = null;

		if (eventatr != null) { // triggertype.equals("鐘舵�");
								// 鎻掑叆DA涔嬪墠锛屽厛瑕佹彃鍏rigger浠ュ強鐩稿簲鍏宠仈灞炴�,骞惰繑鍥瀟rigger鐨刬d锛宒a鎵嶈兘澶熸彃鍏�
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger eventtrigger = etd.insert(eventlogic, eventcode);
			eventtriggerId = eventtrigger.getTriggerId();
			tas.insertAnalyse(eventatr, eventtriggerId);
		}

		if (endeventatr != null) { // triggertype.equals("鐘舵�");
									// 鎻掑叆DA涔嬪墠锛屽厛瑕佹彃鍏rigger浠ュ強鐩稿簲鍏宠仈灞炴�,骞惰繑鍥瀟rigger鐨刬d锛宒a鎵嶈兘澶熸彃鍏�
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger endeventtrigger = etd.insert(endeventlogic,
					endeventcode);
			endeventtriggerId = endeventtrigger.getTriggerId();
			tas.insertAnalyse(endeventatr, endeventtriggerId);
		}

		caId = cadao.insertTo(eventtriggerId, ispId, usersId,
				endeventtriggerId, typeId, name, description, triggertype,
				priority, defbelong, ifAuto, ifdef, state, planStarttime,
				planEndtime, excuteStarttime, excuteEndtime, terminate)
				.getCaId();

		if (preDA != null) {
			SequenceDAO seqdao = new SequenceDAO();
			seqdao.insert("DA", preDA, "CA", caId);
		}

		if (preCA != null) {
			SequenceDAO seqdao = new SequenceDAO();
			seqdao.insert("CA", preDA, "CA", caId);
		}
		return caId;
	}

	public void update(Integer caId, Integer ispId, Integer usersId,
			Integer typeId, String name, String description,
			String triggertype, Integer priority, Integer defbelong,
			Integer ifAuto, Integer ifdef, String state,
			Timestamp planStarttime, Timestamp planEndtime,
			Timestamp excuteStarttime, Timestamp excuteEndtime,
			String terminate, Integer eventTriggerByEventId, String eventlogic,
			String eventcode, String[] eventatr,
			Integer eventTriggerByEndEventId, String endeventlogic,
			String endeventcode, String[] endeventatr, Integer preDA,
			Integer preCA) {

		if (eventatr != null) {
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			etd.update(eventTriggerByEventId, eventlogic, eventcode);
			tas.updateAnalyse(eventatr, eventTriggerByEventId);
		}

		if (endeventatr != null) {
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			etd.update(eventTriggerByEndEventId, endeventlogic, endeventcode);
			tas.updateAnalyse(endeventatr, eventTriggerByEndEventId);
		}

		cadao.update(caId, eventTriggerByEventId, ispId, usersId,
				eventTriggerByEndEventId, typeId, name, description,
				triggertype, priority, defbelong, ifAuto, ifdef, state,
				planStarttime, planEndtime, excuteStarttime, excuteEndtime,
				terminate);
		// ca 没有参与逻辑
		/*
		 * if (preDA != null) { SequenceDAO seqdao = new SequenceDAO(); Sequence
		 * seq = seqdao.queryByPost("CA", caId); if (seq != null)
		 * seqdao.update(seq.getSequenceId(), "DA", preDA, "CA", caId); } if
		 * (preCA != null) { SequenceDAO seqdao = new SequenceDAO(); Sequence
		 * seq = seqdao.queryByPost("CA", caId); if (seq != null)
		 * seqdao.update(seq.getSequenceId(), "CA", preCA, "CA", caId); }
		 */
	}

	public void delete(Integer caId) {
		Ca ca = new Ca();
		ca = cadao.findById(caId);
		if (ca != null) {
			if (ca.getEventTriggerByEvent() != null) {
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer eventid = ca.getEventTriggerByEvent().getTriggerId();
				etd.delete(eventid);
			}
			if (ca.getEventTriggerByEndevent() != null) {
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer endeventid = ca.getEventTriggerByEndevent()
						.getTriggerId();
				etd.delete(endeventid);
			}
		}

		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> post = seqdao.queryByPost("CA", caId);
		if (post != null) {
			for (Sequence s : post) {
				Integer postseq = s.getSequenceId();
				if (postseq != null) {
					seqdao.delete(postseq);
				}
			}
		}
		List<Sequence> pre = seqdao.queryByPre("CA", caId);
		if (pre != null) {
			for (Sequence s : pre) {
				Integer preseq = s.getSequenceId();
				if (preseq != null) {
					seqdao.delete(preseq);
				}
			}
		}

		cadao.delete(caId);
	}

	public List<Map> getCAbyISP(Integer ispId) {
		List<Ca> u = cadao.getCAbyISP(ispId);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public List<Map> getCAbyType(Integer typeId) {
		List<Ca> u = cadao.getCAbyType(typeId);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public List<Integer[]> DAlocDS(Integer caId) {
		List<Integer[]> result = new ArrayList<Integer[]>();
		DaDAO dadao = new DaDAO();
		DsDAO dsdao = new DsDAO();
		List<Da> dalist = dadao.getDAbyCa(caId);
		List<Ds> dslist = dsdao.getDSbyCa(caId);
		Iterator<Ds> dsit = dslist.iterator();
		Ds ds = new Ds();
		Da da = new Da();
		Integer preDaId = null;
		Integer tureDaId = null;
		Integer falseDaId = null;

		while (dsit.hasNext()) {
			ds = dsit.next();
			preDaId = ds.getDaByPreDa().getDaId();
			tureDaId = ds.getDaByTrueExit().getDaId();
			falseDaId = ds.getDaByFalseExit().getDaId();
			Integer[] loc = new Integer[3];
			for (int i = 0; i < dalist.size(); i++) {
				da = dalist.get(i);
				if (da.getDaId().equals(preDaId))
					loc[0] = i;
				if (da.getDaId().equals(tureDaId))
					loc[1] = i;
				if (da.getDaId().equals(falseDaId))
					loc[2] = i;
			}
			result.add(loc);
		}

		return result;
	}

	public List<Map> getCAbyState(String state) {
		List<Ca> u = cadao.findByState(state);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public List<Map> getCAbyId(Integer caId) {
		List<Ca> u = new ArrayList<Ca>();
		Ca ca = cadao.findById(caId);
		u.add(ca);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public void updateState(Integer caId, String state) {
		cadao.updateState(caId, state);
		return;
	}

	public Integer createCheckPendingCa(Integer caid) {
		Insert insert = new Insert();
		try {
			Integer newcaid = insert.createCA(caid, "", null, null,
					null, "CheckPending");
			return newcaid;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public List<Map> getPendingCA() {
		List<Ca> calist = cadao.getPendingCA();
		List<Map> result = this.convert(calist);
		return result;
	}
}
