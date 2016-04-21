package com.service.def;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.dao.AtrributeDAO;
import com.dao.DaDAO;
import com.dao.EventTriggerDAO;
import com.dao.TriggerAtrDAO;
import com.dao.def.Create;
import com.dao.def.GetAtr;
import com.dao.def.GetCA;
import com.dao.def.GetDA;
import com.dao.def.GetDS;
import com.dao.def.GetEventTrigger;
import com.dao.def.GetISP;
import com.dao.def.SelectSeq;
import com.domain.EventTrigger;
import com.domain.TriggerAtr;
import com.domain.TriggerAtrId;
import com.domain.def.CAforCopy;
import com.domain.def.DAAtrforCopy;
import com.domain.def.DAforCopy;
import com.domain.def.DSAtrforCopy;
import com.domain.def.DSforCopy;
import com.domain.def.EventTriggerforCopy;
import com.domain.def.ISPforCopy;
import com.domain.def.SequenceforCopy;
import com.domain.def.TriggerAtrforCopy;

public class Insert {
	private DaDAO dadao = new DaDAO();

	public void createISP(Integer isp_id) throws CloneNotSupportedException{
		GetISP getisp = new GetISP();
		ISPforCopy isp = new ISPforCopy();
		ISPforCopy newisp = new ISPforCopy();
		Create create = new Create();
		GetCA getca = new GetCA();
		GetDA getda = new GetDA();
		GetDS getds = new GetDS();
		GetAtr getatr = new GetAtr();
		SelectSeq ss = new SelectSeq();
		
		isp = getisp.queryISP(isp_id);
		newisp = (ISPforCopy)isp.clone();
		newisp.setIfdef(0);
        Integer newispid = 	create.createISP(newisp, null, null, null).getISP_id();
        isp.setChange_isp_id(newispid);
		
		List<CAforCopy> calist = new ArrayList<CAforCopy>();
		List<DAforCopy> dalist = new ArrayList<DAforCopy>();
		List<DSforCopy> dslist = new ArrayList<DSforCopy>();
		List<DSforCopy> newdslist = new ArrayList<DSforCopy>();
		
		calist = getca.queryCAByBelong(isp_id);
		for(int i=0;i<calist.size();i++){
			CAforCopy tempca = (CAforCopy)calist.get(i).clone();
			tempca.setIfdef(0);
			tempca.setDefbelong(calist.get(i).getCA_id());
			tempca.setISPbelong(newispid);
			Integer newcaid = create.createCA(tempca);
			calist.get(i).setChange_ca_id(newcaid);
			
			List<DAforCopy> cada = getda.queryDAByBelong(calist.get(i).getCA_id());
			for(int j=0;j<cada.size();j++){
				DAforCopy da = (DAforCopy)cada.get(j).clone();
				da.setCAbelong(newcaid);
				da.setDefbelong(cada.get(j).getDA_id());
				da.setIfdef(0);
				
				if(da.getTriggertype().equals("State")&&da.getEvent()!=null){
					GetEventTrigger geteventtrigger = new GetEventTrigger();
					// store the event data
					EventTriggerforCopy event = new EventTriggerforCopy();
					// store the end event data
					// EventTriggerforCopy endevent = new EventTriggerforCopy();
					
					// get the event by isp's event id
					event = geteventtrigger.getTrigger(da.getEvent());
					// get the end event by isp's end event id

					// endevent = geteventtrigger.getTrigger(tempda.getEndEvent());
					// System.out.println(7+" "+endevent);
					// store into database and get the new trigger id

					Integer newtriggerid = create.createEventTrigger(event);
					// Integer newendtriggerid =
					// create.createEventTrigger(endevent);

					event.setChange_trigger_id(newtriggerid);
					// endevent.setChange_trigger_id(newendtriggerid);

					List<TriggerAtrforCopy> tac = new ArrayList<TriggerAtrforCopy>();
					
					tac = getatr.queryTriggerAtr(event.getTrigger_id());
					for (int n = 0; n < tac.size(); n++) {
						tac.get(n).setTrigger_id(event.getChange_trigger_id());
						create.createTriggerAtr(tac.get(n));
					}
					// tac = getatr.queryTriggerAtr(endevent.getTrigger_id());
					// for (int n = 0; n < tac.size(); n++) {
					// tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
					// create.createTriggerAtr(tac.get(n));
					// }

					da.setEvent(newtriggerid);
				}
				Integer newdaid = create.createDA(da);
				cada.get(j).setChange_da_id(newdaid);
				dalist.add(da);
				
				//da attribute
				List<DAAtrforCopy> daatrlist = getatr.queryDAAtr(cada.get(i).getDA_id());
				for(DAAtrforCopy daatr : daatrlist){
					DAAtrforCopy tempatr = new DAAtrforCopy();
					tempatr.setDA_id(cada.get(i).getChange_da_id());
					tempatr.setAtr_id(daatr.getAtr_id());
					tempatr.setValue(null);
					create.createDAAtr(tempatr);
				}
			}
			
			List<DSforCopy> cads = getds.queryDSByCA(calist.get(i).getCA_id());
			for(int j=0;j<cads.size();j++){
				DSforCopy ds = (DSforCopy)cads.get(j).clone();
				ds.setCAbelong(newcaid);
				ds.setDSbelong(cads.get(j).getDS_id());
				ds.setIfdef(0);
				// ds hai buneng cun 
				dslist.add(cads.get(j));
				newdslist.add(ds);
			}
		}
		
		for(int i=0;i<dslist.size();i++){
			Integer dapre = null;
			Integer trueexit = null;
			Integer falseexit = null;
			for(DAforCopy da : dalist){
				if(dslist.get(i).getPreDA().intValue()==da.getDA_id().intValue())
					dapre = da.getChange_da_id();
				else if(dslist.get(i).getTrueExit().intValue()==da.getDA_id().intValue())
					trueexit = da.getChange_da_id();
				else if(dslist.get(i).getFalseExit().intValue()==da.getDA_id().intValue())
					falseexit = da.getChange_da_id();
			}
			if(dapre!=null&&trueexit!=null&&falseexit!=null){
				newdslist.get(i).setPreDA(dapre);
				newdslist.get(i).setTrueExit(trueexit);
				newdslist.get(i).setFalseExit(falseexit);
				Integer newdsid = create.createDS(newdslist.get(i));
				dslist.get(i).setChange_ds_id(newdsid);
			}
		}
		
		//sequence
		for(int i=0;i<dalist.size();i++){
			List<SequenceforCopy> slist = ss.querySeqByPreDA(dalist.get(i).getDA_id());
			for(SequenceforCopy sc: slist){
				if(sc.getPost_type().equals("DA")){
					SequenceforCopy news = new SequenceforCopy();
					news.setPre_type("DA");
					news.setPost_type("DA");
					news.setPre(dalist.get(i).getChange_da_id());
					for(DAforCopy da: dalist){
						if(da.getDA_id().intValue()==sc.getPost().intValue()){
							news.setPost(da.getChange_da_id());
						}
					}
					create.createSeq(news);
				}else if(sc.getPost_type().equals("DS")){
					SequenceforCopy news = new SequenceforCopy();
					news.setPre_type("DA");
					news.setPre(dalist.get(i).getChange_da_id());
					news.setPost_type("DS");
					for(DSforCopy ds : dslist){
						if(ds.getDS_id().intValue()==sc.getPost().intValue())
							news.setPost(ds.getChange_ds_id());
					}
					create.createSeq(news);
				}
			}
		}
		//ds attribute
		for(DSforCopy ds : dslist){
			List<DSAtrforCopy> dsatrlist = getatr.queryDSAtr(ds.getDS_id());
			for(DSAtrforCopy dsatr : dsatrlist){
				dsatr.setDS_id(ds.getChange_ds_id());
				create.createDSAtr(dsatr);
			}
		}
	}
	/*
	 * public void createISP(Integer isp_id) throws CloneNotSupportedException {
	 * // get ISP by ISP id GetISP getisp = new GetISP(); ISPforCopy isp = new
	 * ISPforCopy(); ISPforCopy newisp = new ISPforCopy(); isp =
	 * getisp.queryISP(isp_id); // newisp = getisp.queryISP(1); newisp =
	 * (ISPforCopy) isp.clone();
	 * 
	 * // for ds copy, DA_DS_list contains all das which are belong the isp
	 * List<DAforCopy> DA_DS_list = new ArrayList<DAforCopy>();
	 * 
	 * // create a new isp in database, and get the ISP id Create create = new
	 * Create();
	 * 
	 * // use for select atr table GetAtr getatr = new GetAtr();
	 * 
	 * // check if the isp's trigger type if the type is 'state', copy it's //
	 * event_trigger table if
	 * (isp.getTriggertype().equals("state")&&isp.getEvent()!=null) {
	 * GetEventTrigger geteventtrigger = new GetEventTrigger(); // store the
	 * event data EventTriggerforCopy event = new EventTriggerforCopy(); //
	 * store the end event data EventTriggerforCopy endevent = new
	 * EventTriggerforCopy();
	 * 
	 * // get the event by isp's event id event =
	 * geteventtrigger.getTrigger(isp.getEvent()); // get the end event by isp's
	 * end event id endevent = geteventtrigger.getTrigger(isp.getEndevent());
	 * 
	 * // store into database and get the new trigger id Integer newtriggerid =
	 * create.createEventTrigger(event); Integer newendtriggerid =
	 * create.createEventTrigger(endevent);
	 * 
	 * event.setChange_trigger_id(newtriggerid);
	 * endevent.setChange_trigger_id(newendtriggerid);
	 * 
	 * // copy the Trigger_Attribute table List<TriggerAtrforCopy> tac = new
	 * ArrayList<TriggerAtrforCopy>(); tac =
	 * getatr.queryTriggerAtr(event.getTrigger_id()); for (int n = 0; n <
	 * tac.size(); n++) {
	 * tac.get(n).setTrigger_id(event.getChange_trigger_id());
	 * create.createTriggerAtr(tac.get(n)); } tac =
	 * getatr.queryTriggerAtr(endevent.getTrigger_id()); for (int n = 0; n <
	 * tac.size(); n++) {
	 * tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
	 * create.createTriggerAtr(tac.get(n)); }
	 * 
	 * newisp.setEvent(newtriggerid); newisp.setEndevent(newendtriggerid); }
	 * 
	 * // create new isp newisp = create.createISP(newisp);
	 * isp.setChange_isp_id(newisp.getISP_id());
	 * 
	 * // get CAs which are belong the ISP below GetCA getca = new GetCA();
	 * List<CAforCopy> list = new ArrayList<CAforCopy>(); list =
	 * getca.queryCAByBelong(isp.getISP_id()); isp.setCalist(list);
	 * 
	 * // create a list of new ca in database, change it's ispbelong
	 * List<CAforCopy> calisttemp = new ArrayList<CAforCopy>(); Integer
	 * ispidchangge = newisp.getISP_id();
	 * 
	 * GetDA getda = new GetDA();
	 * 
	 * for (int i = 0; i < list.size(); i++) { // get the das which are belong
	 * ca List<DAforCopy> dalisttemp = new ArrayList<DAforCopy>(); dalisttemp =
	 * getda.queryDAByBelong(list.get(i).getCA_id());
	 * list.get(i).setDalist(dalisttemp); // copy ca param CAforCopy tempca =
	 * (CAforCopy) list.get(i).clone(); // change ca's ispbelong
	 * tempca.setISPbelong(ispidchangge);
	 * 
	 * // copy the event trigger and change the new trigger id if
	 * (list.get(i).getTriggertype
	 * ().equals("state")&&list.get(i).getEvent()!=null) { GetEventTrigger
	 * geteventtrigger = new GetEventTrigger(); // store the event data
	 * EventTriggerforCopy event = new EventTriggerforCopy(); // store the end
	 * event data EventTriggerforCopy endevent = new EventTriggerforCopy();
	 * 
	 * // get the event by isp's event id event =
	 * geteventtrigger.getTrigger(list.get(i).getEvent()); // get the end event
	 * by isp's end event id endevent = geteventtrigger
	 * .getTrigger(list.get(i).getEndevent());
	 * 
	 * // store into database and get the new trigger id Integer newtriggerid =
	 * create.createEventTrigger(event); Integer newendtriggerid =
	 * create.createEventTrigger(endevent);
	 * 
	 * event.setChange_trigger_id(newtriggerid);
	 * endevent.setChange_trigger_id(newendtriggerid);
	 * 
	 * // copy the Trigger_Attribute table List<TriggerAtrforCopy> tac = new
	 * ArrayList<TriggerAtrforCopy>(); tac =
	 * getatr.queryTriggerAtr(event.getTrigger_id()); for (int n = 0; n <
	 * tac.size(); n++) {
	 * tac.get(n).setTrigger_id(event.getChange_trigger_id());
	 * create.createTriggerAtr(tac.get(n)); } tac =
	 * getatr.queryTriggerAtr(endevent.getTrigger_id()); for (int n = 0; n <
	 * tac.size(); n++) {
	 * tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
	 * create.createTriggerAtr(tac.get(n)); }
	 * 
	 * tempca.setEvent(newtriggerid); tempca.setEndevent(newendtriggerid); } //
	 * insert into database Integer newcaid = create.createCA(tempca);
	 * tempca.setCA_id(newcaid); // record the ca change id
	 * list.get(i).setChange_ca_id(newcaid); // record the das belonged this ca
	 * List<DAforCopy> newdalist = new ArrayList<DAforCopy>(); for (int j = 0; j
	 * < dalisttemp.size(); j++) { DAforCopy tempda = (DAforCopy)
	 * dalisttemp.get(j).clone();
	 * 
	 * tempda.setCAbelong(newcaid);
	 * 
	 * // copy the event trigger and change the new trigger id if
	 * (tempda.getTriggertype().equals("state")&&tempda.getEvent()!=null) {
	 * GetEventTrigger geteventtrigger = new GetEventTrigger(); // store the
	 * event data EventTriggerforCopy event = new EventTriggerforCopy(); //
	 * store the end event data // EventTriggerforCopy endevent = new
	 * EventTriggerforCopy(); //!!genju ca gai // get the event by isp's event
	 * id event = geteventtrigger.getTrigger(tempda.getEvent()); // get the end
	 * event by isp's end event id // endevent =
	 * geteventtrigger.getTrigger(tempda.getEndEvent());
	 * 
	 * // store into database and get the new trigger id Integer newtriggerid =
	 * create.createEventTrigger(event); // Integer newendtriggerid = create //
	 * .createEventTrigger(endevent);
	 * 
	 * event.setChange_trigger_id(newtriggerid); //
	 * endevent.setChange_trigger_id(newendtriggerid);
	 * 
	 * // copy the Trigger_Attribute table List<TriggerAtrforCopy> tac = new
	 * ArrayList<TriggerAtrforCopy>(); tac =
	 * getatr.queryTriggerAtr(event.getTrigger_id()); for (int n = 0; n <
	 * tac.size(); n++) {
	 * tac.get(n).setTrigger_id(event.getChange_trigger_id());
	 * create.createTriggerAtr(tac.get(n)); } // tac =
	 * getatr.queryTriggerAtr(endevent.getTrigger_id()); // for (int n = 0; n <
	 * tac.size(); n++) { // tac.get(n).setTrigger_id( //
	 * endevent.getChange_trigger_id()); // create.createTriggerAtr(tac.get(n));
	 * // }
	 * 
	 * tempda.setEvent(newtriggerid); // tempda.setEndEvent(newendtriggerid); }
	 * // copy da and get the new da's id Integer newdaid =
	 * create.createDA(tempda); tempda.setDA_id(newdaid); newdalist.add(tempda);
	 * // record the new da's id dalisttemp.get(j).setChange_da_id(newdaid); //
	 * use the same da instant because the instant need to add ds
	 * DA_DS_list.add(dalisttemp.get(j)); } tempca.setDalist(newdalist); // add
	 * executed ca into calist calisttemp.add(tempca); }
	 * 
	 * // copying ds must be executed when all the das ware completed for ds //
	 * has preda and exit da GetDS getds = new GetDS(); for (int i = 0; i <
	 * DA_DS_list.size(); i++) { // this ds is the orign one List<DSforCopy>
	 * dslist = getds.queryDSByPre(DA_DS_list.get(i).getDA_id());
	 * System.out.println("ds pre id+"+DA_DS_list.get(i).getDA_id()); // check
	 * whether the da has ds if (dslist != null&&dslist.size()>0) {
	 * for(DSforCopy ds : dslist){ // set the ds into it's pre da
	 * DA_DS_list.get(i).setDs((DSforCopy) ds.clone());
	 * ds.setPreDA(DA_DS_list.get(i).getChange_da_id());
	 * System.out.println("change da id"+DA_DS_list.get(i).getChange_da_id());
	 * // change the trueExit and falseExit Integer trueExit = ds.getTrueExit();
	 * Integer falseExit = ds.getFalseExit(); for (int j = 0; j <
	 * DA_DS_list.size(); j++) { if
	 * (DA_DS_list.get(j).getDA_id().equals(trueExit)) trueExit =
	 * DA_DS_list.get(j).getChange_da_id(); if
	 * (DA_DS_list.get(j).getDA_id().equals(falseExit)) falseExit =
	 * DA_DS_list.get(j).getChange_da_id(); } ds.setTrueExit(trueExit);
	 * ds.setFalseExit(falseExit); // ds.setCAbelong(); //!!!!!!!!!!!!!!!ca id
	 * bujianle // copy the ds into database Integer newdsid =
	 * create.createDS(ds); // rewrite the change ds id
	 * 
	 * //!!!!!!!!!!!!diao yong de shihou xuyao ba ds gaicheng dslist!!!!!!!!!!
	 * DA_DS_list.get(i).getDs().setChange_ds_id(newdsid); } } }
	 * 
	 * SelectSeq ss = new SelectSeq(); // select sequence by ca for (int i = 0;
	 * i < list.size(); i++) { SequenceforCopy seq = new SequenceforCopy(); seq
	 * = ss.querySeqByPreCA(list.get(i).getCA_id()); if (seq.getPost_type() !=
	 * null) { // change the pre and post to new ca or da
	 * seq.setPre(list.get(i).getChange_ca_id());
	 * System.out.println(seq.getPost_type()); if
	 * (seq.getPost_type().equals("CA")) { for (int j = 0; j < list.size(); j++)
	 * { if (list.get(j).getCA_id().equals(seq.getPost())) {
	 * seq.setPost(list.get(j).getChange_ca_id()); } } } else { for (int j = 0;
	 * j < DA_DS_list.size(); j++) { if
	 * (DA_DS_list.get(j).getDA_id().equals(seq.getPost())) {
	 * seq.setPost(DA_DS_list.get(j).getChange_da_id()); } } } // copy into
	 * database create.createSeq(seq); } }
	 * 
	 * // copy ds by pre da, and copy the daatr table for (int i = 0; i <
	 * DA_DS_list.size(); i++) { List<SequenceforCopy> seqlist = new
	 * ArrayList<SequenceforCopy>(); seqlist =
	 * ss.querySeqByPreDA(DA_DS_list.get(i).getDA_id()); // the sequence may
	 * contains da-ds for(SequenceforCopy seq : seqlist){ for (int j = 0; j < 1;
	 * j++) { if (seq.getPost_type() != null) { // change the pre and post to
	 * new ca or da seq.setPre(DA_DS_list.get(i).getChange_da_id()); if
	 * (seq.getPost_type().equals("CA")) { for (int h = 0; h < list.size(); h++)
	 * { if (list.get(h).getCA_id().equals(seq.getPost())) {
	 * seq.setPost(list.get(h).getChange_ca_id()); } } } else if
	 * (seq.getPost_type().equals("DA")) { for (int h = 0; h <
	 * DA_DS_list.size(); h++) { if (DA_DS_list.get(h).getDA_id()
	 * .equals(seq.getPost())) {
	 * seq.setPost(DA_DS_list.get(h).getChange_da_id()); } } } else if
	 * (seq.getPost_type().equals("DS")) { for (int h = 0; h <
	 * DA_DS_list.size(); h++) { if (DA_DS_list.get(h).getDs() != null) if
	 * (DA_DS_list.get(h).getDs().getDS_id() .equals(seq.getPost())) {
	 * seq.setPost(DA_DS_list.get(h).getDs() .getChange_ds_id()); } } } // copy
	 * into database create.createSeq(seq); } } } // copy atr table
	 * List<DAAtrforCopy> daatr = new ArrayList<DAAtrforCopy>();
	 * List<DSAtrforCopy> dsatr = new ArrayList<DSAtrforCopy>(); // da attribute
	 * daatr = getatr.queryDAAtr(DA_DS_list.get(i).getDA_id()); for (int n = 0;
	 * n < daatr.size(); n++) { if (!daatr.get(n).getAtr_id().equals(0)) {
	 * daatr.get(n).setDA_id(DA_DS_list.get(i).getChange_da_id());
	 * create.createDAAtr(daatr.get(n)); } } // ds attribute if
	 * (DA_DS_list.get(i).getDs() != null) { dsatr =
	 * getatr.queryDSAtr(DA_DS_list.get(i).getDs().getDS_id()); for (int n = 0;
	 * n < dsatr.size(); n++) { if (DA_DS_list.get(i).getDs().getName() != null)
	 * { dsatr = getatr.queryDSAtr(DA_DS_list.get(i).getDs() .getDS_id()); if
	 * (!dsatr.get(n).getAtr_id().equals(0)) { dsatr.get(n) .setDS_id(
	 * DA_DS_list.get(i).getDs() .getChange_ds_id());
	 * create.createDSAtr(dsatr.get(n)); } } } } }
	 * 
	 * // set the new calist into newisp newisp.setCalist(calisttemp); }
	 */
	public Integer createCA(Integer ca_id, String triggertype,
			Timestamp planStarttime, Timestamp planEndtime, Integer ispId,
			String state) throws CloneNotSupportedException {
		// for ds copy, DA_DS_list contains all das which are belong the isp
		List<DAforCopy> DA_DS_list = new ArrayList<DAforCopy>();
		List<DSforCopy> dslist = new ArrayList<DSforCopy>();
		// create a new isp in database, and get the ISP id
		Create create = new Create();

		// use for select atr table
		GetAtr getatr = new GetAtr();

		// get CAs which are belong the ISP below
		GetCA getca = new GetCA();
		CAforCopy ca = new CAforCopy();
		System.out.println("1 " + ca_id + " " + ispId);
		ca = getca.queryCAbyid(ca_id);
		// ca.setISPbelong(isp_id);

		GetDA getda = new GetDA();

		// get the das which are belong ca
		List<DAforCopy> dalisttemp = new ArrayList<DAforCopy>();
		dalisttemp = getda.queryDAByBelong(ca.getCA_id());
		System.out.println("dalisttemp:" + dalisttemp.size());
		System.out.println(2 + " " + dalisttemp);
		ca.setDalist(dalisttemp);
		// copy ca param
		CAforCopy tempca = (CAforCopy) ca.clone();
		tempca.setDefbelong(ca_id);
		tempca.setIfdef(0);
		tempca.setTriggertype(triggertype);
		tempca.setPlanStarttime(planStarttime);
		tempca.setPlanEndtime(planEndtime);
		tempca.setISPbelong(ispId);
		tempca.setState(state);
		// copy the event trigger and change the new trigger id
		if (ca.getTriggertype().equals("state") && ca.getEvent() != null) {
			GetEventTrigger geteventtrigger = new GetEventTrigger();
			// store the event data
			EventTriggerforCopy event = new EventTriggerforCopy();
			// store the end event data
			EventTriggerforCopy endevent = new EventTriggerforCopy();

			// get the event by isp's event id
			event = geteventtrigger.getTrigger(ca.getEvent());
			// get the end event by isp's end event id
			endevent = geteventtrigger.getTrigger(ca.getEndevent());

			// store into database and get the new trigger id
			Integer newtriggerid = create.createEventTrigger(event);
			Integer newendtriggerid = create.createEventTrigger(endevent);

			event.setChange_trigger_id(newtriggerid);
			endevent.setChange_trigger_id(newendtriggerid);

			// copy the Trigger_Attribute table
			List<TriggerAtrforCopy> tac = new ArrayList<TriggerAtrforCopy>();
			tac = getatr.queryTriggerAtr(event.getTrigger_id());
			for (int n = 0; n < tac.size(); n++) {
				tac.get(n).setTrigger_id(event.getChange_trigger_id());
				create.createTriggerAtr(tac.get(n));
			}
			tac = getatr.queryTriggerAtr(endevent.getTrigger_id());
			for (int n = 0; n < tac.size(); n++) {
				tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
				create.createTriggerAtr(tac.get(n));
			}

			tempca.setEvent(newtriggerid);
			tempca.setEndevent(newendtriggerid);
		}
		// insert into database
		Integer newcaid = create.createCA(tempca);
		System.out.println(3 + " " + newcaid);
		tempca.setCA_id(newcaid);
		// record the ca change id
		ca.setChange_ca_id(newcaid);
		// record the das belonged this ca
		List<DAforCopy> newdalist = new ArrayList<DAforCopy>();
		for (int j = 0; j < dalisttemp.size(); j++) {
			DAforCopy tempda = (DAforCopy) dalisttemp.get(j).clone();

			tempda.setCAbelong(newcaid);

			// copy the event trigger and change the new trigger id
			if (tempda.getTriggertype().equals("state")
					&& tempda.getEvent() != null) {
				System.out.println(5 + " " + tempda.getEvent());
				GetEventTrigger geteventtrigger = new GetEventTrigger();
				// store the event data
				EventTriggerforCopy event = new EventTriggerforCopy();
				// store the end event data
				// EventTriggerforCopy endevent = new EventTriggerforCopy();

				// get the event by isp's event id
				event = geteventtrigger.getTrigger(tempda.getEvent());
				System.out.println(6 + " " + event);
				// get the end event by isp's end event id

				// !!!! da keneng meiyou jiesu de event
				// endevent = geteventtrigger.getTrigger(tempda.getEndEvent());
				// System.out.println(7+" "+endevent);
				// store into database and get the new trigger id

				Integer newtriggerid = create.createEventTrigger(event);
				// Integer newendtriggerid =
				// create.createEventTrigger(endevent);

				event.setChange_trigger_id(newtriggerid);
				// endevent.setChange_trigger_id(newendtriggerid);

				List<TriggerAtrforCopy> tac = new ArrayList<TriggerAtrforCopy>();
				tac = getatr.queryTriggerAtr(event.getTrigger_id());
				for (int n = 0; n < tac.size(); n++) {
					tac.get(n).setTrigger_id(event.getChange_trigger_id());
					create.createTriggerAtr(tac.get(n));
				}
				// tac = getatr.queryTriggerAtr(endevent.getTrigger_id());
				// for (int n = 0; n < tac.size(); n++) {
				// tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
				// create.createTriggerAtr(tac.get(n));
				// }

				tempda.setEvent(newtriggerid);
				// tempda.setEndEvent(newendtriggerid);
			}
			// copy da and get the new da's id
			Integer newdaid = create.createDA(tempda);
			System.out.println(4 + " " + newdaid);
			tempda.setDA_id(newdaid);
			newdalist.add(tempda);
			// record the new da's id
			dalisttemp.get(j).setChange_da_id(newdaid);
			// use the same da instant because the instant need to add ds
			DA_DS_list.add(dalisttemp.get(j));
		}
		tempca.setDalist(newdalist);

		// copying ds must be executed when all the das ware completed for ds
		// has preda and exit da
		GetDS getds = new GetDS();
		System.out.println("DA_DS:" + DA_DS_list.size());
		try {
			// this ds is the orign one
			// List<DSforCopy> dslist =
			// getds.queryDSByPre(DA_DS_list.get(i).getDA_id());
			dslist = getds.queryDSByCA(ca_id);
			System.out.println("insert-createca-dslist.size:"+dslist.size());
			// check whether the da has ds
			if (dslist != null && dslist.size() > 0) {
				for (DSforCopy ds : dslist) {
					System.out.println("ds copy step 1");
					// set the ds into it's pre da
					// DA_DS_list.get(i).getDs().add((DSforCopy) ds.clone());
					// DA_DS_list.get(i).setDs((DSforCopy) ds.clone());
					System.out.println("insert-createca-dsPreDA:"+ds.getPreDA().intValue()+" cabelong:"+dadao.findById(ds.getPreDA()).getCa().getCaId()+" ca_id:"+ca_id);
					if (dadao.findById(ds.getPreDA()).getCa().getCaId().intValue() == ca_id.intValue()) {
						boolean dsbool = false;
						for (DAforCopy da : DA_DS_list) {
							System.out.println("biedouwo:"+da.getDA_id().intValue());
							if (da.getDA_id().intValue()
									== ds.getPreDA().intValue()) {
								ds.setPreDA(da.getChange_da_id());
								dsbool = true;
								break;
							}
						}
						if(!dsbool)
							ds.setPreDA(null);
					} else {
						ds.setPreDA(null);
					}
					System.out.println("ds copy step 3");
					// change the trueExit and falseExit
					Integer trueExit = ds.getTrueExit();
					System.out.println("ds trueexit:"+ds.getTrueExit());
					Integer falseExit = ds.getFalseExit();
					System.out.println("ds falseexit:"+ds.getFalseExit());
					for (int j = 0; j < DA_DS_list.size(); j++) {
						if (DA_DS_list.get(j).getDA_id().intValue()==trueExit)
							trueExit = DA_DS_list.get(j).getChange_da_id();
						if (DA_DS_list.get(j).getDA_id().intValue()==falseExit)
							falseExit = DA_DS_list.get(j).getChange_da_id();
					}
					if (dadao.findById(trueExit).getCa().getCaId().intValue() == newcaid.intValue()) {
						ds.setTrueExit(trueExit);
					} else {
						ds.setTrueExit(null);
					}
					if (dadao.findById(falseExit).getCa().getCaId().intValue() == newcaid.intValue()) {
						ds.setFalseExit(falseExit);
					} else {
						ds.setFalseExit(null);
					}
					ds.setCAbelong(newcaid);
					// copy the ds into database
					Integer newdsid = create.createDS(ds);
					ds.setChange_ds_id(newdsid);
					// rewrite the change ds id

					// !!!!!!!!!!!!diao yong de shihou xuyao ba ds gaicheng
					// dslist!!!!!!!!!!
					// int size = DA_DS_list.get(i).getDs().size();
					// DA_DS_list.get(i).getDs().get(size-1).setChange_ds_id(newdsid);
					// DA_DS_list.get(i).getDs().setChange_ds_id(newdsid);
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("create ca finish");
		/*
		 * if(preDA!=null){ SequenceforCopy casequ = new SequenceforCopy();
		 * casequ.setPre_type("DA"); casequ.setPre(preDA);
		 * casequ.setPost_type("CA"); casequ.setPost(newcaid); }
		 * if(preCA!=null){ SequenceforCopy casequ = new SequenceforCopy();
		 * casequ.setPre_type("CA"); casequ.setPre(preCA);
		 * casequ.setPost_type("CA"); casequ.setPost(newcaid); }
		 */
		SelectSeq ss = new SelectSeq();
		// select sequence by ca
		/*
		 * for (int i = 0; i < 1; i++) { SequenceforCopy seq = new
		 * SequenceforCopy(); seq = ss.querySeqByPreCA(ca.getCA_id()); if
		 * (seq.getPost_type() != null) { // change the pre and post to new ca
		 * or da seq.setPre(ca.getChange_ca_id()); if
		 * (seq.getPost_type().equals("CA")) { for (int j = 0; j < 1; j++) { if
		 * (ca.getCA_id() == seq.getPost()) { seq.setPost(ca.getChange_ca_id());
		 * } } } else { for (int j = 0; j < DA_DS_list.size(); j++) { if
		 * (DA_DS_list.get(j).getDA_id() == seq.getPost()) {
		 * seq.setPost(DA_DS_list.get(j).getChange_da_id()); } } } // copy into
		 * database create.createSeq(seq); } }
		 */

		// copy ds by pre da, and copy the daatr table
		for (int i = 0; i < DA_DS_list.size(); i++) {
			List<SequenceforCopy> seqlist = new ArrayList<SequenceforCopy>();
			seqlist = ss.querySeqByPreDA(DA_DS_list.get(i).getDA_id());
			// the sequence may contains da-ds
			for (SequenceforCopy seq : seqlist) {
				// for (int j = 0; j < 1; j++) {
				if (seq.getPost_type() != null) {
					// change the pre and post to new ca or da
					seq.setPre(DA_DS_list.get(i).getChange_da_id());
					/*
					 * if (seq.getPost_type().equals("CA")) { for (int h = 0; h
					 * < 1; h++) { if (ca.getCA_id().equals(seq.getPost())) {
					 * seq.setPost(ca.getChange_ca_id()); } } } else
					 */
					if (seq.getPost_type().equals("DA")) {
						for (int h = 0; h < DA_DS_list.size(); h++) {
							if (DA_DS_list.get(h).getDA_id()
									.equals(seq.getPost())) {
								seq.setPost(DA_DS_list.get(h).getChange_da_id());
							}
						}
						create.createSeq(seq);
					}/*
					 * else if (seq.getPost_type().equals("DS")) { for (int h =
					 * 0; h < DA_DS_list.size(); h++) { if
					 * (DA_DS_list.get(h).getDs() != null &&
					 * DA_DS_list.get(h).getDs().size() > 0) { for (int a = 0; a
					 * < DA_DS_list.get(h) .getDs().size(); a++) { if
					 * (DA_DS_list.get(h).getDs().get(a) .getDS_id()
					 * .equals(seq.getPost())) { seq.setPost(DA_DS_list.get(h)
					 * .getDs().get(a) .getChange_ds_id()); } }
					 * 
					 * } } }
					 */
					// copy into database

				}
				// }
			}

			// copy atr table
			List<DAAtrforCopy> daatr = new ArrayList<DAAtrforCopy>();

			// da attribute
			daatr = getatr.queryDAAtr(DA_DS_list.get(i).getDA_id());
			for (int n = 0; n < daatr.size(); n++) {
				if (!daatr.get(n).getAtr_id().equals(0)) {
					daatr.get(n).setDA_id(DA_DS_list.get(i).getChange_da_id());
					create.createDAAtr(daatr.get(n));
				}
			}
			// ds attribute
			// ////////////////////////////////////////////////////////////////////////
			// if (DA_DS_list.get(i).getDs() != null
			// && DA_DS_list.get(i).getDs().size() > 0) {
			// for (int m = 0; m < DA_DS_list.get(i).getDs().size(); m++) {
			// dsatr = getatr.queryDSAtr(DA_DS_list.get(i).getDs().get(m)
			// .getDS_id());
			// for (int n = 0; n < dsatr.size(); n++) {
			// if (DA_DS_list.get(i).getDs().get(m) != null) {
			// dsatr = getatr.queryDSAtr(DA_DS_list.get(i).getDs()
			// .get(m).getDS_id());
			// if (!dsatr.get(n).getAtr_id().equals(0)) {
			// dsatr.get(n).setDS_id(
			// DA_DS_list.get(i).getDs().get(m)
			// .getChange_ds_id());
			// create.createDSAtr(dsatr.get(n));
			// }
			// }
			// }
			// }
			// }
		}
		List<DSAtrforCopy> dsatr = new ArrayList<DSAtrforCopy>();
		List<SequenceforCopy> seqlist = new ArrayList<SequenceforCopy>();
		for (DSforCopy ds : dslist) {
			seqlist = ss.querySeqByPostDS(ds.getDS_id());
			for (SequenceforCopy seq : seqlist) {
				for (DAforCopy dafor : DA_DS_list) {
					if (seq.getPre_type().equals("DA")
							&& seq.getPre().intValue() == dafor.getDA_id().intValue()) {
						SequenceforCopy tempseq = new SequenceforCopy();
						tempseq.setPre_type("DA");
						tempseq.setPre(dafor.getChange_da_id());
						tempseq.setPost_type("DS");
						tempseq.setPost(ds.getChange_ds_id());
						create.createSeq(tempseq);
					}
				}
			}

			dsatr = getatr.queryDSAtr(ds.getDS_id());
			for (DSAtrforCopy atrcopy : dsatr) {
				atrcopy.setDS_id(ds.getChange_ds_id());
				create.createDSAtr(atrcopy);
			}
		}
		return newcaid;
	}

	public Integer createDA(Integer da_id, Timestamp start_time,
			Timestamp end_time, String triggertype, List<Integer> pre_da_id,
			Integer caId, Integer startattributeId, Integer startsymbol,
			Float startvalue) throws CloneNotSupportedException {
		// create a new isp in database, and get the ISP id
		Create create = new Create();

		// use for select atr table
		GetAtr getatr = new GetAtr();

		GetDA getda = new GetDA();
		DAforCopy da = new DAforCopy();

		da = getda.queryDAByid(da_id);
		// da.setCAbelong(ca_id);
		DAforCopy tempda = (DAforCopy) da.clone();
		tempda.setDefbelong(da_id);
		tempda.setIfdef(0);
		tempda.setCAbelong(caId);
		// copy the event trigger and change the new trigger id
		if (tempda.getTriggertype().equals("state")
				&& tempda.getEvent() != null) {
			GetEventTrigger geteventtrigger = new GetEventTrigger();
			// store the event data
			EventTriggerforCopy event = new EventTriggerforCopy();
			// store the end event data
			// EventTriggerforCopy endevent = new EventTriggerforCopy();
			// !!!genju ca shangmian de gai
			// get the event by isp's event id
			event = geteventtrigger.getTrigger(tempda.getEvent());
			// get the end event by isp's end event id
			// endevent = geteventtrigger.getTrigger(tempda.getEndEvent());

			// store into database and get the new trigger id
			Integer newtriggerid = create.createEventTrigger(event);
			// Integer newendtriggerid = create.createEventTrigger(endevent);

			event.setChange_trigger_id(newtriggerid);
			// endevent.setChange_trigger_id(newendtriggerid);

			// copy the Trigger_Attribute table
			List<TriggerAtrforCopy> tac = new ArrayList<TriggerAtrforCopy>();
			tac = getatr.queryTriggerAtr(event.getTrigger_id());
			for (int n = 0; n < tac.size(); n++) {
				tac.get(n).setTrigger_id(event.getChange_trigger_id());
				create.createTriggerAtr(tac.get(n));
			}
			// tac = getatr.queryTriggerAtr(endevent.getTrigger_id());
			// for (int n = 0; n < tac.size(); n++) {
			// tac.get(n).setTrigger_id(endevent.getChange_trigger_id());
			// create.createTriggerAtr(tac.get(n));
			// }

			tempda.setEvent(newtriggerid);
			// tempda.setEndEvent(newendtriggerid);
		}
		// copy da and get the new da's id
		tempda.setPlanStart(start_time);
		tempda.setPlanEnd(end_time);
		tempda.setTriggertype(triggertype);

		Integer newdaid = create.createDA(tempda);

		tempda.setDA_id(newdaid);
		da.setChange_da_id(newdaid);

		// copy the daatr table
		for (int i = 0; i < 1; i++) {
			// copy atr table
			List<DAAtrforCopy> daatr = new ArrayList<DAAtrforCopy>();
			// da attribute
			daatr = getatr.queryDAAtr(da.getDA_id());
			for (int n = 0; n < daatr.size(); n++) {
				if (daatr.get(n).getAtr_id() != null) {
					daatr.get(n).setDA_id(da.getChange_da_id());
					create.createDAAtr(daatr.get(n));
				}
			}
		}

		if (pre_da_id != null) {
			SequenceforCopy seq = new SequenceforCopy();
			for (Integer id : pre_da_id) {
				seq.setPre_type("DA");
				seq.setPre(id);
				seq.setPost_type("DA");
				seq.setPost(newdaid);
				create.createSeq(seq);
			}
		}

		if (triggertype.equals("state")&&startattributeId!=null) {
			EventTrigger starteventtrigger = new EventTrigger();
			EventTriggerDAO etd = new EventTriggerDAO();
			starteventtrigger = etd.createTrigger(starteventtrigger.getLogic(),
					starteventtrigger.getCode());
			TriggerAtr startTA = new TriggerAtr();
			startTA.setSymbol(startsymbol);
			startTA.setValue(startvalue);
			TriggerAtrId starttaid = new TriggerAtrId();
			starttaid.setEventTrigger(starteventtrigger);
			AtrributeDAO atrDAO = new AtrributeDAO();
			starttaid.setAtrribute(atrDAO.findById(startattributeId));
			startTA.setId(starttaid);
			TriggerAtrDAO triggeratrdao = new TriggerAtrDAO();
			triggeratrdao.insert(startTA);
			DaDAO dadao = new DaDAO();
			dadao.updateStartEvent(newdaid, starteventtrigger.getTriggerId());
		}

		return newdaid;
	}

	public Integer CreateDS(Integer ds_id, Integer preDA, Integer trueDA,
			Integer falseDA, Integer caId) throws CloneNotSupportedException {
		// create a new isp in database, and get the ISP id
		Create create = new Create();

		// use for select atr table
		GetAtr getatr = new GetAtr();

		GetDS getds = new GetDS();
		DSforCopy ds = new DSforCopy();

		ds = getds.queryDSById(ds_id);
		DSforCopy tempds = new DSforCopy();
		tempds = (DSforCopy) ds.clone();
		tempds.setPreDA(preDA);
		tempds.setTrueExit(trueDA);
		tempds.setFalseExit(falseDA);
		tempds.setDSbelong(ds_id);
		tempds.setIfdef(0);
		tempds.setCAbelong(caId);

		Integer newdsid = create.createDS(tempds);
		tempds.setDS_id(newdsid);
		ds.setChange_ds_id(newdsid);

		List<DSAtrforCopy> dsatr = new ArrayList<DSAtrforCopy>();
		dsatr = getatr.queryDSAtr(ds_id);
		for (int i = 0; i < dsatr.size(); i++) {
			if (dsatr.get(i).getAtr_id() != null) {
				dsatr.get(i).setDS_id(newdsid);
				create.createDSAtr(dsatr.get(i));
			}
		}

		// It doesn't need to save 'pre ds and post da', because the ds has
		// trueexit and falseexit
		//ԭ����ֱ�Ӹ�ds˳���������ȴ�����ٴ�����
//		SequenceforCopy seq = new SequenceforCopy();
//		seq.setPre_type("DA");
//		seq.setPre(preDA);
//		seq.setPost_type("DS");
//		seq.setPost(newdsid);
//		create.createSeq(seq);
		
		return newdsid;
	}
}
