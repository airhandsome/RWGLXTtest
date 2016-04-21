package com.service.execute;

import java.util.List;
import java.util.Stack;

import com.dao.DaDAO;
import com.dao.DsDAO;
import com.dao.SequenceDAO;
import com.domain.Da;
import com.domain.Ds;
import com.domain.Sequence;

public class ExecuteUndoDaService {
	DaDAO dadao = new DaDAO();
	SequenceDAO sequencedao = new SequenceDAO();
	DsDAO dsdao = new DsDAO();
	Stack<Ds> stack = new Stack<Ds>();

	public void executeUndoDa(Da da) {
		dadao.changeState(da, "Undo");
		List<Sequence> list = sequencedao.queryByPre("DA", da.getDaId());
		for (Sequence sequence : list) {
			if (sequence != null && sequence.getPostType().equals("DA")) {
				executeUndoDa(dadao.findById(sequence.getPost()));
			} else if (sequence != null) {
				Ds ds = dsdao.findById(sequence.getPost());
				stack.push(ds);
				executeUndoDa(ds.getDaByTrueExit());
				executeUndoDa(stack.pop().getDaByFalseExit());
			}
		}
	}
}
