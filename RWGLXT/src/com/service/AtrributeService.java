package com.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

import com.dao.AtrributeDAO;
import com.dao.SystemsDAO;
import com.domain.Atrribute;
import com.domain.Da;
import com.domain.Systems;

public class AtrributeService {
	
	private AtrributeDAO atrdao = new AtrributeDAO();
	
	public List<Map> convert(List<Atrribute> list){
		List<Map> result = new ArrayList<Map> ();
		Iterator<Atrribute> iter = list.iterator(); 
		while(iter.hasNext()){
			Map<String, Object> map = new HashMap<String, Object>();
			Atrribute atr = new Atrribute();
			atr = iter.next();
			map.put("id",atr.getAtrId());
			map.put("systemId",atr.getSystems().getSystemId());
			map.put("systemName",atr.getSystems().getName());
			map.put("name",atr.getName());
			map.put("value",atr.getValue());
			map.put("tag", atr.getTag());
			map.put("change", atr.getChanges());
			result.add(map);
		}
		return result;
	}
public List<Map> getAtrBySearch(String str){
		
		List <Atrribute> u = new ArrayList<Atrribute>();
		u = atrdao.findBySearch(str);
		System.out.println("atrvalue:" + u);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
		}

	public List<Map> getAtr(){
		List<Atrribute> u = atrdao.findAll();
		List<Map> result = new ArrayList<Map>();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getAtrBySystem(Integer systemId){
		List <Atrribute> u = atrdao.getAtrBySystem(systemId);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public List<Map> getAtrbyId(Integer atrId){
		Atrribute atr = new Atrribute();
		atr = atrdao.findById(atrId);
		System.out.println("atrvalue:"+atr.getValue());
		List <Atrribute> u = new ArrayList<Atrribute>();
		u.add(atr);
		List<Map> result = new ArrayList<Map> ();
		result = this.convert(u);
		return result;
	}
	
	public Integer insert(Integer systemId, String name, String tag){
		Integer atrId = null;
		atrId = atrdao.insertAtr(systemId, name, tag).getAtrId();
		return atrId;	
	}
	
	public void delete(Integer atrId){
		atrdao.delete(atrId);	
	}
	
	public void update(Integer atrId, String name, String tag, Integer sysId){
		Systems sys = new SystemsDAO().findById(sysId);
		atrdao.update(atrId, name, tag, sys);
	}
	
	public Integer getAtrByTag(String tag){
		List<Atrribute> list = atrdao.getAtrByTag(tag);
		if(list!=null&&list.size()>0)
			return list.get(0).getAtrId();
		else
			return null;
	}
	
	public void uploadExc(String path,String filename){
		try{
			File file = new File(path+"\\"+filename);
			FileInputStream fis= new FileInputStream(file);
			StringBuilder sb = new StringBuilder();
			jxl.Workbook rwb = Workbook.getWorkbook(fis);
			Sheet[] sheet = rwb.getSheets();
			SystemsDAO systemdao = new SystemsDAO();
			for(int i=0;i<sheet.length;i++){
				Sheet rs = rwb.getSheet(i);
				for(int j=1;j<rs.getRows();j++){
					Cell[] cells = rs.getRow(j);
					if(cells.length>=3){
						String systemname = cells[0].getContents();
						System.out.println(systemname);
						List list = systemdao.queryByName(systemname);
						System.out.println("list:"+list.size());
						if(list!=null&&list.size()>0){
							Systems s = (Systems)list.get(0);
							String name = cells[1].getContents();
							String tag = cells[2].getContents();
							atrdao.insertAtr(s.getSystemId(), name, tag);
						}			
					}
				}
			}
			fis.close();
			file.delete();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
