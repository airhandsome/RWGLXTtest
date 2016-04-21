package com.dao.def;

import java.util.ArrayList;
import java.util.List;

import com.dao.DsDAO;
import com.domain.Ds;
import com.domain.def.DSforCopy;

public class GetDS {
/*	public List<DSforCopy> queryDSByPre(Integer DA_id) {
		List<DSforCopy> list = new ArrayList<DSforCopy>();

		DsDAO dsdao = new DsDAO();
		List<Ds> dslist = dsdao.findByDaId(DA_id);
		if (dslist != null && dslist.size() > 0) {
			for (Ds dsO : dslist) {
				DSforCopy ds = new DSforCopy();
				ds.setPreDA(DA_id);

				ds.setDS_id((Integer) dsO.getDsId());
				if (dsO.getDstype() != null) {
					ds.setDS_type((Integer) dsO.getDstype().getDsTypeId());
				} else {
					ds.setDS_type(null);
				}
				ds.setName((String) dsO.getName());
				ds.setDescription((String) dsO.getDescription());
				if (dsO.getDaByTrueExit() != null) {
					ds.setTrueExit((Integer) dsO.getDaByTrueExit().getDaId());
				} else {
					ds.setTrueExit(null);
				}
				if (dsO.getDaByFalseExit() != null) {
					ds.setFalseExit((Integer) dsO.getDaByFalseExit().getDaId());
				} else {
					ds.setFalseExit(null);
				}
				ds.setCode((String) dsO.getCode());
				ds.setIfdef((Integer) dsO.getIfdef());
				ds.setDSbelong((Integer) dsO.getDsbelong());
				ds.setLogic((String) dsO.getLogic());
				list.add(ds);

			}
			return list;
		}
		return null;
	}
*/
	public List<DSforCopy> queryDSByCA(Integer CA_id){
		List<DSforCopy> list = new ArrayList<DSforCopy>();

		DsDAO dsdao = new DsDAO();
		List<Ds> dslist = dsdao.getDSbyCa(CA_id);
		if (dslist != null && dslist.size() > 0) {
			for (Ds dsO : dslist) {
				DSforCopy ds = new DSforCopy();
				if(dsO.getDaByPreDa()!=null)
					ds.setPreDA(dsO.getDaByPreDa().getDaId());
				else
					ds.setPreDA(null);
				ds.setDS_id((Integer) dsO.getDsId());
				if (dsO.getDstype() != null) {
					ds.setDS_type((Integer) dsO.getDstype().getDsTypeId());
				} else {
					ds.setDS_type(null);
				}
				ds.setName((String) dsO.getName());
				ds.setDescription((String) dsO.getDescription());
				if (dsO.getDaByTrueExit() != null) {
					ds.setTrueExit((Integer) dsO.getDaByTrueExit().getDaId());
				} else {
					ds.setTrueExit(null);
				}
				if (dsO.getDaByFalseExit() != null) {
					ds.setFalseExit((Integer) dsO.getDaByFalseExit().getDaId());
				} else {
					ds.setFalseExit(null);
				}
				ds.setCode((String) dsO.getCode());
				ds.setIfdef((Integer) dsO.getIfdef());
				ds.setDSbelong((Integer) dsO.getDsbelong());
				ds.setLogic((String) dsO.getLogic());
				ds.setCAbelong(CA_id);
				list.add(ds);
			}
			return list;
		}
		return null;
	}
	public DSforCopy queryDSById(Integer ds_id) {
		DSforCopy ds = new DSforCopy();
		DsDAO dsdao = new DsDAO();
		Ds dsO = new Ds();
		dsO = dsdao.findById(ds_id);
		if (dsO != null) {
			if (dsO.getDaByPreDa() == null) {
				ds.setPreDA(null);
			} else {
				ds.setPreDA(dsO.getDaByPreDa().getDaId());
			}
			ds.setDS_id((Integer) dsO.getDsId());
			if (dsO.getDstype() != null) {
				ds.setDS_type((Integer) dsO.getDstype().getDsTypeId());
			} else {
				ds.setDS_type(null);
			}
			ds.setName((String) dsO.getName());
			ds.setDescription((String) dsO.getDescription());
			if (dsO.getDaByTrueExit() != null) {
				ds.setTrueExit((Integer) dsO.getDaByTrueExit().getDaId());
			} else {
				ds.setTrueExit(null);
			}
			if (dsO.getDaByFalseExit() != null) {
				ds.setFalseExit((Integer) dsO.getDaByFalseExit().getDaId());
			} else {
				ds.setFalseExit(null);
			}
			if (dsO.getCa() != null) {
				ds.setCAbelong(dsO.getCa().getCaId());
			} else {
				ds.setCAbelong(null);
			}
			ds.setCode((String) dsO.getCode());
			ds.setIfdef((Integer) dsO.getIfdef());
			ds.setDSbelong((Integer) dsO.getDsbelong());
			ds.setLogic((String) dsO.getLogic());
		}
		return ds;
	}
}
