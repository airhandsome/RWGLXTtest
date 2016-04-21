package com.service.execute;

//import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.DsatrDAO;
import com.dao.SequenceDAO;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Dsatr;
import com.domain.Sequence;

public class ExecuteFollowDaService {
	DaDAO dadao = new DaDAO();
	SequenceDAO sedao = new SequenceDAO();
	DsatrDAO dsatrdao = new DsatrDAO();
	Stack<Da> stack = new Stack<Da>();

	public void execute(List<Da> dalist) {
		List<Sequence> list = new ArrayList<Sequence>();
		DsDAO dsdao = new DsDAO();
		for (int i = 0; i < dalist.size(); i++) {
			list = sedao.queryByPre("DA", dalist.get(i).getDaId());
			for (Sequence sequence : list) {
				if (sequence != null && sequence.getPostType().equals("DA")) {
					Da da = dadao.findById(sequence.getPost());
					if (da.getIfAuto() == 0
							&& da.getTriggertype().equals("event")
							&& checkDApre(da)) {
						dadao.changeState(da, "AffirmPending");
					} else if (da.getIfAuto() == 1
							&& da.getTriggertype().equals("event")
							&& checkDApre(da)) {
						da.setExcuteStart(new Timestamp(System
								.currentTimeMillis()));
						dadao.changeState(da, "Executing");
						// List<Da> tplist = new ArrayList<Da>();
						// tplist.add(da);
						// execute(tplist);
					}
				} else if (sequence != null
						&& sequence.getPostType().equals("DS")) {
					System.out.println("sequence pre:" + sequence.getPre());
					Ds ds = dsdao.findById(sequence.getPost());
					executeDs(ds);
				}
			}
		}
	}

	private boolean checkDApre(Da da) {
		boolean result = true;
		List<Sequence> list = new ArrayList<Sequence>();
		list = (List<Sequence>) sedao.queryByPost("DA", da.getDaId());
		if (list != null && list.size() > 1) {
			for (Sequence s : list) {
				Da tempda = dadao.findById(s.getPre());
				if (!tempda.getState().equals("Finish")) {
					result = false;
					break;
				}
			}
		} else if (list != null && list.size() == 1) {
			result = true;
		}
		return result;
	}

	private boolean getDSRes(String logic) {
		boolean result = false;

		return result;
	}

	private void executeDs(Ds ds) {
		List<Dsatr> dsatr = dsatrdao.getDSAtr(ds.getDsId());
		Da exit1 = null;
		boolean dsresult = new GetDSResult().queryResult(dsatr, ds.getLogic());
		if (dsresult) {
			Da da = ds.getDaByTrueExit();
			exit1 = ds.getDaByFalseExit();
			stack.push(exit1);
			if (da.getIfAuto() == 0 && da.getTriggertype().equals("event")) {
				dadao.changeState(da, "AffirmPending");
			} else if (da.getIfAuto() == 1
					&& da.getTriggertype().equals("event")) {
				da.setExcuteStart(new Timestamp(System.currentTimeMillis()));
				dadao.changeState(da, "Executing");
			}
		} else {
			exit1 = ds.getDaByTrueExit();
			Da da = ds.getDaByFalseExit();
			stack.push(exit1);
			if (da.getIfAuto() == 0 && da.getTriggertype().equals("event")) {
				dadao.changeState(da, "AffirmPending");
			} else if (da.getIfAuto() == 1
					&& da.getTriggertype().equals("event")) {
				da.setExcuteStart(new Timestamp(System.currentTimeMillis()));
				dadao.changeState(da, "Executing");
			}
		}
		// switch (dsatr.get(i).getSymbol()) {
		// case 1:
		// if (atr.getValue().floatValue() == atrvalue
		// .floatValue()) {
		// Da da = ds.getDaByTrueExit();
		// exit1 = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// } else {
		// exit1 = ds.getDaByTrueExit();
		// Da da = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// }
		// break;
		// case 2:
		// if (atr.getValue().floatValue() > atrvalue
		// .floatValue()) {
		// Da da = ds.getDaByTrueExit();
		// exit1 = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// } else {
		// exit1 = ds.getDaByTrueExit();
		// Da da = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// }
		// break;
		// case 3:
		// if (atr.getValue().floatValue() < atrvalue
		// .floatValue()) {
		// Da da = ds.getDaByTrueExit();
		// exit1 = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// } else {
		// exit1 = ds.getDaByTrueExit();
		// Da da = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// }
		// break;
		// case 4:
		// if (atr.getValue().floatValue() >= atrvalue
		// .floatValue()) {
		// Da da = ds.getDaByTrueExit();
		// exit1 = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// } else {
		// exit1 = ds.getDaByTrueExit();
		// Da da = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// }
		// break;
		// case 5:
		// if (atr.getValue().floatValue() <= atrvalue
		// .floatValue()) {
		// Da da = ds.getDaByTrueExit();
		// exit1 = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// } else {
		// exit1 = ds.getDaByTrueExit();
		// Da da = ds.getDaByFalseExit();
		// stack.push(exit1);
		// if (da.getIfAuto() == 0
		// && da.getTriggertype().equals("event")) {
		// dadao.changeState(da, "AffirmPending");
		// } else if (da.getIfAuto() == 1
		// && da.getTriggertype().equals("event")) {
		// da.setExcuteStart(new Timestamp(System
		// .currentTimeMillis()));
		// dadao.changeState(da, "Executing");
		// // List<Da> tplist = new ArrayList<Da>();
		// // tplist.add(da);
		// // execute(tplist);
		// }
		// }
		// break;
		// }
		System.out.println("finish");
		ExecuteUndoDaService executeUndoDaService = new ExecuteUndoDaService();
		executeUndoDaService.executeUndoDa(stack.pop());
		// sign follows
	}
}
