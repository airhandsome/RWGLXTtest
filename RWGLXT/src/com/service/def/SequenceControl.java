package com.service.def;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.json.JSONObject;

import com.dao.CaDAO;
import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.IspDAO;
import com.dao.SequenceDAO;
import com.domain.Ca;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Sequence;
import com.domain.def.CASequence;
import com.domain.def.CaPositionLine;
import com.domain.def.DASequence;

public class SequenceControl {
	SequenceDAO sqdao = new SequenceDAO();
	DaDAO dadao = new DaDAO();
	DsDAO dsdao = new DsDAO();
	List<String> nodeslist = new ArrayList<String>();
	List<String> areaslist = new ArrayList<String>();

	public String getSequenceImage(Integer ispid, String layout) {
		String result = "";
		try {
			//test
			layout = new IspDAO().findById(ispid).getLayout();
			JSONObject jo = new JSONObject(layout);
			JSONObject nodes = jo.getJSONObject("nodes");
			JSONObject areas = jo.getJSONObject("areas");
			List<CASequence> caseqlist = new ArrayList<CASequence>();

			List<Ca> calist = new CaDAO().getCAbyISP(ispid);

			for (Ca ca : calist) {
				CASequence caseq = new CASequence();
				caseq.setCa(ca);
				List<DASequence> seqlist = new ArrayList<DASequence>();
				List<Da> dalist = dadao.getDAbyCa(ca.getCaId());

				List<Ds> dslist = dsdao.getDSbyCa(ca.getCaId());
				DASequence dasequence = new DASequence();
				// find the first da in ca
				for (int i = 0; i < dalist.size(); i++) {
					Da da = dalist.get(i);
					List<Sequence> seq = sqdao.queryByPost("DA", da.getDaId());
					if (seq == null || seq.size() == 0
							|| !preInThisCa(seq, caseq)) {
						boolean iffirst = true;
						for (Ds ds : dslist) {
							List<Sequence> dsseq = sqdao.queryByPost("DS",
									ds.getDsId());
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
														.getCa().getCaId()
														.intValue()) {
													re = true;
													break;
												}
											}
											if (!re) {
												caseq.getPreca().add(
														dat.getCa());
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
														.getCa().getCaId()
														.intValue()) {
													re = true;
													break;
												}
											}
											if (!re) {
												caseq.getPreca().add(
														dat.getCa());
											}
										}
									}
									if (!iffirst)
										break;
								}
							}
						}
						if (iffirst) {
							dasequence.list.add(da);
							dalist.remove(i);
							i = i - 1;
						}
					}
				}
				seqlist.add(dasequence);

				while (dalist.size() > 0) {
					DASequence templist = new DASequence();
					for (Da da : dasequence.getList()) {
						List<Sequence> list = sqdao.queryByPre("DA",
								da.getDaId());
						for (Sequence s : list) {
							if (s.getPostType().equals("DA")) {
								for (int i = 0; i < dalist.size(); i++) {
									if (dalist.get(i).getDaId().intValue() == s
											.getPost().intValue()) {
										templist.list.add(dadao.findById(s
												.getPost()));
										dalist.remove(i);
										break;
									}
								}
							} else if (s.getPostType().equals("DS")) {
								Ds ds = dsdao.findById(s.getPost());
								for (int i = 0; i < dalist.size(); i++) {
									if (ds.getDaByTrueExit().getDaId()
											.intValue() == dalist.get(i)
											.getDaId().intValue()
											|| ds.getDaByFalseExit().getDaId()
													.intValue() == dalist
													.get(i).getDaId()
													.intValue()) {
										templist.list.add(dadao.findById(s
												.getPost()));
										dalist.remove(i);
										break;
									}
								}
							}
						}
					}
					seqlist.add(templist);
				}
				caseq.setDalist(seqlist);
				int i = 0;
				for (DASequence d : seqlist) {
					i++;
					System.out.println("ca:" + ca.getCaId() + "xiade di" + i
							+ "ceng");
					for (Da da : d.getList()) {
						System.out.println(da.getDaId());
					}
				}
				caseqlist.add(caseq);
			}
			// for(int i=0;i<caseqlist.size();i++){
			// System.out.println("i"+i);
			// System.out.println("test caseqlist size"+caseqlist.size());
			// System.out.println("caid"+caseqlist.get(i).getCa().getCaId());
			// System.out.println("preca size"+caseqlist.get(i).getPreca().size());
			// for(int j=0;j<caseqlist.get(i).getPreca().size();j++){
			// System.out.println("ca pre:"+caseqlist.get(i).getPreca().get(j).getCaId());
			// }
			// }
			List<CaPositionLine> cpt = new ArrayList<CaPositionLine>();
			CaPositionLine firstline = new CaPositionLine();
			for (int i = 0; i < caseqlist.size(); i++) {
				if (caseqlist.get(i).getPreca() == null
						|| caseqlist.get(i).getPreca().size() == 0) {
					firstline.casequnce.add(caseqlist.get(i));
					caseqlist.remove(i);
					i = i - 1;
				}
			}
			cpt.add(firstline);
			int count = 0;
			while (caseqlist.size() > 0 && cpt.size() > count) {
				CaPositionLine tp = cpt.get(count);
				CaPositionLine next = new CaPositionLine();
				for (int i = 0; i < caseqlist.size(); i++) {
					boolean haspre = false;
					for (int j = 0; j < caseqlist.get(i).getPreca().size(); j++) {
						for (int t = 0; t < tp.getCasequnce().size(); t++) {
							if (caseqlist.get(i).getPreca().get(j).getCaId()
									.intValue() == tp.getCasequnce().get(t)
									.getCa().getCaId().intValue()) {
								next.casequnce.add(caseqlist.get(i));
								caseqlist.remove(i);
								i = i - 1;
								haspre = true;
								break;
							}
						}
						if (haspre)
							break;
					}
				}
				cpt.add(next);
				count++;
			}
			// System.out.println("ca cengci shu:"+cpt.size());
			// for(int i=0;i<cpt.size();i++){
			// System.out.println("di "+(i+1)+"ceng");
			// for(int j=0;j<cpt.get(i).getCasequnce().size();j++){
			// System.out.println("ca:"+cpt.get(i).getCasequnce().get(j).getCa().getName());
			// }
			// }
			int areax = 24;
			int areay = 24;
			for(int i=0;i<cpt.size();i++){
				CaPositionLine caline = cpt.get(i);
				int maxwidth = 0;
				for(int j=0;j<caline.getCasequnce().size();j++){
					int[] r = caculateArea(caline.getCasequnce().get(j),nodes,areas,areax,areay);
					areay = areay + r[1] + 24;
					if(r[0]>maxwidth){
						maxwidth = r[0];
					}
				}
				areay = 24;
				areax = areax + maxwidth + 24;
			}
			String title = new IspDAO().findById(ispid).getName();
			title = title+"#"+ispid.toString();
			String n = "";
			for(int i=0;i<nodeslist.size();i++){
				if(i!=(nodeslist.size()-1)){
					n = n+nodeslist.get(i)+",";
				}else{
					n = n + nodeslist.get(i);
				}
			}
			System.out.println(nodeslist.size());
//			for(int i=0;i<14;i++){
//				if(i!=13){
//					n = n+nodeslist.get(i)+",";
//				}else{
//					n = n + nodeslist.get(i);
//				}
//			}
			String a = "";
			for(int i=0;i<areaslist.size();i++){
				if(i!=(areaslist.size()-1)){
					a = a + areaslist.get(i)+",";
				}else{
					a = a + areaslist.get(i);
				}
			}
			System.out.println(areaslist.size());
//			for(int i=0;i<4;i++){
//				if(i!=3){
//					a = a + areaslist.get(i)+",";
//				}else{
//					a = a + areaslist.get(i);
//				}
//			}
			String r = "{\"title\":\""+title+"\",\"nodes\":{"+n+"},\"areas\":{"+a+"}}";
		//	System.out.println("result:"+r);
			result = r;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public int[] caculateArea(CASequence caseq, JSONObject nodes,
			JSONObject areas, int areax, int areay) {
		int[] result = new int[2];
		try {
			int x = 24;
			int y = 24;

			int maxheight = 0;
			for (int i = 0; i < caseq.getDalist().size(); i++) {
				int maxwidth = 0;
				int tempheight = 24;
				for (int j = 0; j < caseq.getDalist().get(i).getList().size(); j++) {
					Da da = caseq.getDalist().get(i).getList().get(j);
					JSONObject node = nodes.getJSONObject(da.getNodename());
					int width = node.getInt("width");
					int height = node.getInt("height");
					if (width > maxwidth) {
						maxwidth = width;
					}
					node.put("top", y + areay);
					tempheight = tempheight + 24 + height;
					y = tempheight;
					
					node.put("left", x + areax);
					nodeslist.add("\"" + da.getNodename() + "\":"
							+ node.toString());
					System.out.println("new nodes:" + "\"" + da.getNodename()
							+ "\":" + node.toString());
				}
				if (tempheight > maxheight)
					maxheight = tempheight;
				x = x + maxwidth + 24;
				y = 24;
			}
			Iterator it = areas.keys();
			JSONObject ca = null;
			String name = "";
			while (it.hasNext()) {
				String key = it.next().toString();
				JSONObject area = areas.getJSONObject(key);
				if (caseq.getCa().getName().equals(area.getString("name"))) {
					ca = area;
					name = key;
					break;
				}
			}
			if (ca != null) {
				ca.put("height", maxheight);
				
				result[1] = maxheight;
				if (x < 200) {
					ca.put("width", 200);
					result[0]= 200;
				} else {
					ca.put("width", x);
					result[0] = x;
				}
				ca.put("top", areay);
				ca.put("left", areax);
				String newarea = "\"" + name + "\":" + ca.toString();
				System.out.println(newarea);
				areaslist.add(newarea);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
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

}
