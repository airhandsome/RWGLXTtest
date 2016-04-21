package com.service.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Sequence;
import com.domain.def.CASequence;
import com.domain.def.DASequence;

public class GetCAforExecute {
	SequenceDAO sqdao = new SequenceDAO();
	DaDAO dadao = new DaDAO();
	DsDAO dsdao = new DsDAO();
	CaDAO cadao = new CaDAO();
	List<String> nodeslist = new ArrayList<String>();
	List<String> areaslist = new ArrayList<String>();
	public final int height = 24;	//默认设置的按钮高度
	public final int caDiff = 0;	//ca之间的距离
	public final int totalW = 600;		//默认时间轴总长度
	public final long minW = 24;		//默认按钮最小宽度
	public final int topheight = 24;    //最顶端的位置
	private long totaltime; // 整个ISP使用的总时间
	public Timestamp startTime = null, endTime = null;		//时间轴起始时间，结束时间。为ISP起始时间为准

//	public static void main(String[] args){
//		ExecuteController exe = new ExecuteController();
//		String result = exe.getCaSequence(305);
//		
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
		if (da.getState().equals("Executing")) {
			
			left = calculateW(startTime, da.getExcuteStart());
			width = calculateW(da.getExcuteStart(), da.getPlanEnd());
		} else if (da.getState().equals("Finish")) {
			
			left = calculateW(startTime, da.getExcuteStart());
			width = calculateW(da.getExcuteStart(), da.getExcuteEnd());
		} else {
			left = calculateW(startTime, da.getPlanStart());
			width = calculateW(da.getPlanStart(), da.getPlanEnd());
		}
		if (width < minW)   width = minW;
		map.put("name", name);
		map.put("left", left);
		map.put("top", top);
		map.put("type", type);
		map.put("width", width);
		map.put("height",height);
		return map;
	}
	public String getCaSequence(Integer daid){
		String result = null;
		Da dat = dadao.findById(daid);
		Ca ca = dat.getCa();
		List<Da> dalist = getfirstDAfromCA(ca);
		Da daarray[] = new Da[100];
		List<Da> dall = dadao.getDAbyCa(ca.getCaId());
		for(Da da: dall){
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
		totaltime = endTime.getTime() - startTime.getTime();
		int minX = totalW;   
		int maxX = 0;		
		int head = 0, tail = 0, maxY = 24;
		for(int i = 0; i < dalist.size(); i++){
			daarray[i] = dalist.get(i);
			tail ++;
		}
		
		Map<String, Object> daMap = new HashMap<String, Object>();
		Map<String, Object> caMap = new HashMap<String, Object>();
		caMap.put("name", ca.getName());
		caMap.put("top", height);
		caMap.put("color", "blue");
		while(head < tail){
			maxY += height;
			List<Da> tmpda = getnextDa(daarray[head]);
			daMap = quickput(daarray[head], maxY, "task");	
			int left = Integer.valueOf(daMap.get("left").toString());
			int right = Integer.valueOf(daMap.get("width").toString()) + left;
			if (minX > left)   minX = left;
			if (maxX < right) 	maxX = right;
			
			for(Da da: tmpda){
				if (da.getCa()==daarray[head].getCa()){
					boolean boo = true;
					for(int i = 0; i < tail-1; i++){
						if (da == daarray[i]) {
							boo = false;
							break;
						}
					}
					if (boo) daarray[tail++] = da;
			}
			}
			
			net.sf.json.JSONObject jsob = net.sf.json.JSONObject.fromObject(daMap);
			
			nodeslist.add("\"" + daarray[head].getNodename() + "\":"
					+ jsob.toString());
			head++;
			
		}
		caMap.put("left", minX-1);
		caMap.put("width",maxX - minX +2);
		caMap.put("height", maxY + 1);
		
		String n = "";
		for (int i = 0; i < nodeslist.size(); i++) {
			if (i != (nodeslist.size() - 1)) {
				n = n + nodeslist.get(i) + ",";
			} else {
				n = n + nodeslist.get(i);
			}
		}
		
		String a = "";
		net.sf.json.JSONObject jsob = net.sf.json.JSONObject.fromObject(caMap);
		a = "\"" + "demo_area_8" + "\":" + jsob.toString();
		System.out.println("a = " +a+", n = "+n);
		
		String r = "{\"title\":\"" + "demo" + "\",\"nodes\":{" + n
				+ "},\"areas\":{" + a + "}}";
		
		result = r;
		return result;
	}
}
