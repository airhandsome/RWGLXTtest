package com.dao.def;

import java.util.ArrayList;
import java.util.List;

import com.dao.SequenceDAO;
import com.domain.Sequence;
import com.domain.def.SequenceforCopy;

public class SelectSeq {
	public SequenceforCopy querySeqByPreCA(Integer CA_id) {
		SequenceDAO seqdao = new SequenceDAO();
		Sequence seqO = new Sequence();
		if(seqdao.queryByPre("CA", CA_id).size()>0){
			seqO = seqdao.queryByPre("CA", CA_id).get(0);
		}
		
		SequenceforCopy seq = new SequenceforCopy();

		if (seqO != null) {
			seq.setPre_type("CA");
			seq.setPre((Integer) seqO.getPre());
			seq.setPost_type((String) seqO.getPostType());
			seq.setPost((Integer) seqO.getPost());
		}
		return seq;
	}

	public List<SequenceforCopy> querySeqByPreDA(Integer DA_id) {
		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> seqO = new ArrayList<Sequence>();
		seqO = seqdao.queryByPre("DA", DA_id);
		List<SequenceforCopy> seq = new ArrayList<SequenceforCopy>();

		if (seqO != null&&seqO.size()>0) {
			for(Sequence tempseq : seqO){
				SequenceforCopy seqf = new SequenceforCopy();
				seqf.setPre_type("DA");
				seqf.setPre((Integer) tempseq.getPre());
				seqf.setPost_type((String) tempseq.getPostType());
				seqf.setPost((Integer) tempseq.getPost());
				seq.add(seqf);
			}
		}
		return seq;
	}
	
	public List<SequenceforCopy> querySeqByPostDS(Integer DS_id){
		SequenceDAO seqdao = new SequenceDAO();
		List<Sequence> seqO = new ArrayList<Sequence>();
		seqO = seqdao.queryByPost("DS", DS_id);
		List<SequenceforCopy> seq = new ArrayList<SequenceforCopy>();

		if (seqO != null&&seqO.size()>0) {
			for(Sequence tempseq : seqO){
				SequenceforCopy seqf = new SequenceforCopy();
				seqf.setPre_type((String)tempseq.getPreType());
				seqf.setPre((Integer) tempseq.getPre());
				seqf.setPost_type("DS");
				seqf.setPost((Integer) tempseq.getPost());
				seq.add(seqf);
			}
		}
		return seq;
	}

}
