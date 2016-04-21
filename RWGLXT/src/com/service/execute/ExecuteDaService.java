package com.service.execute;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

import com.dao.DaDAO;
import com.domain.Da;
import com.service.DaAtrService;

public class ExecuteDaService {
	DaDAO dadao = new DaDAO();
	
public List<Map> convert(List<Da> list){
		
		List<Map> result = new ArrayList<Map> ();
		Iterator<Da> iter = list.iterator(); 
		DaAtrService daatrservice= new DaAtrService();
		
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Da da = new Da();
			da = iter.next();
			map.put("id", da.getDaId());
			map.put("fightPostion", da.getFightPostion().getPostionId());
			map.put("fightPostionName", da.getFightPostion().getName());
			map.put("name", da.getName());
			map.put("description", da.getDescription() );
			map.put("triggertype", da.getTriggertype());
			map.put("priority", da.getPriority());
			map.put("state", da.getState());
			map.put("ifAuto", da.getIfAuto());
			map.put("ifdef", da.getIfdef());
			map.put("Type",da.getType().getTypeId());
			map.put("attribute", daatrservice.getDAAtr(da.getDaId()));
			map.put("ispid", da.getCa().getIsp().getIspId());
			map.put("ispname", da.getCa().getIsp().getName());
			if(da.getUsers()!=null){
				map.put("userId", da.getUsers().getUserid());
				map.put("userName", da.getUsers().getUserid());
			}
			else{
				map.put("userId", null);
				map.put("userName", null);
			}
				
			if(da.getEventTriggerByEvent()!=null)
				map.put("eventTriggerByEvent", da.getEventTriggerByEvent().getTriggerId());
			else
				map.put("eventTriggerByEvent", null);
			
			if(da.getCa()!=null)
				map.put("caId", da.getCa().getCaId());
			else
				map.put("caId", null);
			
			if(da.getEventTriggerByEndEvent()!=null)
				map.put("eventTriggerByEndEvent", da.getEventTriggerByEndEvent().getTriggerId());
			else
				map.put("eventTriggerByEndEvent", null);
			
			if(da.getDs()!=null)
				map.put("postDs", da.getDs().getDsId());
			else
				map.put("postDs", null);
			
			if(da.getDefbelong()!=null)
				map.put("defBelong", da.getDefbelong());
			else
				map.put("defBelong", null);
			
			if(da.getPlanStart()!=null)
				map.put("planStart", da.getPlanStart().toString());
			else
				map.put("planStart", "");
			
			if(da.getPlanEnd()!=null)
				map.put("planEnd", da.getPlanEnd().toString());
			else
				map.put("planEnd", "");
			
			if(da.getPlanDuration()!=null)
				map.put("planDuration", da.getPlanDuration().toString());
			else
				map.put("planDuration", "");
			
			if(da.getTerminate()!=null)
				map.put("terminate", da.getTerminate());
			else
				map.put("terminate", null);
			
			if(da.getExcuteStart()!=null)
				map.put("excuteStart", da.getExcuteStart().toString());
			else
				map.put("excuteStart", "");
			
			if(da.getExcuteEnd()!=null)
				map.put("excuteEnd", da.getExcuteEnd().toString());
			else
				map.put("excuteEnd", "");
			
			result.add(map);
		}
		return result;
	}

	public void daStart(Integer daId){
		Da da = dadao.findById(daId);
		da.setExcuteStart(new Timestamp(System.currentTimeMillis()));
		System.out.println(da.getExcuteStart());
		dadao.updateState(da, "Executing");
	}
	
	public void daFinish(Integer daId){
		Da da = dadao.findById(daId);
		
		if(da.getCode()!=null&&!da.getCode().equals("no data")&&!da.getCode().equals("нч")){
			String[] s = da.getCode().split(";");
			for(int i=0;i<s.length;i++){
				daexecute(daId, s[i]);
			}
		}
		
		da.setExcuteEnd(new Timestamp(System.currentTimeMillis()));
		dadao.updateState(da, "Finish");
		
		ExecuteFollowDaService executefollowdaservice = new ExecuteFollowDaService();
		List<Da> list = new ArrayList<Da>();
		list.add(da);
		executefollowdaservice.execute(list);
		ExecuteCAandISPService ecis = new ExecuteCAandISPService();
		ecis.executeFinishCA(da);
	}
	
	public List<Map> getDaByPosition(String state, Integer positionId){
		List<Da> dalist = dadao.getDaByPosition(state, positionId);
		
		return convert(dalist);
	}
	//exe
/*	public void daexecute(Integer daId, String code){
		String path = ServletActionContext.getRequest().getSession().getServletContext().getRealPath("/")+"tool\\";
		String s ="";
		Runtime rt = Runtime.getRuntime();
		Process pr = null;
		try{
			pr = rt.exec(path+"OPCWriter "+code,null,null);
			BufferedReader buff = new BufferedReader(new InputStreamReader(pr.getInputStream()));
			while((s=buff.readLine())!=null){
				System.out.println(s);
			}
			buff.close();
			pr.destroy();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	*/
	// bat:code = 192.168.1.7 Wonderware.KLPServer xxx 45
	public void daexecute(Integer daId, String code){
		String batname = daId.toString()+".bat";
	//	String batpath = "c:\\RWGLXT\\1\\"+batname;
		String batpath = "c:\\RWGLXT\\1";
		String content = "cd "+batpath +"\r\n"+"java -jar OPCWriter.jar "+code;
		try{
			FileWriter fw = null;
			try {
				fw = new FileWriter(batpath+"\\"+batname);
				fw.write(content);
			} catch (Exception e) {
				e.printStackTrace();
				System.exit(0);
			} finally {
				if (fw != null) {
					try {
						fw.close();
					} catch (Exception e) {
						e.printStackTrace();
						System.exit(0);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		sendOPC(daId, batpath+"\\"+batname);
	}
	
	public void sendOPC(Integer daId, String batpath){
		try{
			File file = new File(batpath);
			if(file.isFile()&&file.exists()){
				Process p = Runtime.getRuntime().exec(batpath);
				BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
				while(reader.readLine()!=null){
					System.out.println(reader.readLine());
				}
				System.out.println("finish");
				//
				//delete
				file.delete();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
