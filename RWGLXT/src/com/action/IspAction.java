package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.json.JSONObject;

import net.sf.json.JSONArray;

import com.domain.Da;
import com.domain.Isp;
import com.domain.def.ISPforCopy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.ISPService;
import com.service.def.SequenceAnalysis;
import com.service.def.SequenceControl;
import com.service.def.SequenceController;

public class IspAction extends ActionSupport implements SessionAware {

	private Integer ispId;
	private Integer usersId;
	private String name;
	private String description;
	private String triggertype;
	private Integer priority;
	private Integer ifAuto;
	private Integer ifdef;
	private String state;
	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private Timestamp excuteStarttime;
	private Timestamp excuteEndtime;
	private Timestamp time;
	private String terminate;
	private Integer eventTriggerByEventId;
	private String eventlogic;
	private String eventcode;
	private String[] eventatr;
	private Integer eventTriggerByEndEventId;
	private String endeventlogic;
	private String endeventcode;
	private String[] endeventatr;
	private String layout;
	private ISPService ispservice = new ISPService();
	private JSONArray responseJson;
	private Integer[] caIds;
	private Map<String, Object> session;
	private String strname;
	

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getStrname() {
		return strname;
	}

	public void setStrname(String strname) {
		this.strname = strname;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Integer[] getCaIds() {
		return caIds;
	}

	public String getLayout() {
		return layout;
	}

	public void setLayout(String layout) {
		this.layout = layout;
	}

	public void setCaIds(Integer[] caIds) {
		this.caIds = caIds;
	}

	public Integer getIspId() {
		return ispId;
	}

	public void setIspId(Integer ispId) {
		this.ispId = ispId;
	}

	public Integer getUsersId() {
		return usersId;
	}

	public void setUsersId(Integer usersId) {
		this.usersId = usersId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTriggertype() {
		return triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public Integer getIfAuto() {
		return ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public Integer getIfdef() {
		return ifdef;
	}

	public void setIfdef(Integer ifdef) {
		this.ifdef = ifdef;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Timestamp getPlanStarttime() {
		return planStarttime;
	}

	public void setPlanStarttime(Timestamp planStarttime) {
		this.planStarttime = planStarttime;
	}

	public Timestamp getPlanEndtime() {
		return planEndtime;
	}

	public void setPlanEndtime(Timestamp planEndtime) {
		this.planEndtime = planEndtime;
	}

	public Timestamp getExcuteStarttime() {
		return excuteStarttime;
	}

	public void setExcuteStarttime(Timestamp excuteStarttime) {
		this.excuteStarttime = excuteStarttime;
	}

	public Timestamp getExcuteEndtime() {
		return excuteEndtime;
	}

	public void setExcuteEndtime(Timestamp excuteEndtime) {
		this.excuteEndtime = excuteEndtime;
	}

	public String getTerminate() {
		return terminate;
	}

	public void setTerminate(String terminate) {
		this.terminate = terminate;
	}

	public Integer getEventTriggerByEventId() {
		return eventTriggerByEventId;
	}

	public void setEventTriggerByEventId(Integer eventTriggerByEventId) {
		this.eventTriggerByEventId = eventTriggerByEventId;
	}

	public String getEventlogic() {
		return eventlogic;
	}

	public void setEventlogic(String eventlogic) {
		this.eventlogic = eventlogic;
	}

	public String getEventcode() {
		return eventcode;
	}

	public void setEventcode(String eventcode) {
		this.eventcode = eventcode;
	}

	public String[] getEventatr() {
		return eventatr;
	}

	public void setEventatr(String[] eventatr) {
		this.eventatr = eventatr;
	}

	public Integer getEventTriggerByEndEventId() {
		return eventTriggerByEndEventId;
	}

	public void setEventTriggerByEndEventId(Integer eventTriggerByEndEventId) {
		this.eventTriggerByEndEventId = eventTriggerByEndEventId;
	}

	public String getEndeventlogic() {
		return endeventlogic;
	}

	public void setEndeventlogic(String endeventlogic) {
		this.endeventlogic = endeventlogic;
	}

	public String getEndeventcode() {
		return endeventcode;
	}

	public void setEndeventcode(String endeventcode) {
		this.endeventcode = endeventcode;
	}

	public String[] getEndeventatr() {
		return endeventatr;
	}

	public void setEndeventatr(String[] endeventatr) {
		this.endeventatr = endeventatr;
	}

	public ISPService getIspservice() {
		return ispservice;
	}

	public void setIspservice(ISPService ispservice) {
		this.ispservice = ispservice;
	}

	public JSONArray getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}

	public String insertISP() throws IOException {
		String role = null;
		if(ActionContext.getContext().getSession().containsKey("role")){
			role = (String)ActionContext.getContext().getSession().get("role");
		}
		if(role!=null){
			if(role.equals("��������Ա")){
				state = "template";
			}else{
				state = "CheckPending";
			}
		}
		
		Integer id = ispservice.insert(usersId, name, description, triggertype,
				priority, ifAuto, ifdef, state, planStarttime, planEndtime,
				excuteStarttime, excuteEndtime, terminate, eventlogic,
				eventcode, eventatr, endeventlogic, endeventcode, endeventatr,
				layout);
		ActionContext.getContext().getSession().put("ispId", id);
		ActionContext.getContext().getSession().put("ispName", name);
		ServletActionContext.getResponse().getWriter().print(id);
		return null;
	}

	public String updateISP() {
		ispservice.update(ispId, usersId, name, description, triggertype,
				priority, ifAuto, ifdef, state, planStarttime, planEndtime,
				excuteStarttime, excuteEndtime, terminate,
				eventTriggerByEventId, eventlogic, eventcode, eventatr,
				eventTriggerByEndEventId, endeventlogic, endeventcode,
				endeventatr, layout);
		ActionContext.getContext().getSession().put("ispId", ispId);
		ActionContext.getContext().getSession().put("ispName", name);
		return "Success";
	}

	public String deleteISP() {
		ispservice.delete(ispId);
		return "Success";
	}

	public String getExeISP() {
		List<Map> result = new ArrayList<Map>();
		result = ispservice.getISPbyState("Executing");
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getToBeApprovedISP() {
		List<Map> result = new ArrayList<Map>();
		result = ispservice.getISPbyState("CheckPending");
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getISPbyId() {
		List<Map> result = new ArrayList<Map>();
		System.out.println("ispId:"+ispId);
		result = ispservice.getISPbyId(ispId);
		System.out.println("getISPbyId:"+ispId);
		System.out.println("result size:"+result.size());
		System.out.println("result is "+result);
		if(result.size()>0) {
			if(ActionContext.getContext().getSession().containsKey("ispId"))
				ActionContext.getContext().getSession().remove("ispId");
			if(ActionContext.getContext().getSession().containsKey("ispName"))
				ActionContext.getContext().getSession().remove("ispName");
//			if(ActionContext.getContext().getSession().containsKey("time"))
//				ActionContext.getContext().getSession().remove("time");
			
			ActionContext.getContext().getSession()
					.put("ispId", ispId);
			ActionContext.getContext().getSession()
					.put("ispName", (String)result.get(0).get("name"));
//			ActionContext.getContext().getSession()
//					.put("time", result.get(0).get("startTime"));
			
		}
		System.out.println(ActionContext.getContext().getSession().get("ispId:")+" ispName:"+result.get(0).get("name"));
		Da da = new Da();
		da.setDaId(0);
		if(ActionContext.getContext().getSession().containsKey("currentDa"))
			ActionContext.getContext().getSession().remove("currentDa");
		ActionContext.getContext().getSession().put("currentDa", da);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		System.out.println("jsonarray1"+jsonarray);
		return "Success";
	}
	//bumenzhang diaoyong muban, zhijie fuzhi yige isp
	public String getDefISPbyId(){
		Da da = new Da();
		da.setDaId(0);
		if(ActionContext.getContext().getSession().containsKey("currentDa"))
			ActionContext.getContext().getSession().remove("currentDa");
		ActionContext.getContext().getSession().put("currentDa", da);
		SequenceAnalysis sa = new SequenceAnalysis();
	//	ISPforCopy ispf = sa.copyISP(ispId, (Integer)ActionContext.getContext().getSession().get("userid"),time,null,null);
//		if(ActionContext.getContext().getSession().containsKey("ispId"))
//			ActionContext.getContext().getSession().remove("ispId");
//		if(ActionContext.getContext().getSession().containsKey("ispName"))
//			ActionContext.getContext().getSession().remove("ispName");
//		if(ActionContext.getContext().getSession().containsKey("ispTime"))
//			ActionContext.getContext().getSession().remove("ispTime");
//		ActionContext.getContext().getSession()
//		.put("ispId", ispf.getISP_id());
//		ActionContext.getContext().getSession()
//		.put("ispName", ispf.getName());
//		ActionContext.getContext().getSession()
//		.put("ispTime", time);
//		List<Map> result = new ArrayList<Map>();
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("name", ispf.getName());
//		map.put("description", ispf.getDescription());
//		result.add(map);
//		JSONArray jsonarray = JSONArray.fromObject(result);
//		this.setResponseJson(jsonarray);
		List<Map> result = new ArrayList<Map>();
		result = ispservice.getISPbyId(ispId);
		if(result.size()>0) {
			if(ActionContext.getContext().getSession().containsKey("ispId"))
				ActionContext.getContext().getSession().remove("ispId");
			if(ActionContext.getContext().getSession().containsKey("ispName"))
				ActionContext.getContext().getSession().remove("ispName");
			
			ActionContext.getContext().getSession()
					.put("ispId", ispId);
			ActionContext.getContext().getSession()
					.put("ispName", (String)result.get(0).get("name"));
		}
		System.out.println(ActionContext.getContext().getSession().get("ispId:")+" ispName:"+result.get(0).get("name"));
		da.setDaId(0);
		if(ActionContext.getContext().getSession().containsKey("currentDa"))
			ActionContext.getContext().getSession().remove("currentDa");
		ActionContext.getContext().getSession().put("currentDa", da);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		System.out.println("jsonarray2"+jsonarray);
		return "Success";
	}
	
	public String getISPdef() {
		List<Map> result = ispservice.getISPdef();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getISPbyState() {
		List<Map> result = new ArrayList<Map>();
		result = ispservice.getISPbyState(state);
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String updateISPState() {
		ispservice.updateState(ispId, state);
		return "Success";
	}

	public String submitISP() {
		Isp isp = new Isp();
		// isp.setName(name);
		isp.setName(name);
		// isp.setDesription(description);
		isp.setDesription(description);
		ispservice.createISP(isp, caIds);

		return "Success";
	}

	public String saveISPLayout() {
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		SequenceAnalysis sa = new SequenceAnalysis();
		try {	
				JSONObject jo = new JSONObject(layout);
				String title = jo.getString("title");
				System.out.println(layout);
				// test
			//	 title = "test#31";
				JSONObject nodes = jo.getJSONObject("nodes");
				JSONObject lines = jo.getJSONObject("lines");
				JSONObject areas = jo.getJSONObject("areas");
				System.out.println(nodes.toString());
				Integer userid = null;
				if(ActionContext.getContext().getSession().containsKey("userid"));
					userid = (Integer) ActionContext.getContext()
						.getSession().get("userid");
				// test
		//		userid = new Integer(5);

				if (title.split("#").length == 2) {
					Integer ispid = new Integer(title.split("#")[1]);
					sa.init(nodes, lines, areas, ispid, userid, layout);
				}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}

	public String reloadISPPic() {
		String title = "new ISP";
		Isp isp = null;
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		// test
//		 ActionContext.getContext().getSession().put("ispName", "test");
//		 ActionContext.getContext().getSession().put("ispId",new Integer(23));

		if (ActionContext.getContext().getSession().containsKey("ispName")
				&& ActionContext.getContext().getSession().containsKey("ispId")) {
			title = ActionContext.getContext().getSession().get("ispName")
					.toString()
					+ "#"
					+ ActionContext.getContext().getSession().get("ispId")
							.toString();
			isp = ispservice.queryISPbyId((Integer) ActionContext.getContext()
					.getSession().get("ispId"));
		}
		System.out.println(ActionContext.getContext().getSession().getClass());
		try {
			title = "{\"title\":\"" + title + "\"}";
			if (isp != null && isp.getLayout() != null)
				title = isp.getLayout();
			
			List<Map> list = new ArrayList<Map>();
			Map map = new HashMap<String,Object>();
			map.put("layout", title);
			list.add(map);
			System.out.println("reloadtitle:"+title);
			JSONArray jsonarray = JSONArray.fromObject(list);
			this.setResponseJson(jsonarray);
		//	ServletActionContext.getResponse().getWriter().print(title);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Success";
	//	return null;
	}
	public String loadSecondMonitor() {
		Integer ispid = null;
		if (ActionContext.getContext().getSession().containsKey("ispId")) {
			ispid = (Integer) ActionContext.getContext().getSession()
					.get("ispId");
		}
		System.out.println("ispid:" + ispid);
		SequenceControl sc = new SequenceControl();
		SequenceController scr = new SequenceController();
		String title = " ";
		title = "{\"title\":\"" + title + "\"}";
		String r = title;
		if(ispid!=null){
			r = scr.getSequenceImage(ispid, "",2);
		}
		try {
			if(r!=null){
				List<Map> list = new ArrayList<Map>();
				Map map = new HashMap<String,Object>();
				map.put("layout", r);
				list.add(map);
				JSONArray jsonarray = JSONArray.fromObject(list);
				this.setResponseJson(jsonarray);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Success";
	}
	public String loadMonitor() {
		Integer ispid = null;
		// test
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
//
//		ActionContext.getContext().getSession().put("ispName", "test");
//		ActionContext.getContext().getSession().put("ispId", (Integer) 23);

		if (ActionContext.getContext().getSession().containsKey("ispId")) {
			ispid = (Integer) ActionContext.getContext().getSession()
					.get("ispId");
		}
		System.out.println("ispid:" + ispid);
		SequenceControl sc = new SequenceControl();
		SequenceController scr = new SequenceController();
		String title = " ";
		title = "{\"title\":\"" + title + "\"}";
		String r = title;
if(ispid!=null){
			r = scr.getSequenceImage(ispid, "",1);
		}

		try {
			if(r!=null){
				List<Map> list = new ArrayList<Map>();
				Map map = new HashMap<String,Object>();
				map.put("layout", r);
				list.add(map);
				JSONArray jsonarray = JSONArray.fromObject(list);
				this.setResponseJson(jsonarray);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "Success";
	}

	public String apprISP() {
		ispservice.apprISP(ispId);
		return "Success";
	}

	public String rejectISP() {
		ispservice.rejectISP(ispId);
		return "Success";
	}

	public String getPendingISP() {
		List<Map> result = ispservice.getPendingISP();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getRejectISP() {
		List<Map> result = ispservice.getRejectISP();
		JSONArray jsonarray = JSONArray.fromObject(result);
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getExcutingISP() {
		JSONArray jsonarray = JSONArray
				.fromObject(ispservice.getExecutingISP());
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getRunningState() {
		Integer ispid = null;
//		ActionContext.getContext().getSession().put("ispId", 23);
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		if (ActionContext.getContext().getSession().containsKey("ispId"))
			ispid = (Integer) ActionContext.getContext().getSession()
					.get("ispId");
		JSONArray jsonarray = null;
		if(ispid!=null){
			jsonarray = JSONArray.fromObject(ispservice.getRunningState(ispid));
		}
				
		
		this.setResponseJson(jsonarray);
		return "Success";
	}

	public String getNodesId() {

		// if(ActionContext.getContext().getSession().containsKey("ispId"))
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		ActionContext a = ActionContext.getContext();
		// test
//		a.getSession().put("ispId", 23);
		Integer ispid = null;
		JSONArray j = null;
//		a.getSession().put("role", "任务执行员");
//		a.getSession().put("exeDa", new Integer(906));
		if(a.getSession().containsKey("role")&&a.getSession().get("role").equals("任务执行员")&&a.getSession().containsKey("exeDa")){
			Integer daid = (Integer) a.getSession().get("exeDa");
			j = JSONArray.fromObject(ispservice.getDaIdbyCa(daid));
		}else if(a.getSession().containsKey("ispId")){
			ispid = (Integer) a.getSession().get("ispId");
			if(ispid!=null){
				j = JSONArray.fromObject(ispservice.getNodesId(ispid));
			}
		}
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getCaRunningState(){
		Integer daid = null;
		JSONArray j = null;
		//test
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
//		ActionContext.getContext().getSession().put("exeDa", 906);
		if(ActionContext.getContext().getSession().containsKey("exeDa")){
			daid = (Integer)ActionContext.getContext().getSession().get("exeDa");
		}
		if(daid!=null){
			j = JSONArray.fromObject(ispservice.getCaRunningState(daid));
		}
		this.setResponseJson(j);
		return "Success";
	}
	
	public String getProvNodes() {
		System.out.println("getNodesIdProv");
		// if(ActionContext.getContext().getSession().containsKey("ispId"))
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		ActionContext a = ActionContext.getContext();
		// test
	//	a.getSession().put("ispId", 23);
		Integer ispid = null;
		JSONArray j = null;
		if(a.getSession().containsKey("ispId")){
			ispid = (Integer) a.getSession().get("ispId");
			if(ispid!=null){
				j = JSONArray.fromObject(ispservice.getNodesIdPro(ispid));
			}
		}
		this.setResponseJson(j);
		return "Success";
	}
	public String getISPStartTime() {
//		ServletActionContext.getResponse().setHeader(
//				"Access-Control-Allow-Origin", "*");
		Integer ispid = null;
		System.out.println("getISPStartTime");
		if (ActionContext.getContext().getSession().containsKey("ispId"))
			ispid = (Integer) ActionContext.getContext().getSession()
					.get("ispId");
		// test
	//	ispId = 23;
		JSONArray j = JSONArray.fromObject(ispservice.getISPStartTime(ispid));
		this.setResponseJson(j);
		return "Success";
	}
}
