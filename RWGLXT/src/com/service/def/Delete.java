package com.service.def;

import java.util.ArrayList;
import java.util.List;

import com.dao.def.DeleteISP;
import com.dao.def.GetCA;
import com.dao.def.GetDA;
import com.dao.def.GetEventTrigger;
import com.dao.def.GetISP;
import com.domain.def.CAforCopy;
import com.domain.def.DAforCopy;
import com.domain.def.EventTriggerforCopy;
import com.domain.def.ISPforCopy;

public class Delete {
//	public static void main(String[] args) {
//        deleteISP(2);
//		//deleteCA(13);
//		//deleteDA(31);
//	}

	public void deleteISP(Integer isp_id) {

		DeleteISP deleteisp = new DeleteISP();

		GetISP getisp = new GetISP();
		GetCA getca = new GetCA();
		GetDA getda = new GetDA();
		GetEventTrigger getevent = new GetEventTrigger();

		// delete the trigger of the isp
		ISPforCopy isp = new ISPforCopy();
		isp = getisp.queryISP(isp_id);
		List<CAforCopy> calist = new ArrayList<CAforCopy>();
		calist = getca.queryCAByBelong(isp.getISP_id());

		for (int i = 0; i < calist.size(); i++) {
			// delete ca
			deleteisp.deleteSeqByCAid(calist.get(i).getCA_id());
			List<DAforCopy> dalist = new ArrayList<DAforCopy>();
			dalist = getda.queryDAByBelong(calist.get(i).getCA_id());

			for (int j = 0; j < dalist.size(); j++) {

				if (dalist.get(j).getTriggertype().equals("state")) {
					EventTriggerforCopy daevent = new EventTriggerforCopy();
					EventTriggerforCopy daendevent = new EventTriggerforCopy();

					daevent = getevent.getTrigger(dalist.get(j).getEvent());
					daendevent = getevent.getTrigger(dalist.get(j)
							.getEndEvent());
					deleteisp.deleteTriggerByid(daevent.getTrigger_id());
					deleteisp.deleteTriggerByid(daendevent.getTrigger_id());
				}
				deleteisp.deleteSeqByDAid(dalist.get(j).getDA_id());
			}
			// delete the event trigger which is belong the ca
			if (calist.get(i).getTriggertype().equals("state")) {
				EventTriggerforCopy caevent = new EventTriggerforCopy();
				EventTriggerforCopy caendevent = new EventTriggerforCopy();

				caevent = getevent.getTrigger(calist.get(i).getEvent());
				caendevent = getevent.getTrigger(calist.get(i).getEndevent());

				deleteisp.deleteTriggerByid(caevent.getTrigger_id());
				deleteisp.deleteTriggerByid(caendevent.getTrigger_id());
			}
		}

		//if the isp is a def, remove the follow function
		if (isp.getTriggertype().equals("state")) {
			EventTriggerforCopy ispevent = new EventTriggerforCopy();
			EventTriggerforCopy ispendevent = new EventTriggerforCopy();

			ispevent = getevent.getTrigger(isp.getEvent());
			ispendevent = getevent.getTrigger(isp.getEndevent());
			deleteisp.deleteTriggerByid(ispevent.getTrigger_id());
			deleteisp.deleteTriggerByid(ispendevent.getTrigger_id());
		}
		// ???
		deleteisp.deleteByISPid(isp_id);
	}

	public void deleteCA(Integer ca_id) {
		DeleteISP deleteisp = new DeleteISP();

		GetCA getca = new GetCA();
		GetDA getda = new GetDA();
		GetEventTrigger getevent = new GetEventTrigger();

		CAforCopy ca = new CAforCopy();
		ca = getca.queryCAbyid(ca_id);

		// delete ca
		deleteisp.deleteSeqByCAid(ca.getCA_id());
		List<DAforCopy> dalist = new ArrayList<DAforCopy>();
		dalist = getda.queryDAByBelong(ca.getCA_id());

		for (int j = 0; j < dalist.size(); j++) {

			if (dalist.get(j).getTriggertype().equals("state")) {
				EventTriggerforCopy daevent = new EventTriggerforCopy();
				EventTriggerforCopy daendevent = new EventTriggerforCopy();

				daevent = getevent.getTrigger(dalist.get(j).getEvent());
				daendevent = getevent.getTrigger(dalist.get(j).getEndEvent());
				deleteisp.deleteTriggerByid(daevent.getTrigger_id());
				deleteisp.deleteTriggerByid(daendevent.getTrigger_id());
			}
			deleteisp.deleteSeqByDAid(dalist.get(j).getDA_id());
		}
	//	deleteisp
		// delete the event trigger which is belong the ca
		if (ca.getTriggertype().equals("state")) {
			EventTriggerforCopy caevent = new EventTriggerforCopy();
			EventTriggerforCopy caendevent = new EventTriggerforCopy();

			caevent = getevent.getTrigger(ca.getEvent());
			caendevent = getevent.getTrigger(ca.getEndevent());

			deleteisp.deleteTriggerByid(caevent.getTrigger_id());
			deleteisp.deleteTriggerByid(caendevent.getTrigger_id());
		}
		deleteisp.deleteCaById(ca_id);

	}

	public void deleteDA(Integer da_id) {
		DeleteISP deleteisp = new DeleteISP();

		GetDA getda = new GetDA();
		GetEventTrigger getevent = new GetEventTrigger();

		// delete da
		DAforCopy da = new DAforCopy();
        da = getda.queryDAByid(da_id);
        
		if (da.getTriggertype().equals("state")) {
			EventTriggerforCopy daevent = new EventTriggerforCopy();
			EventTriggerforCopy daendevent = new EventTriggerforCopy();

			daevent = getevent.getTrigger(da.getEvent());
			daendevent = getevent.getTrigger(da.getEndEvent());
			deleteisp.deleteTriggerByid(daevent.getTrigger_id());
			deleteisp.deleteTriggerByid(daendevent.getTrigger_id());
		}
		deleteisp.deleteSeqByDAid(da.getDA_id());
		deleteisp.deleteDaById(da_id);
	}
	
	public void deleteDS(Integer ds_id){
		DeleteISP deleteisp = new DeleteISP();
		deleteisp.deleteDsById(ds_id);
	}
}
