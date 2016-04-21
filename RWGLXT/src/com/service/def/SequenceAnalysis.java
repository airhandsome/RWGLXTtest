package com.service.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DaatrDAO;
import com.dao.DsDAO;
import com.dao.DsatrDAO;
import com.dao.EventTriggerDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.dao.TriggerAtrDAO;
import com.dao.def.Create;
import com.dao.def.GetISP;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Daatr;
import com.domain.DaatrId;
import com.domain.Ds;
import com.domain.Dsatr;
import com.domain.EventTrigger;
import com.domain.Sequence;
import com.domain.TriggerAtr;
import com.domain.def.Area;
import com.domain.def.DSforCopy;
import com.domain.def.ISPforCopy;
import com.domain.def.Line;
import com.domain.def.Node;
import com.service.CAService;

public class SequenceAnalysis {
	private List<Ca> calist;

	public void analysis(JSONObject nodes, JSONObject lines, JSONObject areas,
			Integer ispId, Integer usersId, String layout) {
		try {
			List<Node> danodes = new ArrayList<Node>();
			List<Node> dsnodes = new ArrayList<Node>();
			List<Node> nodelist = new ArrayList<Node>();
			List<Line> linelist = new ArrayList<Line>();
			List<Area> arealist = new ArrayList<Area>();
			Iterator nodeit = nodes.keys();
			Iterator lineit = lines.keys();
			Iterator areait = areas.keys();
			CAService caservice = new CAService();
			List<Sequence> seqlist = new ArrayList<Sequence>();
			IspDAO ispdao = new IspDAO();
			DsDAO dsdao = new DsDAO();
			DaDAO dadao = new DaDAO();
			SequenceDAO seqdao = new SequenceDAO();

			ispdao.updateLayout(layout, ispId);

			while (areait.hasNext()) {
				String key = areait.next().toString();
				JSONObject tempareajson = areas.getJSONObject(key);
				Area temparea = new Area();
				temparea.setAreaname(key);
				temparea.setCaname(tempareajson.getString("name"));
				temparea.setColor(tempareajson.getString("color"));
				temparea.setHeight(tempareajson.getInt("height"));
				temparea.setWidth(tempareajson.getInt("width"));
				temparea.setTop(tempareajson.getInt("top"));
				temparea.setLeft(tempareajson.getInt("left"));
				int[] x = new int[2];
				x[0] = tempareajson.getInt("left");
				x[1] = x[0] + tempareajson.getInt("width");
				int[] y = new int[2];
				y[0] = tempareajson.getInt("top");
				y[1] = y[0] + tempareajson.getInt("height");
				temparea.setX(x);
				temparea.setY(y);
				Integer id = caservice.insert(ispId, usersId, null,
						temparea.getCaname(), temparea.getCaname(), "", 0,
						null, 1, 0, "template", null, null, null, null, null,
						null, null, null, null, null, null, null, null);
				temparea.setCaId(id);
				arealist.add(temparea);
			}

			while (nodeit.hasNext()) {
				String key = nodeit.next().toString();
				JSONObject tempnodejson = nodes.getJSONObject(key);
				Node tempnode = new Node();
				tempnode.setNodename(key);
				tempnode.setHeight(tempnodejson.getInt("height"));
				tempnode.setWidth(tempnodejson.getInt("width"));
				String[] name = tempnodejson.getString("name").split("#");
				tempnode.setDaname(name[0]);
				tempnode.setId(new Integer(name[1]));
				tempnode.setLeft(tempnodejson.getInt("left"));
				tempnode.setTop(tempnodejson.getInt("top"));
				// cabelong
				if (tempnodejson.getString("type").equals("task")) {
					tempnode.setType("DA");
					danodes.add(tempnode);
				} else {
					tempnode.setType("DS");
					dsnodes.add(tempnode);
				}
				for (Area area : arealist) {
					if (area.getX()[0] < tempnode.getLeft()
							&& (tempnode.getLeft() + tempnode.getWidth()) < area
									.getX()[1]
							&& area.getY()[0] < tempnode.getTop()
							&& (tempnode.getHeight() + tempnode.getTop()) < area
									.getY()[1]) {
						tempnode.setArea(area);
						break;
					}

				}
				nodelist.add(tempnode);
			}

			while (lineit.hasNext()) {
				String key = lineit.next().toString();
				JSONObject templinejson = lines.getJSONObject(key);
				Line templine = new Line();
				String from = templinejson.getString("from");
				String to = templinejson.getString("to");
				for (Node node : nodelist) {
					if (from.equals(node.getNodename())) {
						templine.setFrom(node);
					}
					if (to.equals(node.getNodename())) {
						templine.setTo(node);
					}
				}
				templine.setLinename(key);
				templine.setName(templinejson.getString("name"));
				templine.setType(templinejson.getString("type"));
				linelist.add(templine);
				System.out.println();
			}

			for (Line line : linelist) {
				Node from = line.getFrom();
				System.out.println(from);
				Node to = line.getTo();
				System.out.println(to);
				if (from.getType().equals("DA") && to.getType().equals("DA")) {
					Sequence sequence = new Sequence();
					sequence.setPreType("DA");
					sequence.setPre(from.getId());
					sequence.setPostType("DA");
					sequence.setPost(to.getId());
					seqlist.add(sequence);
				}
				if (from.getType().equals("DA") && to.getType().equals("DS")) {
					Sequence sequence = new Sequence();
					sequence.setPreType("DA");
					sequence.setPre(from.getId());
					sequence.setPostType("DS");
					sequence.setPost(to.getId());
					seqlist.add(sequence);
					DSforCopy ds = new DSforCopy();
					ds.setPreDA(from.getId());
					ds.setCAbelong(to.getArea().getCaId());
					for (Line tp : linelist) {
						if (tp.getFrom().getType().equals("DS")
								&& tp.getFrom().getId().intValue() == to
										.getId().intValue()) {
							if (tp.getName().equals("true"))
								ds.setTrueExit(tp.getTo().getId());
							if (tp.getName().equals("false"))
								ds.setFalseExit(tp.getTo().getId());
						}
						if (ds.getTrueExit() != null
								&& ds.getFalseExit() != null)
							break;
					}
					dsdao.updatePTF(to.getId(), ds.getPreDA(),
							ds.getTrueExit(), ds.getFalseExit(),
							ds.getCAbelong(), to.getDaname());
				}
			}

			for (Node node : danodes) {
				dadao.updateCAbelong(node.getId(), node.getArea().getCaId(),
						node.getDaname(),node.getNodename());
			}

			for (Sequence sq : seqlist) {
				seqdao.insert(sq.getPreType(), sq.getPre(), sq.getPostType(),
						sq.getPost());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ISPforCopy copyISP(Integer ispId, Integer usersId ,Timestamp time, String name, String desc){
		try{
			GetISP getisp = new GetISP();
			ISPforCopy isp = getisp.queryISP(ispId);
			String oldlayout = isp.getLayout();
			isp.setIfdef(0);
			isp.setLayout(null);
			isp.setState("CheckPending");
			Create create = new Create();
			Integer newispid = create.createISP(isp, name,desc,time).getISP_id();
			isp.setISP_id(newispid);
			if(oldlayout!=null&&!oldlayout.equals("")){
				DaDAO dadao = new DaDAO();
				DsDAO dsdao = new DsDAO();
				DaatrDAO daatrdao = new DaatrDAO();
				DsatrDAO dsadao = new DsatrDAO();
				EventTriggerDAO etdao = new EventTriggerDAO();
				TriggerAtrDAO tadao = new TriggerAtrDAO();
				JSONObject jo = new JSONObject(oldlayout);
				JSONObject tempnodes = jo.getJSONObject("nodes");
				JSONObject lines = jo.getJSONObject("lines");
				JSONObject areas = jo.getJSONObject("areas");
				Iterator nodeit = tempnodes.keys();
				List<String> nodelist = new ArrayList<String>();
				while(nodeit.hasNext()){
					String key = nodeit.next().toString();
					JSONObject node = tempnodes.getJSONObject(key);
					String newnode = "";
					if(node.getString("type").equals("task")){
						Integer daid = new Integer(node.getString("name").split("#")[1]);
						System.out.println(daid);
						Da oldda = dadao.findById(daid);
						Da newda = new Da();
						newda.setCode(oldda.getCode());
						
						newda.setCa(null);
						newda.setDefbelong(daid);
						if(desc!=null)
							newda.setDescription(desc);
						else
							newda.setDescription(oldda.getDescription());
						newda.setFightPostion(oldda.getFightPostion());
						newda.setIfAuto(oldda.getIfAuto());
						newda.setIfdef(0);
						newda.setName(oldda.getName());
						newda.setPlanDuration(oldda.getPlanDuration());
						Timestamp planstart = oldda.getPlanStart();
						Timestamp planend = oldda.getPlanEnd();
						if(time!=null){
							if (planstart!=null){
								planstart.setYear(time.getYear());
								planstart.setMonth(time.getMonth());
								planstart.setDate(time.getDate());
							}
							if (planend!=null){
								planend.setYear(time.getYear());
								planend.setMonth(time.getMonth());
								planend.setDate(time.getDate());
							}

						}
						newda.setPlanEnd(planend);
						newda.setPlanStart(planstart);
						newda.setPriority(oldda.getPriority());
						newda.setState("template");
						newda.setTerminate(oldda.getTerminate());
						newda.setType(oldda.getType());
						newda.setTriggertype(oldda.getTriggertype());
						newda.setUsers(oldda.getUsers());
						if(oldda.getTriggertype().equals("state")){
							EventTrigger e1 = oldda.getEventTriggerByEvent();
							Integer triggerid = e1.getTriggerId();
							EventTrigger newtrigger = etdao.insert(e1.getLogic(), e1.getCode());
							newda.setEventTriggerByEvent(newtrigger);
							List<TriggerAtr> list = tadao.getTriggerAtr(triggerid);
							for(TriggerAtr ta : list){
								tadao.insert(newtrigger.getTriggerId(), ta.getId().getAtrribute().getAtrId(), ta.getValue(), ta.getSymbol());
							}
						}
						dadao.saveDa(newda);
						List<Daatr> daatrlist = daatrdao.getDAAtr(oldda.getDaId());
						for(Daatr daatr:daatrlist){
							daatrdao.insert(newda.getDaId(), daatr.getId().getAtrribute().getAtrId(), daatr.getValue());
							System.out.println("daatr is "+daatr);
						}
						
						String newname = newda.getName()+"#"+newda.getDaId().toString();
						newnode = createNode(newname,node,key);
						nodelist.add(newnode);
					}else if(node.getString("type").equals("fork")){
						Integer dsid = new Integer(node.getString("name").split("#")[1]);
						Ds oldds = dsdao.findById(dsid);
						Ds newds = new Ds();
						newds.setCa(null);
						newds.setCode(oldds.getCode());
						newds.setDaByFalseExit(null);
						newds.setDaByPreDa(null);
						newds.setDaByTrueExit(null);
						newds.setDescription(oldds.getDescription());
						newds.setDsbelong(dsid);
						newds.setDstype(oldds.getDstype());
						newds.setIfdef(0);
						newds.setLogic(oldds.getLogic());
						newds.setName(oldds.getName());
						dsdao.saveDs(newds);
						List<Dsatr> list = new ArrayList<Dsatr>();
						list = dsadao.getDSAtr(dsid);
						for(Dsatr dsatr : list){
							if(dsatr.getId()!=null){
								dsadao.insert(newds.getDsId(), dsatr.getId().getAtrribute().getAtrId(), dsatr.getSymbol(), dsatr.getValue());
							}
						}
						String newname = newds.getName()+"#"+newds.getDsId().toString();
						newnode = createNode(newname,node,key);
						nodelist.add(newnode);
					}
				}
				String newlayout = createNodes(nodelist);
				String layout = "{"+"\"title\":"+"\""+isp.getName()+"#"+isp.getISP_id().toString()+"\""+","+"\"nodes\":"+newlayout+",\"lines\":"+lines.toString()+",\"areas\":"+areas.toString()+",\"initNum\":1}";
				analysis(new JSONObject(newlayout),lines,areas,newispid,usersId,layout);
				System.out.println(newlayout);
				System.out.println(lines);
				System.out.println(areas);
				System.out.println(layout);
			}
			return isp;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
	public String createNodes(List<String> nodelist){
		String result = "{";
		for(int i =0;i<nodelist.size();i++){
			if(i!=nodelist.size()-1){
				result = result+nodelist.get(i)+",";
			}else{
				result = result + nodelist.get(i);
			}
		}
		result = result + "}";
		return result;
	}
	
	public String createNode(String name, JSONObject node, String key){
		try{
			String result = "\""+key+"\""+":{\"name\":"+"\""+name+"\""+",\"left\":"+node.getInt("left")+",\"top\":"+node.getInt("top")+",\"type\":"+"\""+node.getString("type")+"\""+",\"width\":"+node.getInt("width")+",\"height\":"+node.getInt("height")+"}";
			return result;
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		
	}
	public void init(JSONObject nodes, JSONObject lines,
			JSONObject areas,Integer ispId, Integer usersId, String layout) {
		// ���ȣ��϶����е�DA DS�����Ѿ�ʵ���˵ġ�
		// ɾ��isp�����е�sequence����¼��ISP�µ�ca
		String oldlayout = new IspDAO().findById(ispId).getLayout();
		if (oldlayout != null && !oldlayout.equals("")) {
			CaDAO cadao = new CaDAO();
			DaDAO dadao = new DaDAO();
			DsDAO dsdao = new DsDAO();
			SequenceDAO seqdao = new SequenceDAO();
			this.calist = cadao.getCAbyISP(ispId);
			for (Ca ca : calist) {
				List<Da> dalist = dadao.getDAbyCa(ca.getCaId());
				for (Da da : dalist) {
					seqdao.deleteByDa(da.getDaId());
				}
			}
			try {
				Iterator nodeitnew = nodes.keys();

				JSONObject jo = new JSONObject(oldlayout);
				JSONObject oldnode = jo.getJSONObject("nodes");
				Iterator nodeitold = oldnode.keys();
				List<Node> newnodes = new ArrayList<Node>();
				List<Node> oldnodes = new ArrayList<Node>();
				while (nodeitold.hasNext()) {
					Node tempnode = new Node();
					JSONObject temp = oldnode.getJSONObject(nodeitold.next()
							.toString());
					String[] name = temp.getString("name").split("#");
					tempnode.setId(new Integer(name[1]));
					if (temp.getString("type").equals("task")) {
						tempnode.setType("DA");
					} else {
						tempnode.setType("DS");
					}
					oldnodes.add(tempnode);
				}
				while (nodeitnew.hasNext()) {
					Node tempnode = new Node();
					JSONObject temp = nodes.getJSONObject(nodeitnew.next()
							.toString());
					String[] name = temp.getString("name").split("#");
					tempnode.setId(new Integer(name[1]));
					if (temp.getString("type").equals("task")) {
						tempnode.setType("DA");
					} else {
						tempnode.setType("DS");
					}
					newnodes.add(tempnode);
				}
				for (Node node : oldnodes) {
					boolean in = false;
					for (Node n : newnodes) {
						if (n.getId().intValue() == node.getId().intValue()
								&& n.getType().equals(node.getType())) {
							in = true;
							break;
						}
					}
					if ((!in) && node.getType().equals("DA")) {
						dadao.delete(node.getId());
					} else if ((!in && node.getType().equals("DS"))) {
						dsdao.delete(node.getId());
					}
				}
				analysis(nodes, lines, areas, ispId, usersId, layout);
				for(Ca ca : this.calist){
					cadao.delete(ca.getCaId());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			analysis(nodes, lines, areas, ispId, usersId, layout);
		}
	}
}
