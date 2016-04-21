package com.service;

import java.util.ArrayList;
import java.util.List;

import com.dao.DsatrDAO;
import com.domain.Daatr;
import com.domain.Dsatr;

public class DsAtrService {
	DsatrDAO dsatrdao = new DsatrDAO();

	public void insertAnalyse(String[] attribute, Integer dsId) {
		for (int i = 0; i < attribute.length; i++) {
			String[] a = attribute[i].split(",");
			dsatrdao.insert(dsId, Integer.parseInt(a[0]),
					Integer.parseInt(a[1]), Float.parseFloat(a[2]));
		}
	}

	public void updateAnalyse(String[] attribute, Integer dsId) {
		dsatrdao.deleteByDS(dsId);
		for (int i = 0; i < attribute.length; i++) {
			String[] a = attribute[i].split(",");
			dsatrdao.insert(dsId, Integer.parseInt(a[0]),
					Integer.parseInt(a[1]), Float.parseFloat(a[2]));
		}
	}

	public String[] getDSAtr(Integer dsId, String logic) {
		String[] tmp = logic.split("#");
		if (logic!=null&&tmp.length > 1) {
			String[] attribute = new String[tmp.length - 1];
			if (tmp.length > 2) {
				for (int i = 0; i < (tmp.length - 1); i++) {
					String attrS = tmp[i];
					String[] atrsp = attrS.split(",");
					Dsatr att = dsatrdao.getDsatrByAtr(Integer
							.parseInt(atrsp[0]));
					String s = att.getId().getAtrribute().getAtrId().toString()
							+ "," + att.getId().getAtrribute().getName() + ","
							+ atrsp[1] + "," + atrsp[2] + "," + atrsp[3];
					attribute[i] = s;
				}
			} else if (tmp.length == 2) {
				String attrS = tmp[0];
				String[] atrsp = attrS.split(",");
				Dsatr att = dsatrdao.getDsatrByAtr(Integer.parseInt(atrsp[0]));
				String s = atrsp[0] + ","
						+ att.getId().getAtrribute().getName() + "," + atrsp[1]
						+ "," + atrsp[2] + "," + atrsp[3];
				attribute[0] = s;
			}
			return attribute;
		}else if(logic==null){
			return null;
		}else
			return null;
	}
}
