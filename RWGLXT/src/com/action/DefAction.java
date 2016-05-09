package com.action;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.domain.def.ISPforCopy;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.service.def.Delete;
import com.service.def.Insert;
import com.service.def.SequenceAnalysis;

public class DefAction extends ActionSupport {

	private Integer ispId;
	private Integer caId;
	private Integer daId;
	private Timestamp planStart;
	private Timestamp planEnd;
	private String triggertype;
	private Integer[] preDA;

	private Integer dsId;
	private Integer daByTrueExitId;
	private Integer daByFalseExitId;
	private Integer daByPreDaId;

	private Timestamp planStarttime;
	private Timestamp planEndtime;
	private Integer preCA;

	private Integer startattributeId;
	private Integer startsymbol;
	private Float startvalue;

	private Integer ifAuto;
	
	public Integer getIfAuto() {
		return ifAuto;
	}

	public void setIfAuto(Integer ifAuto) {
		this.ifAuto = ifAuto;
	}

	public void setPreDA(Integer[] preDA) {
		this.preDA = preDA;
	}

	public Integer getStartattributeId() {
		return startattributeId;
	}

	public void setStartattributeId(Integer startattributeId) {
		this.startattributeId = startattributeId;
	}

	public Integer getStartsymbol() {
		return startsymbol;
	}

	public void setStartsymbol(Integer startsymbol) {
		this.startsymbol = startsymbol;
	}

	public Float getStartvalue() {
		return startvalue;
	}

	public void setStartvalue(Float startvalue) {
		this.startvalue = startvalue;
	}

	public Timestamp getPlanStarttime() {
		return planStarttime;
	}

	public void setPlanStarttime(Timestamp planStarttime) {
		this.planStarttime = planStarttime;
	}

	public Timestamp getPlanEndtime() {
		return planEndtime;
	}

	public void setPlanEndtime(Timestamp planEndtime) {
		this.planEndtime = planEndtime;
	}

	public Integer getPreCA() {
		return preCA;
	}

	public void setPreCA(Integer preCA) {
		this.preCA = preCA;
	}

	public Integer getDsId() {
		return dsId;
	}

	public void setDsId(Integer dsId) {
		this.dsId = dsId;
	}

	public Integer getDaByTrueExitId() {
		return daByTrueExitId;
	}

	public void setDaByTrueExitId(Integer daByTrueExitId) {
		this.daByTrueExitId = daByTrueExitId;
	}

	public Integer getDaByFalseExitId() {
		return daByFalseExitId;
	}

	public void setDaByFalseExitId(Integer daByFalseExitId) {
		this.daByFalseExitId = daByFalseExitId;
	}

	public Integer getDaByPreDaId() {
		return daByPreDaId;
	}

	public void setDaByPreDaId(Integer daByPreDaId) {
		this.daByPreDaId = daByPreDaId;
	}

	public Timestamp getPlanStart() {
		return planStart;
	}

	public void setPlanStart(Timestamp planStart) {
		this.planStart = planStart;
	}

	public Timestamp getPlanEnd() {
		return planEnd;
	}

	public void setPlanEnd(Timestamp planEnd) {
		this.planEnd = planEnd;
	}

	public String getTriggertype() {
		return triggertype;
	}

	public void setTriggertype(String triggertype) {
		this.triggertype = triggertype;
	}

	public Integer getIspId() {
		return ispId;
	}

	public void setIspId(Integer ispId) {
		this.ispId = ispId;
	}

	public Integer getCaId() {
		return caId;
	}

	public void setCaId(Integer caId) {
		this.caId = caId;
	}

	public Integer getDaId() {
		return daId;
	}

	public void setDaId(Integer daId) {
		this.daId = daId;
	}

	public String IspDef() {
		SequenceAnalysis sa = new SequenceAnalysis();
		ISPforCopy isp = sa.copyISP(ispId, new Integer(ActionContext.getContext().getSession().get("userid").toString()));
		ActionContext.getContext().getSession().put("ispId", (Integer)isp.getISP_id());
		ActionContext.getContext().getSession().put("ispName",(String)isp.getName());
		return "Success";
	}

	public String CaDef() throws IOException {
		Insert insert = new Insert();
		Integer id = null;
		try {
			id = insert.createCA(caId, triggertype, planStarttime, planEndtime,
					ispId, "CheckPending");
			ServletActionContext.getResponse().getWriter().print(id);
			System.out.println("CADEF finish");
			return null;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}

	public String DaDef() throws IOException {
		Insert insert = new Insert();
		Integer id = null;
		try {
			List<Integer> list = new ArrayList<Integer>();
			if(preDA!=null&&preDA.length>0)
				for(Integer preid:preDA){
					list.add(preid);
				}
//			System.out.println("daid ="+daId);
//			System.out.println("triggertype ="+triggertype);
//			System.out.println("ifauto0 ="+ifAuto);
			id = insert.createDA(daId, planStart, planEnd, triggertype, list,caId, startattributeId, startsymbol, startvalue,ifAuto);
			ServletActionContext.getResponse().getWriter().print(id);
			return null;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}

	public String DsDef() {
		Insert insert = new Insert();
		Integer id = null;
		try {
//			id = insert.CreateDS(dsId, daByPreDaId, daByTrueExitId,
//					daByFalseExitId, caId);
			id = insert.CreateDS(dsId, null, null,
					null, null);
			ServletActionContext.getResponse().getWriter().print(id);
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return "Fail";
		}
	}

	public String deleteIspDef() {
		Delete delete = new Delete();
		try {
			delete.deleteISP(ispId);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}

	public String deleteCaDef() {
		Delete delete = new Delete();
		try {
			delete.deleteCA(caId);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}

	public String deleteDaDef() {
		Delete delete = new Delete();
		try {
			delete.deleteDA(daId);
			return "Success";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "Fail";
		}
	}
}
