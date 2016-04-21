package com.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

import net.sf.json.JSONArray;


import com.dao.IspDAO;
import com.opensymphony.xwork2.ActionSupport;
import com.service.def.SequenceAnalysis;
import com.service.def.SequenceControl;

public class Test extends ActionSupport {
	private JSONArray responseJson;
	public String strings;
	private JSONObject jsonobject;
	
	public JSONObject getJsonobject() {
		return jsonobject;
	}

	public void setJsonobject(JSONObject jsonobject) {
		this.jsonobject = jsonobject;
	}

	public String getStrings() {
		return strings;
	}

	public void setStrings(String strings) {
		this.strings = strings;
	}

	public JSONArray getResponseJson() {
		return responseJson;
	}

	public void setResponseJson(JSONArray responseJson) {
		this.responseJson = responseJson;
	}

	public String execute() {
		try{
//		SequenceControl sc = new SequenceControl();
//		System.out.println("saga");
//		String r = sc.getSequenceImage(new Integer(23), "");
//		System.out.println("sagdagfdsg"+r);
		Date d = new Date(System.currentTimeMillis());
		System.out.println(d);
		int h = d.getHours();
		int m = d.getMinutes();
		int s = d.getSeconds();
		int total = h*3600+m*60+s;
		
		System.out.println(total);
		ServletActionContext.getResponse().setHeader("Access-Control-Allow-Origin", "*");
	//	String bb = "{\"title\":\"abc\"}";
	//	String layout = new IspDAO().findById(new Integer(23)).getLayout();
//		String aa = "{\"title\":\"demo\",\"nodes\":{\"demo_node_1\":{\"name\":\"DA111\",\"left\":67,\"top\":69,\"type\":\"start\",\"width\":86,\"height\":24},\"demo_node_2\":{\"name\":\"DA2\",\"left\":219,\"top\":71,\"type\":\"task\",\"width\":86,\"height\":24},\"demo_node_5\":{\"name\":\"DA3\",\"left\":380,\"top\":71,\"type\":\"fork\",\"width\":86,\"height\":24}},\"lines\":{\"demo_line_3\":{\"type\":\"sl\",\"from\":\"demo_node_1\",\"to\":\"demo_node_2\",\"name\":\"\"},\"demo_line_6\":{\"type\":\"sl\",\"from\":\"demo_node_2\",\"to\":\"demo_node_5\",\"name\":\"\"}},\"areas\":{\"demo_area_8\":{\"name\":\"CA1\",\"left\":35,\"top\":39,\"color\":\"red\",\"width\":472,\"height\":114}}}";

	//	ServletActionContext.getResponse().getWriter().print(bb);
		ServletActionContext.getResponse().getWriter().print(total);
		}catch(Exception e){
			e.printStackTrace();
		}
	/*	try {
			JSONObject jo = new JSONObject(strings);
			String title = jo.getString("title");
			JSONObject nodes = jo.getJSONObject("nodes");
			JSONObject lines = jo.getJSONObject("lines");
			
			JSONObject areas = jo.getJSONObject("areas");
			Iterator it = nodes.keys();
			System.out.println(nodes.toString());
			System.out.println(lines.toString());
			System.out.println(areas.toString());
			while(it.hasNext()){
				System.out.println(nodes.getJSONObject(it.next().toString()).toString());
				
			}
			SequenceAnalysis s = new SequenceAnalysis();
			s.analysis(nodes, lines, areas, 16, 16, strings);
			String aa = "{\"title\":\"demo\",\"nodes\":{\"demo_node_1\":{\"name\":\"DA111\",\"left\":67,\"top\":69,\"type\":\"start\",\"width\":86,\"height\":24},\"demo_node_2\":{\"name\":\"DA2\",\"left\":219,\"top\":71,\"type\":\"task\",\"width\":86,\"height\":24},\"demo_node_5\":{\"name\":\"DA3\",\"left\":380,\"top\":71,\"type\":\"fork\",\"width\":86,\"height\":24}},\"lines\":{\"demo_line_3\":{\"type\":\"sl\",\"from\":\"demo_node_1\",\"to\":\"demo_node_2\",\"name\":\"\"},\"demo_line_6\":{\"type\":\"sl\",\"from\":\"demo_node_2\",\"to\":\"demo_node_5\",\"name\":\"\"}},\"areas\":{\"demo_area_8\":{\"name\":\"CA1\",\"left\":35,\"top\":39,\"color\":\"red\",\"width\":472,\"height\":114}},\"initNum\":9}";
			String bb = "{\"title\":\"abc\"}";
			ServletActionContext.getResponse().getWriter().print(bb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		// List<String> l = new ArrayList<String>();
		// l.add("aaaa");
		// l.add("bbbb");
		// l.add("cccc");
		// JSONArray jsonarray = JSONArray.fromObject(l);
		// this.setResponseJson(jsonarray);
		return null;
	}
}
