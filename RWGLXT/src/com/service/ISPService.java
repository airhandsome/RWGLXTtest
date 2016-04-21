package com.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.hql.ast.HqlASTFactory;
import org.json.JSONObject;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.EventTrigger;
import com.domain.Isp;
import com.domain.Sequence;
import com.domain.Type;
import com.domain.Users;
import com.service.execute.TriggerISPService;

public class ISPService {
	private IspDAO ispdao = new IspDAO();

	/*
	 * private Integer ispId; private EventTrigger eventTriggerByEvent; private
	 * Users users; private EventTrigger eventTriggerByEndevent; private String
	 * name; private String desription; private Integer priority; private
	 * Integer ifdef; private Integer ifAuto; private String state; private
	 * Timestamp excuteStarttime; private Timestamp excuteEndtime; private
	 * Timestamp planStarttime; private Timestamp planEndtime; private String
	 * triggertype; private String terminate;
	 */

	public List<Map> convert(List<Isp> list) {

		List<Map> result = new ArrayList<Map>();
		Iterator<Isp> iter = list.iterator();

		while (iter.hasNext()) {
			Map<String, Object> map = new HashMap<String, Object>();
			Isp isp = new Isp();
			isp = iter.next();
			map.put("id", isp.getIspId());
			map.put("name", isp.getName());
			map.put("description", isp.getDesription());
			map.put("ifdef", isp.getIfdef());
			map.put("ifAuto", isp.getIfAuto());
			map.put("state", isp.getState());
			map.put("priority", isp.getPriority());
			map.put("triggertype", isp.getTriggertype());
			map.put("terminate", isp.getTerminate());
			map.put("layout", isp.getLayout());
			if(isp.getPlanStarttime()!=null){
				map.put("startTime", isp.getPlanStarttime());
				map.put("endTime", isp.getPlanEndtime());
			}else{
				map.put("startTime", null);
				map.put("endTime", null);
			}
			if (isp.getUsers() != null) {
				map.put("userId", isp.getUsers().getUserid());
				map.put("userName", isp.getUsers().getUserid());
			} else {
				map.put("userId", null);
				map.put("userName", null);
			}

			if (isp.getEventTriggerByEvent() != null)
				map.put("eventTriggerByEvent", isp.getEventTriggerByEvent()
						.getTriggerId());
			else
				map.put("eventTriggerByEvent", null);

			if (isp.getEventTriggerByEndevent() != null)
				map.put("eventTriggerByEndEvent", isp
						.getEventTriggerByEndevent().getTriggerId());
			else
				map.put("eventTriggerByEndEvent", null);

			result.add(map);
		}
		return result;
	}

	public Integer insert(Integer usersId, String name, String description,
			String triggertype, Integer priority, Integer ifAuto,
			Integer ifdef, String state, Timestamp planStarttime,
			Timestamp planEndtime, Timestamp excuteStarttime,
			Timestamp excuteEndtime, String terminate, String eventlogic,
			String eventcode, String[] eventatr, String endeventlogic,
			String endeventcode, String[] endeventatr, String layout) {

		Integer eventtriggerId = null;
		Integer endeventtriggerId = null;
		Integer ispId = null;

		if (eventatr != null) {
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger eventtrigger = etd.insert(eventlogic, eventcode);
			eventtriggerId = eventtrigger.getTriggerId();
			tas.insertAnalyse(eventatr, eventtriggerId);
		}

		if (endeventatr != null) {
			EventTriggerDAO etd = new EventTriggerDAO();
			TriggerAtrService tas = new TriggerAtrService();
			EventTrigger endeventtrigger = etd.insert(endeventlogic,
					endeventcode);
			endeventtriggerId = endeventtrigger.getTriggerId();
			tas.insertAnalyse(endeventatr, endeventtriggerId);
		}

		ispId = ispdao.insertTo(eventtriggerId, usersId, endeventtriggerId,
				name, description, triggertype, priority, ifAuto, ifdef, state,
				planStarttime, planEndtime, excuteStarttime, excuteEndtime,
				terminate, layout).getIspId();
		return ispId;
	}

	public void update(Integer ispId, Integer usersId, String name,
			String description, String triggertype, Integer priority,
			Integer ifAuto, Integer ifdef, String state,
			Timestamp planStarttime, Timestamp planEndtime,
			Timestamp excuteStarttime, Timestamp excuteEndtime,
			String terminate, Integer eventTriggerByEventId, String eventlogic,
			String eventcode, String[] eventatr,
			Integer eventTriggerByEndEventId, String endeventlogic,
			String endeventcode, String[] endeventatr, String layout) {

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

		ispdao.update(ispId, eventTriggerByEventId, usersId,
				eventTriggerByEndEventId, name, description, triggertype,
				priority, ifAuto, ifdef, state, planStarttime, planEndtime,
				excuteStarttime, excuteEndtime, terminate, layout);
	}

	public void delete(Integer ispId) {
		Isp isp = new Isp();
		isp = ispdao.findById(ispId);
		if (isp != null) {
			if (isp.getEventTriggerByEvent() != null) {
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer eventid = isp.getEventTriggerByEvent().getTriggerId();
				etd.delete(eventid);
			}
			if (isp.getEventTriggerByEndevent() != null) {
				EventTriggerDAO etd = new EventTriggerDAO();
				Integer endeventid = isp.getEventTriggerByEndevent()
						.getTriggerId();
				etd.delete(endeventid);
			}
		}
		ispdao.delete(ispId);
	}

	public List<Map> getISPbyState(String state) {
		List<Isp> u = ispdao.getISPbyState(state);
		for(int i=0;i<u.size();i++){
			if(u.get(i).getIfdef()==1){
				u.remove(i);
				i--;
			}
		}
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public List<Map> getISPdef() {
		List<Isp> u = ispdao.getISPdef();
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public List<Map> getISPbyId(Integer ispId) {
		List<Isp> u = new ArrayList<Isp>();
		System.out.println(ispId);
		Isp isp = ispdao.findById(ispId);
		u.add(isp);
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}

	public Isp queryISPbyId(Integer ispId) {
		return ispdao.findById(ispId);
	}

	public void updateState(Integer ispId, String state) {
		ispdao.updateState(ispId, state);
		return;
	}

	public void createISP(Isp isp, Integer[] caids) {

		isp.setIfAuto(1);
		isp.setIfdef(0);
		isp.setPriority(1);
		isp.setState("CheckPending");
		isp.setTriggertype("event");
		Isp newisp = isp;
		ispdao.save(newisp);
		CaDAO cadao = new CaDAO();
		cadao.updateCaBelong(isp.getIspId(), caids);
		// StringBuffer stringbuffer = new StringBuffer();
		// for(int i=0;i<caids.length;i++){
		// String hql =
		// "update Ca as ca set ca.isp.ispId="+isp.getIspId()+" where ca.caId="+caids[i]+";";
		// stringbuffer.append(hql);
		// }
	}

	public void apprISP(Integer ispid) {
		CaDAO cadao = new CaDAO();
		ispdao.checkISP(ispid, "Executing");
		TriggerISPService triggerIspService = new TriggerISPService();
		triggerIspService.executeFollowCA(ispid, "Executing");
		List<Ca> list = cadao.getCAbyISP(ispid);
		for (Ca ca : list) {
			triggerIspService.executeFollowDA(ca.getCaId(), "TriggerPending");
		}
		// triggerIspService.executeFollowDA(ispid, "ActivatePending");
		// ca ActivatePending
		// da Ac
	}

	public void rejectISP(Integer ispid) {
		ispdao.checkISP(ispid, "Reject");
	}

	public List<Map> getPendingISP() {
		List<Isp> isplist = ispdao.getCheckPendingIsp();
		List<Map> result = this.convert(isplist);
		return result;
	}

	public List<Map> getRejectISP() {
		List<Isp> isplist = ispdao.getRejectIsp();
		List<Map> result = this.convert(isplist);
		return result;
	}

	public List<Map> getExecutingISP() {
		List<Isp> isplist = ispdao.getISPbyState("Executing");
		return convert(isplist);
	}

	public List<Map> getRunningState(Integer ispId) {
		DaDAO dadao = new DaDAO();
		List<Map> result = new ArrayList<Map>();
		List<Ca> calist = new CaDAO().getCAbyISP(ispId);
		for (Ca ca : calist) {
			List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
			for (Da da : dalist) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (da.getState().equals("Executing")) {
					map.put("key", da.getNodename());
					map.put("color", "#87D37c");
					map.put("style", "ico_start");
					map.put("opacity", "1");
				//	result.add(map);
				} else if (da.getState().equals("Finish")) {
					map.put("key", da.getNodename());
					map.put("color", "#CCCC99");
					map.put("style", "ico_end");
					map.put("opacity", "1");
				//	result.add(map);
				} else if (da.getState().equals("Undo")) {
					map.put("key", da.getNodename());
					map.put("color", "#D2D7D3");
					map.put("style", "ico_delete");
					map.put("opacity", "0.4");
				//	result.add(map);
				} else if (da.getState().equals("TriggerPending")||da.getState().equals("AffirmPending")) {
					map.put("key", da.getNodename());
					map.put("color", "#C1DCFC");
					map.put("style", "ico_state");
					map.put("opacity", "1");
				//	result.add(map);
				}
				String info = "";
				String state = "等待执行";
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(da.getState().equals("Executing")){
					state="正在执行";
					if(da.getPlanEnd().before(now)){
						state+="（超时）";
					}
				}
				else if(da.getState().equals("Finish")){
					state="执行完成";
					if(da.getExcuteEnd()!=null&&da.getExcuteEnd().after(da.getPlanEnd())){
						state+="（超时）";
					}else if(da.getExcuteEnd()!=null&&da.getExcuteEnd().before(da.getPlanEnd())){
						state+="（提前）";
					}
						
				}
				else if(da.getState().equals("Undo")){
					state = "放弃执行";
				}
				
				String description = "暂无描述";
				String executestart = "尚未开始执行";
				String executeend = "尚未结束";
				String code = "没有执行命令";
				if(da.getDescription()!=null&&!da.getDescription().equals(""))
					description = da.getDescription();
				if(da.getExcuteStart()!=null)
					executestart = da.getExcuteStart().toString();
				if(da.getExcuteEnd()!=null)
					executeend = da.getExcuteEnd().toString();
				if(da.getCode()!=null&&!da.getCode().equals("no data"))
					code = da.getCode();
				info = "DA名称：" + da.getName() + "<br>" + "DA描述："
						+ description + "<br>" + "所属域："
						+ da.getType().getName() + "<br>" + "执行站位："
						+ da.getFightPostion().getName() + "<br>计划开始时间："
						+ da.getPlanStart() + "<br>" + "计划结束时间："
						+ da.getPlanEnd() + "<br>" + "实际开始时间："
						+ executestart + "<br>" + "实际结束时间："
						+ executeend + "<br>" + "执行命令：" + code+"<br>执行状态："+state;
				map.put("daInfo", info);
				result.add(map);
			}
		}
		return result;
	}

	public List<Map> getCaRunningState(Integer daid){
		DaDAO dadao = new DaDAO();
		Da tmp= dadao.findById(daid);
		List<Da> dalist = dadao.getDAbyCa(tmp.getCa().getCaId());
		List<Map> result = new ArrayList<Map>();
			for (Da da : dalist) {
				Map<String, Object> map = new HashMap<String, Object>();
				if (da.getState().equals("Executing")) {
					map.put("key", da.getNodename());
					map.put("color", "#87D37c");
					map.put("style", "ico_start");
					map.put("opacity", "1");
				//	result.add(map);
				} else if (da.getState().equals("Finish")) {
					map.put("key", da.getNodename());
					map.put("color", "#CCCC99");
					map.put("style", "ico_end");
					map.put("opacity", "1");
				//	result.add(map);
				} else if (da.getState().equals("Undo")) {
					map.put("key", da.getNodename());
					map.put("color", "#D2D7D3");
					map.put("style", "ico_delete");
					map.put("opacity", "0.4");
				//	result.add(map);
				} else if (da.getState().equals("TriggerPending")||da.getState().equals("AffirmPending")) {
					map.put("key", da.getNodename());
					map.put("color", "#C1DCFC");
					map.put("style", "ico_state");
					map.put("opacity", "1");
				//	result.add(map);
				}
				String info = "";
				String state = "等待执行";
				Timestamp now = new Timestamp(System.currentTimeMillis());
				if(da.getState().equals("Executing")){
					state="正在执行";
					if(da.getPlanEnd().before(now)){
						state+="（超时）";
					}
				}
				else if(da.getState().equals("Finish")){
					state="执行完成";
					if(da.getExcuteEnd()!=null&&da.getExcuteEnd().after(da.getPlanEnd())){
						state+="（超时）";
					}else if(da.getExcuteEnd()!=null&&da.getExcuteEnd().before(da.getPlanEnd())){
						state+="（提前）";
					}
						
				}
				else if(da.getState().equals("Undo")){
					state = "放弃执行";
				}
				
				String description = "暂无描述";
				String executestart = "尚未开始执行";
				String executeend = "尚未结束";
				String code = "没有执行命令";
				if(da.getDescription()!=null&&!da.getDescription().equals(""))
					description = da.getDescription();
				if(da.getExcuteStart()!=null)
					executestart = da.getExcuteStart().toString();
				if(da.getExcuteEnd()!=null)
					executeend = da.getExcuteEnd().toString();
				if(da.getCode()!=null&&!da.getCode().equals("no data"))
					code = da.getCode();
				info = "DA名称：" + da.getName() + "<br>" + "DA描述："
						+ description + "<br>" + "所属域："
						+ da.getType().getName() + "<br>" + "执行站位："
						+ da.getFightPostion().getName() + "<br>计划开始时间："
						+ da.getPlanStart() + "<br>" + "计划结束时间："
						+ da.getPlanEnd() + "<br>" + "实际开始时间："
						+ executestart + "<br>" + "实际结束时间："
						+ executeend + "<br>" + "执行命令：" + code+"<br>执行状态："+state;
				map.put("daInfo", info);
				result.add(map);
			}
		return result;
	}
	public List<Map> getNodesId(Integer ispId) {
		DaDAO dadao = new DaDAO();
		List<Map> result = new ArrayList<Map>();
		List<Ca> calist = new CaDAO().getCAbyISP(ispId);
		for (Ca ca : calist) {
			List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
			for (Da da : dalist) {
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", da.getNodename());
				map.put("daId", da.getDaId());
				String info = "";
				String description = "暂无描述";
				String executestart = "尚未开始执行";
				String executeend = "尚未结束";
				String code = "没有执行命令";
				if(da.getDescription()!=null&&!da.getDescription().equals(""))
					description = da.getDescription();
				if(da.getExcuteStart()!=null)
					executestart = da.getExcuteStart().toString();
				if(da.getExcuteEnd()!=null)
					executeend = da.getExcuteEnd().toString();
				if(da.getCode()!=null&&!da.getCode().equals("no data"))
					code = da.getCode();
				info = "DA名称：" + da.getName() + "<br>" + "DA描述："
						+ description + "<br>" + "所属域："
						+ da.getType().getName() + "<br>" + "执行站位："
						+ da.getFightPostion().getName() + "<br>计划开始时间："
						+ da.getPlanStart() + "<br>" + "计划结束时间："
						+ da.getPlanEnd() + "<br>" + "实际开始时间："
						+ executestart + "<br>" + "实际结束时间："
						+ executeend + "<br>" + "执行命令：" + code;
				map.put("daInfo", info);
				result.add(map);
			}
		}
		return result;
	}
	
	public List<Map> getNodesIdPro(Integer ispId) {
		try{
		DaDAO dadao = new DaDAO();
		DsDAO dsdao = new DsDAO();
		List<Map> result = new ArrayList<Map>();
		List<Ca> calist = new CaDAO().getCAbyISP(ispId);
		IspDAO ispdao = new IspDAO();
		String layout = ispdao.findById(ispId).getLayout();
		JSONObject j = new JSONObject(layout);
		JSONObject nodes = j.getJSONObject("nodes");
		Iterator nodeit = nodes.keys();
		while(nodeit.hasNext()){
			String ke = nodeit.next().toString();
			JSONObject tp = nodes.getJSONObject(ke);
			String type = tp.getString("type");
			if(type.equals("fork")){
				String n = tp.getString("name");
				String dsname = n.split("#")[0];
				String dsid = n.split("#")[1];
				Ds ds = dsdao.findById(Integer.parseInt(dsid));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", ke);
				map.put("id", dsid);
				String info = "";
				String logic = ds.getLogic();
				if(logic.contains("#")){
					String[] t = logic.split("#");
					logic = t[t.length-1];
				}else {
					logic = "暂无执行逻辑";
				}
				info = "DS名称："+ds.getName()+"<br>"+"DS描述："+ds.getDescription()+"<br>"
						+"DS域："+ds.getDstype().getName()+"<br>"+"执行逻辑："+logic;
				map.put("info", info);
				result.add(map);
			}else if(type.equals("task")){
				Da da = dadao.findById(Integer.parseInt(tp.getString("name").split("#")[1]));
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("key", da.getNodename());
				map.put("id", da.getDaId());
				String info = "";
				String description = "暂无描述";
				String code = "没有执行命令";
				if(da.getDescription()!=null&&!da.getDescription().equals(""))
					description = da.getDescription();
				if(da.getCode()!=null&&!da.getCode().equals("no data"))
					code = da.getCode();
				info = "DA名称：" + da.getName() + "<br>" + "DA描述："
						+ description + "<br>" + "所属域："
						+ da.getType().getName() + "<br>" + "执行站位："
						+ da.getFightPostion().getName() + "<br>计划开始时间："
						+ da.getPlanStart() + "<br>" + "计划结束时间："
						+ da.getPlanEnd() + "<br>" + "执行命令：" + code;
				map.put("info", info);
				result.add(map);
			}
		}
		return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Map> getDaIdbyCa(Integer daId){
		DaDAO dadao = new DaDAO();
		Da dat = dadao.findById(daId);
		Ca ca = dat.getCa();
		List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
		List<Map> result = new ArrayList<Map>();
		for (Da da : dalist) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("key", da.getNodename());
			map.put("daId", da.getDaId());
			String info = "";
			String description = "暂无描述";
			String executestart = "尚未开始执行";
			String executeend = "尚未结束";
			String code = "没有执行命令";
			if(da.getDescription()!=null&&!da.getDescription().equals(""))
				description = da.getDescription();
			if(da.getExcuteStart()!=null)
				executestart = da.getExcuteStart().toString();
			if(da.getExcuteEnd()!=null)
				executeend = da.getExcuteEnd().toString();
			if(da.getCode()!=null&&!da.getCode().equals("no data"))
				code = da.getCode();
			info = "DA名称：" + da.getName() + "<br>" + "DA描述："
					+ description + "<br>" + "所属域："
					+ da.getType().getName() + "<br>" + "执行站位："
					+ da.getFightPostion().getName() + "<br>计划开始时间："
					+ da.getPlanStart() + "<br>" + "计划结束时间："
					+ da.getPlanEnd() + "<br>" + "实际开始时间："
					+ executestart + "<br>" + "实际结束时间："
					+ executeend + "<br>" + "执行命令：" + code;
			map.put("daInfo", info);
			result.add(map);
		}
		return result;
	}
	
	public List<Map> getISPStartTime(Integer ispId){
		List<Map> list = new ArrayList<Map>();
		Map<String,Object> map = new HashMap<String, Object>();
		if(ispId!=null){
			Isp isp = ispdao.findById(ispId);
			Date date = (Date)isp.getPlanStarttime();
			int total = date.getHours()*3600+date.getMinutes()*60+date.getSeconds();
			map.put("totaltime", total);
			Double rate = (new Double(1000*3600*24)/new Double(isp.getPlanEndtime().getTime()-date.getTime()));
			System.out.println(rate);
			map.put("proportion", rate);
			list.add(map);
		}else{
			
			map.put("totaltime", 0);
			map.put("proportion", 1);
			list.add(map);
		}
		return list;
	}
}
