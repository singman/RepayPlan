package com.generalsoft.repayplan;
import java.math.BigDecimal;
import java.util.ArrayList;
import org.loushang.next.data.DataSet;
import org.loushang.next.data.Record;
import org.loushang.next.web.cmd.BaseAjaxCommand;

//等额本息计算方式
public class AverageInte extends BaseAjaxCommand implements IRebackCommand {
    public DataSet calc(LoanTrailDao para) {

        DataSet result = new DataSet();
        // 贷款本金
        BigDecimal fixloanamt =new BigDecimal(para.getLoanTotal());// 固定贷款金额
        // 贷款期限
        BigDecimal prdTerm = new BigDecimal(para.getLoanMonths());
        // 贷款利率
        BigDecimal actualrate = new BigDecimal(para.getMonthlyInte()).divide(new BigDecimal(1200), 6, BigDecimal.ROUND_HALF_UP);
        // 每月应还利息
        BigDecimal monthlyInterest;
        // 每月应还本金
        BigDecimal monthlyPrincipal;
        //每月月供额
        BigDecimal monthlyReback;
        // iPrdTerm为prdTerm的int数值
        int iPrdTerm = prdTerm.intValue();
        // 已还月供
        BigDecimal haveReback = new BigDecimal(0);
        // 前n-1个月本金已还额度
        BigDecimal havePrin = new BigDecimal(0);
        /*剩余本金数目*/
        BigDecimal leftPrincipal =  fixloanamt;
        

               
    if(!fixloanamt.equals(new BigDecimal(0)) && !prdTerm.equals(new  BigDecimal(0)))
        {
        	int isZero = actualrate.compareTo(BigDecimal.ZERO);
        	if(isZero == 1)//利率大于0
        	{
        	
		        BigDecimal temp1 = new BigDecimal(Math.pow(
		                actualrate.add(new BigDecimal(1)).doubleValue(),
		                prdTerm.doubleValue()));
		        BigDecimal temp2;
		        
		        // 每月月供额=〔贷款本金×月利率×(1＋月利率)＾还款月数〕÷〔(1＋月利率)＾还款月数-1〕
		         monthlyReback = fixloanamt
		                .multiply(temp1)
		                .multiply(actualrate)
		                .divide(temp1.subtract(new BigDecimal(1)), 6,
		                        BigDecimal.ROUND_HALF_UP);
		       
		       
		        for (int i = 1; i <= iPrdTerm; i++) {// 分期还款的计划，期数为数据表中的期限(Int类型)
		            temp2 = new BigDecimal(Math.pow(actualrate.add(new BigDecimal(1))
		                    .doubleValue(), i - 1));// i表示还款的月序号，1时为还款期的第一个月
		            // 每月应还利息=贷款本金×月利率×〔(1+月利率)^还款月数-(1+月利率)^(还款月序号-1)〕÷〔(1+月利率)^还款月数-1〕
		            monthlyInterest = fixloanamt
		                    .multiply(actualrate)
		                    .multiply(temp1.subtract(temp2))
		                    .divide(temp1.subtract(new BigDecimal(1)), 6,
		                            BigDecimal.ROUND_HALF_UP);
		            // 每月应还本金=贷款本金×月利率×(1+月利率)^(还款月序号-1)÷〔(1+月利率)^还款月数-1〕
		            monthlyPrincipal = fixloanamt
		                    .multiply(actualrate)
		                    .multiply(temp2)
		                    .divide(temp1.subtract(new BigDecimal(1)), 6,
		                            BigDecimal.ROUND_HALF_UP);
		            ArrayList<Object> resTemp = new ArrayList<Object>();
		            if (i == iPrdTerm) {//最后一期
		                resTemp.add(monthlyReback.multiply(prdTerm)
		                        .subtract(haveReback)
		                        .setScale(2, BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyInterest.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(fixloanamt.subtract(havePrin).setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(new BigDecimal(0));
		                resTemp.add("1");//最后一期标志
		
		            } else {
		                haveReback = haveReback.add(monthlyReback).setScale(2,
		                        BigDecimal.ROUND_HALF_UP);
		                havePrin = havePrin.add(monthlyPrincipal.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyReback.setScale(2, BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyInterest.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyPrincipal.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(leftPrincipal.subtract(havePrin).setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add("0");//非最后一期标志
		            }
		            Record tempRecord = new Record();
		            tempRecord.set("num", i);
		            tempRecord.set("monthlyReback", resTemp.get(0));
		            tempRecord.set("monthlyInterest", resTemp.get(1));
		            tempRecord.set("monthlyPrincipal", resTemp.get(2));
		            tempRecord.set("leftPrincipal", resTemp.get(3));
		            tempRecord.set("isLastTerm", resTemp.get(4));
		            result.add(tempRecord);
		        }
        	}
        	else if(isZero == 0)//利率为0，按零利率计算
        	{
        		 for (int i = 1; i <= iPrdTerm; i++) {// 分期还款的计划，期数为数据表中的期限(Int类型)
 		          
 		            monthlyInterest = new BigDecimal(0).setScale(2);
 		            // 每月应还本金=贷款本金/期数
 		            monthlyPrincipal = fixloanamt.divide(prdTerm, 2, BigDecimal.ROUND_HALF_UP);
 		            monthlyReback = monthlyPrincipal;
  		            ArrayList<Object> resTemp = new ArrayList<Object>();
		            Record tempRecord = new Record();
		            
		            
		            
		            if (i == iPrdTerm) {//最后一期
		                resTemp.add(monthlyReback.multiply(prdTerm)
		                        .subtract(haveReback)
		                        .setScale(2, BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyInterest.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(fixloanamt.subtract(havePrin).setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(new BigDecimal(0));
		                resTemp.add("1");//最后一期标志
		
		            } else {
		                haveReback = haveReback.add(monthlyReback).setScale(2,
		                        BigDecimal.ROUND_HALF_UP);
		                havePrin = havePrin.add(monthlyPrincipal.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyReback.setScale(2, BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyInterest.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(monthlyPrincipal.setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add(leftPrincipal.subtract(havePrin).setScale(2,
		                        BigDecimal.ROUND_HALF_UP));
		                resTemp.add("0");//非最后一期标志
		            }
 			            tempRecord.set("num", i);
 			            tempRecord.set("monthlyReback", resTemp.get(0));
 			            tempRecord.set("monthlyInterest", resTemp.get(1));
 			            tempRecord.set("monthlyPrincipal", resTemp.get(2));
 			            tempRecord.set("leftPrincipal", resTemp.get(3));
 			            tempRecord.set("isLastTerm", resTemp.get(4));
 			            result.add(tempRecord);
 		            }
        		
        	}
        	else//利率小于0,输出错误信息
        	{
        		System.out.println("利率小于0");
        	}
	        }
   else //贷款总额或贷款期限为零
        {
	   //什么也不做
	   System.out.println("贷款金额:"+fixloanamt+"剩余期次："+prdTerm);
        }
        return result;
    }
}