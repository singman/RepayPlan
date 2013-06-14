package com.generalsoft.repayplan;

import java.math.BigDecimal;

import org.loushang.next.data.DataSet;
import org.loushang.next.data.Record;

public class LoanCalculator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AverageInte ai = new AverageInte();
		LoanTrailDao lt = new LoanTrailDao();
		lt.setLoanTotal(50812.44);
		lt.setLoanMonths(15);
		lt.setMonthlyInte(0);
		DataSet ds = ai.calc(lt);
		
		int term = lt.getLoanMonths();
		for (int j=0; j< term;j++)
		{
			Record record = ds.getRecord(j);
			
			int num = (Integer)record.get("num");
			BigDecimal monthlyReback = (BigDecimal)record.get("monthlyReback");
			BigDecimal monthlyInterest = (BigDecimal)record.get("monthlyInterest");
			BigDecimal monthlyPrincipal = (BigDecimal)record.get("monthlyPrincipal");
			BigDecimal leftPrincipal = (BigDecimal)record.get("leftPrincipal");
			
			System.out.println(" 期次："+num+" 月供："+monthlyReback+" 月利息："+monthlyInterest+"月本金："+monthlyPrincipal+" 剩余本金："+ leftPrincipal);
			
		}
		
	}

}
