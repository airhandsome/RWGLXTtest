package com.service.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;
import net.sf.json.*;
import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Isp;
import com.domain.Sequence;
import com.domain.def.CASequence;
import com.domain.def.DASequence;
import com.opensymphony.xwork2.ActionContext;

public class SequenceController {

	SequenceDAO sqdao = new SequenceDAO();
	DaDAO dadao = new DaDAO();
	DsDAO dsdao = new DsDAO();
	List<String> nodeslist = new ArrayList<String>();
	List<String> areaslist = new ArrayList<String>();
	public final String[] color = {"red" , "yellow", "green", "blue"};
	public final int height = 24;	//Ĭ�����õİ�ť�߶�
	public final int caDiff = 0;	//ca֮��ľ���
	public final int totalW = 1000;		//Ĭ��ʱ�����ܳ���
	public final int minW = 24;		//Ĭ�ϰ�ť��С���
	public final int topheight = 24;    //��˵�λ��
	private long totaltime; // ���ISPʹ�õ���ʱ��
	public Timestamp startTime = null, endTime = null;		//ʱ������ʼʱ�䣬����ʱ�䡣ΪISP��ʼʱ��Ϊ׼

	
//	public static void main(String[] args) {
//		String layout = "{title:\"demo\",nodes:{	demo_node_1:{name:\"DA1#809\",left:67,top:69,type:\"task\",width:24,height:24},	demo_node_2:{name:\"DS1#89\",left:219,top:71,type:\"fork\",width:86,height:24},	demo_node_5:{name:\"DA2#810\",left:380,top:71,type:\"task\",width:86,height:24}},lines:{	demo_line_3:{type:\"sl\",from:\"demo_node_1\",to:\"demo_node_2\",name:\"\",marked:false},	demo_line_6:{type:\"sl\",from:\"demo_node_2\",to:\"demo_node_5\",name:\"true\",marked:false}},areas:{	demo_area_8: {name:\"CA1\",left:35,top:39,color:\"red\",width:472,height:114}}};";
//		Integer pid = 23;
//		SequenceController sec = new SequenceController();
//		String result = sec.getSequenceImage(pid, layout);
//		System.out.println(result);
//	}
	public List<Da> getfirstDAfromCA(Ca ca) {

		CASequence caseq = new CASequence();
		caseq.setCa(ca);
		List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
		List<Ds> dslist = dsdao.getDSbyCa(ca.getCaId());
		List<Da> firstdalist = new ArrayList<Da>();
		DASequence dasequence = new DASequence();
		for (int i = 0; i < dalist.size(); i++) {
			Da da = dalist.get(i);
			List<Sequence> seq = sqdao.queryByPost("DA", da.getDaId());
			if (seq == null || seq.size() == 0 || !preInThisCa(seq, caseq)) {
				boolean iffirst = true;
				for (Ds ds : dslist) {
					List<Sequence> dsseq = sqdao
							.queryByPost("DS", ds.getDsId());
					if (ds.getDaByTrueExit() != null) {
						if (ds.getDaByTrueExit().getDaId().intValue() == da
								.getDaId().intValue()) {
							for (Sequence s : dsseq) {
								Da dat = dadao.findById(s.getPre());
								if (dat.getCa().getCaId().intValue() == ca
										.getCaId().intValue()) {
									iffirst = false;
								} else {
									boolean re = false;
									for (Ca tp : caseq.getPreca()) {
										if (tp.getCaId().intValue() == dat
												.getCa().getCaId().intValue()) {
											re = true;
											break;
										}
									}
									if (!re) {
										caseq.getPreca().add(dat.getCa());
									}
								}
							}
							if (!iffirst)
								break;
						}
					}
					if (ds.getDaByFalseExit() != null) {
						if (ds.getDaByFalseExit().getDaId().intValue() == da
								.getDaId().intValue()) {
							for (Sequence s : dsseq) {
								Da dat = dadao.findById(s.getPre());
								if (dat.getCa().getCaId().intValue() == ca
										.getCaId().intValue()) {
									iffirst = false;
								} else {
									boolean re = false;
									for (Ca tp : caseq.getPreca()) {
										if (tp.getCaId().intValue() == dat
												.getCa().getCaId().intValue()) {
											re = true;
											break;
										}
									}
									if (!re) {
										caseq.getPreca().add(dat.getCa());
									}
								}
							}
							if (!iffirst)
								break;
						}
					}
				}
				// confirm is the first da
				if (iffirst) {
					dasequence.list.add(da);
					dalist.remove(i);
					i = i - 1;
					firstdalist.add(da);
				}
			}
		}
		return firstdalist;
	}

	public List<Da> getnextDa(Da da) {
		List<Da> dalist = new ArrayList<Da>();
		List<Sequence> list = sqdao.queryByPre("DA", da.getDaId());
		for (Sequence s : list) {
			if (s.getPostType().equals("DA")) {
				dalist.add(dadao.findById(s.getPost()));
			} else if (s.getPostType().equals("DS")) {
				Ds ds = dsdao.findById(s.getPost());
				dalist.add(ds.getDaByTrueExit());
				dalist.add(ds.getDaByFalseExit());
			}
		}

		return dalist;
	}

	public long calculateW(Timestamp da1, Timestamp da2) {
		
		long diff = (da2.getTime() - da1.getTime()) * totalW / totaltime ;
		return diff;
	}

	public boolean preInThisCa(List<Sequence> list, CASequence ca) {
		boolean result = false;
		for (Sequence seq : list) {
			Da preda = dadao.findById(seq.getPre());
			if (preda != null
					&& preda.getCa().getCaId().intValue() == ca.getCa()
							.getCaId().intValue()) {
				result = true;
			} else if (preda != null
					&& preda.getCa().getCaId().intValue() != ca.getCa()
							.getCaId().intValue()) {
				boolean re = false;
				for (Ca tp : ca.getPreca()) {
					if (tp.getCaId().intValue() == preda.getCa().getCaId()
							.intValue()) {
						re = true;
						break;
					}
				}
				if (!re) {
					ca.preca.add(preda.getCa());
				}
			}
		}
		return result;
	}

	public Map<String, Object> quickput(Da da, int top, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		long left, width;
		String name = da.getName();
			left = calculateW(startTime, da.getPlanStart());
			width = calculateW(da.getPlanStart(), da.getPlanEnd());
		if(width < minW)  width = minW;
		map.put("name", name);
		map.put("left", left-1);
		map.put("top", top);
		map.put("type", type);
		map.put("width", width);
		map.put("height",height);
		return map;
	}
public Map<String, Object> quickput2(Da da, int top, String type) {
		Map<String, Object> map = new HashMap<String, Object>();
		long left, width;
		String name = da.getName();
		if (da.getState().equals("Executing")) {
			
			left = calculateW(startTime, da.getExcuteStart());
			width = calculateW(da.getPlanStart(), da.getPlanEnd());
		} else if (da.getState().equals("Finish")) {
			
			left = calculateW(startTime, da.getExcuteStart());
			width = calculateW(da.getExcuteStart(), da.getExcuteEnd());
		} else {
			left = 3000;
			width = calculateW(da.getPlanStart(), da.getPlanStart());
		}
		if(width < minW)  width = minW;
		map.put("name", name);
		map.put("left", left-1);
		map.put("top", top);
		map.put("type", type);
		map.put("width", width);
		map.put("height",height);
		return map;
	}
	
public String getSequenceImage(Integer ispid, String layout,Integer flag) {
		String result = "";
		try {
			
			Isp isp = new IspDAO().findById(ispid);
			layout = new IspDAO().findById(ispid).getLayout();
			//System.out.println(layout);
			JSONObject jo = new JSONObject(layout);
			JSONObject nodes = jo.getJSONObject("nodes");
			JSONObject areas = jo.getJSONObject("areas");

			List<Ca> calist = new CaDAO().getCAbyISP(ispid);
			
			
			for(Ca ca : calist){
				List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
				for(Da da : dalist){
					if (startTime == null) startTime = da.getPlanEnd();
					if (endTime == null) endTime = da.getPlanStart();
					if (da.getState().equals("Finish")){
						startTime = startTime.getTime() < da.getExcuteStart().getTime()? startTime:da.getExcuteStart();
						endTime = endTime.getTime() > da.getExcuteEnd().getTime()? endTime:da.getExcuteEnd();
					}else if (da.getState().equals("Executing")){
						startTime = startTime.getTime() < da.getExcuteStart().getTime()? startTime:da.getExcuteStart();
						endTime = endTime.getTime() > da.getPlanEnd().getTime()? endTime:da.getPlanEnd();
					}else{
						startTime = startTime.getTime() < da.getPlanStart().getTime()? startTime:da.getPlanStart();
						endTime = endTime.getTime() > da.getPlanEnd().getTime()? endTime:da.getPlanEnd();
					}
				}
			}
			totaltime = endTime.getTime() - startTime.getTime();
			IspDAO ispdao = new IspDAO();
			ispdao.updateTime(ispid, startTime, endTime);
		//	ActionContext.getContext().getSession().put("starttime", startTime);
			System.out.println("startTime = "+startTime+" endTime = "+endTime+" totalTime = "+totaltime);
			
			int maxY = topheight;
			Map<String, Object> node = new HashMap<String, Object>();
			List<Map> areaList = new ArrayList<Map>();
			
			int count = 0;
			for (Ca ca : calist) {				//����isp�������е�CA
				maxY += height + caDiff;
				int preY = maxY;
				int minX = totalW;   
				int maxX = 0;
				Map<String, Object> caMap = new HashMap<String, Object>();
				caMap.put("name", ca.getName());
				caMap.put("top", preY - height);
				caMap.put("color", "blue");
				//caMap.put("color", color[count%4]);

				// BFS
				Da[] daarray = new Da[100];
				int head = 0;
				int tail = 0;
				List<Da> dalist = getfirstDAfromCA(ca);
				for (int i = head; i < dalist.size(); i++) {
					daarray[i] = dalist.get(i);
					tail++;
				}
				while (head < tail) {
					if(flag == 1)//!@#
						node = quickput(daarray[head], maxY, "task");
					else if(flag == 2)
						node = quickput2(daarray[head], maxY, "task");
					
					int left = Integer.valueOf(node.get("left").toString());
					int width = Integer.valueOf(node.get("width").toString());
					if (width < minW) width = minW;  
					int right = width + left;
					if (minX > left)   minX = left;
					if (maxX < right) 	maxX = right;

					net.sf.json.JSONObject jsob = net.sf.json.JSONObject.fromObject(node);
					
					nodeslist.add("\"" + daarray[head].getNodename() + "\":"
							+ jsob.toString());
					maxY += height;
					dalist = getnextDa(daarray[head]);
					for (int i = 0; i < dalist.size(); i++) {
						Da tmpda = dalist.get(i);
						boolean boo = true;					//��֤�Ƿ����ӹ�
						for (int j = 0; j < tail - 1; j++) {
							if (daarray[j] == tmpda) {
								boo = false;
								break;
							}
						}
						if (boo && tmpda.getCa().getCaId().equals(daarray[head].getCa().getCaId()))  //δ���Ӳ���ͬһ��CA�ھͽ��
							daarray[tail++] = tmpda;
					}
					head++;
				}
				caMap.put("left", minX);
				caMap.put("width",maxX - minX+2);
				caMap.put("height", maxY - preY + height+1);
				areaList.add(caMap);
				count ++;
				
			}
			// construct the json string
			String title = new IspDAO().findById(ispid).getName();
			title = title + "#" + ispid.toString();
			String n = "";
			for (int i = 0; i < nodeslist.size(); i++) {
				if (i != (nodeslist.size() - 1)) {
					n = n + nodeslist.get(i) + ",";
				} else {
					n = n + nodeslist.get(i);
				}
			}
			//System.out.println(nodeslist.size());

			String a = "";
			Iterator it = areas.keys();
			String name = "";
			while (it.hasNext()) {
				
				String key = it.next().toString();
				JSONObject area = areas.getJSONObject(key);
				//System.out.println(area.getString("name"));
				for(Map<String, Object> map: areaList){
					if (map.get("name").equals(area.getString("name"))){	
						name = key;
						
						net.sf.json.JSONObject jsob = net.sf.json.JSONObject.fromObject(map);
						String newarea = "\"" + name + "\":" + jsob.toString();
						System.out.println(newarea);
						areaslist.add(newarea);
						break;
					}
					
				}	
			}

			for(int i=0;i<areaslist.size();i++){
				if(i!=(areaslist.size()-1)){
					a = a + areaslist.get(i)+",";
				}else{
					a = a + areaslist.get(i);
				}
			}
			String r = "{\"title\":\"" + title + "\",\"nodes\":{" + n
					+ "},\"areas\":{" + a + "}}";
		//	System.out.println("result" + r);
			result = r;

		} catch (NullPointerException e) {
			e.printStackTrace();
			System.out.println("空数据");
		} catch (Exception e2){
			e2.printStackTrace();
			System.out.println("数据出错");
		}

		return result;
	}

}