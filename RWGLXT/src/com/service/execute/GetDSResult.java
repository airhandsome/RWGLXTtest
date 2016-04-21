package com.service.execute;

import java.sql.Timestamp;
import java.util.List;

import com.dao.AtrributeDAO;
import com.domain.Atrribute;
import com.domain.Da;
import com.domain.Dsatr;

public class GetDSResult {

	private AtrributeDAO attrdao = new AtrributeDAO();

	public boolean queryResult(List<Dsatr> dsatr, String logic) {
		boolean result = false;
		if (logic != null&&logic.contains("#")) {
			String[] tp = logic.split("#");
			int length = tp.length - 1;
			if (dsatr.size() > 1) {
				String[] relation = new String[length - 1];
				for (int i = 0; i < relation.length; i++) {
					relation[i] = tp[i].split(",")[3];
				}
				result = preResult(dsatr.get(0));

				for (int i = 0; i < dsatr.size(); i++) {
					if (dsatr.size() > 1 && (i + 1) < dsatr.size()) {
						result = between(result, relation[i],
								preResult(dsatr.get(i + 1)));
					}
				}
			} else if (dsatr.size() == 1) {
				result = preResult(dsatr.get(0));
			}
		}else{
			if(dsatr.size()>1){
				result = preResult(dsatr.get(0));
				for(int i=0;i<dsatr.size();i++){
					if((i+1)<dsatr.size()){
						result = between(result,"1",preResult(dsatr.get(i+1)));
					}
				}
			} else if(dsatr.size()==1){
				result = preResult(dsatr.get(0));
			}
		}
		return result;
	}

	public boolean between(boolean r1, String rela, boolean r2) {
		boolean result = false;
		if (rela.equals("1")) {
			result = r1 && r2;
		} else {
			result = r1 || r2;
		}
		return result;
	}

	public boolean preResult(Dsatr dsatr) {
		boolean result = false;
		Atrribute atr = dsatr.getId().getAtrribute();
		Float atrvalue = dsatr.getValue();
		switch (dsatr.getSymbol()) {
		case 1:
			if (atr.getValue().floatValue() == atrvalue.floatValue()) {
				result = true;
			}
			break;
		case 2:
			if (atr.getValue().floatValue() > atrvalue.floatValue()) {
				result = true;
			}
			break;
		case 3:
			if (atr.getValue().floatValue() < atrvalue.floatValue()) {
				result = true;
			}
			break;
		case 4:
			if (atr.getValue().floatValue() >= atrvalue.floatValue()) {
				result = true;
			}
			break;
		case 5:
			if (atr.getValue().floatValue() <= atrvalue.floatValue()) {
				result = true;
			}
			break;
		}
		return result;
	}

	public boolean getResult(String logic) {
		boolean result = false;
		if (calc(logic) == 0)
			result = false;
		else
			result = true;

		return result;
	}

	private double findValue(String str) {
		// [aaa#14]
		str = str.substring(1, str.length() - 1);
		String[] s = str.split("#");
		try {
			Integer attrid = Integer.getInteger(s[1]);
			double value = attrdao.findById(attrid).getValue().doubleValue();
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	private double calc(String str) {
		int left = 0, right = str.length() - 1;
		if (right > left && str.charAt(left) == 'i'
				&& str.charAt(left + 1) == 'f') {
			int sum = 0;
			for (int i = left + 2; i <= right; i++) {
				if (str.charAt(i) == '(')
					sum++;
				if (str.charAt(i) == ')')
					sum--;
				if (sum == 0) {
					if (calc(str.substring(left + 2, i + 1)) != 0)
						return calc(str.substring(i + 5, right + 1));
					else
						return 0;
				}
			}
		}

		int sum1 = 0, sum2 = 0;
		for (int i = right; i >= left; i--) {
			if (str.charAt(i) == '[' || str.charAt(i) == ']')
				sum1 ^= 1;
			if (str.charAt(i) == '(')
				sum2++;
			if (str.charAt(i) == ')')
				sum2--;
			if (sum1 == 0 && sum2 == 0) {
				if (str.charAt(i) == 'o' && i < right
						&& str.charAt(i + 1) == 'r') {
					double ans = 0;
					if (calc(str.substring(left, i)) != 0
							|| calc(str.substring(i + 2, right + 1)) != 0)
						ans = 1;
					return ans;
				}
				if (i + 2 <= right && str.charAt(i) == 'a'
						&& str.charAt(i + 1) == 'n' && str.charAt(i + 2) == 'd') {
					double ans = 0;
					if (calc(str.substring(left, i)) != 0
							&& calc(str.substring(i + 3, right + 1)) != 0)
						ans = 1;
					return ans;
				}
			}
		}

		if (str.charAt(left) == '(' && str.charAt(right) == ')')
			return calc(str.substring(left + 1, right));
		sum1 = 0;
		for (int i = left; i <= right; i++) {
			if (str.charAt(i) == '[' || str.charAt(i) == ']')
				sum1 ^= 1;
			if (sum1 == 0) {
				double ans = 0;
				if (i < right && str.charAt(i) == '>'
						&& str.charAt(i + 1) == '=') {
					if (calc(str.substring(left, i)) >= calc(str.substring(
							i + 2, right + 1)))
						ans = 1;
					return ans;
				}
				if (i < right && str.charAt(i) == '<'
						&& str.charAt(i + 1) == '=') {
					if (calc(str.substring(left, i)) <= calc(str.substring(
							i + 2, right + 1)))
						ans = 1;
					return ans;
				}
				if (str.charAt(i) == '=') {
					if (calc(str.substring(left, i)) == calc(str.substring(
							i + 1, right + 1)))
						ans = 1;
					return ans;
				}
				if (str.charAt(i) == '>') {
					if (calc(str.substring(left, i)) > calc(str.substring(
							i + 1, right + 1)))
						ans = 1;
					return ans;
				}
				if (str.charAt(i) == '<') {
					if (calc(str.substring(left, i)) < calc(str.substring(
							i + 1, right + 1)))
						ans = 1;
					return ans;
				}
			}
		}
		if (str.charAt(left) == '[' && str.charAt(right) == ']') {
			return findValue(str);
		}

		return Double.parseDouble(str);
	}
}
