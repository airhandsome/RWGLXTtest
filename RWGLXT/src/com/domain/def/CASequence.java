package com.domain.def;

import java.util.ArrayList;
import java.util.List;

import com.domain.Ca;

public class CASequence {
	public Ca ca = null;
	public List<DASequence> dalist = null;
	public List<Ca> preca = new ArrayList<Ca>();
	
	public List<DASequence> getDalist() {
		return dalist;
	}
	public void setDalist(List<DASequence> dalist) {
		this.dalist = dalist;
	}
	public Ca getCa() {
		return ca;
	}
	public void setCa(Ca ca) {
		this.ca = ca;
	}
	public List<Ca> getPreca() {
		return preca;
	}
	public void setPreca(List<Ca> preca) {
		this.preca = preca;
	}

}
