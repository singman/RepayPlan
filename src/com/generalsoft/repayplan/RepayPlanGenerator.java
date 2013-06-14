package com.generalsoft.repayplan;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.loushang.next.data.DataSet;
import org.loushang.next.data.Record;
import com.generalsoft.util.DBUtil;
import com.generalsoft.util.DateUtil;
import com.generalsoft.util.GetSysConf;
import com.generalsoft.util.TsBaseJdbcDao;
import com.generalsoft.util.TsDaoFactory;
import com.generalsoft.util.TsException;
import com.generalsoft.util.UUIDGenerator;
public class RepayPlanGenerator  extends TsBaseJdbcDao{
public RepayPlanGenerator() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}
/**
 * 生成计划还款计划
 * @param conn
 */
public void  InitExpectedRepayPlan(Connection conn)
	{

	PreparedStatement pst = null;
	String querySQL = "select DUE_BILL_ID as  billID,CONT_ID as contID, TOTAMT as loanTotal,TERM as loanMonths,INTRATE as monthlyInte " +
			"from loan_due_bill ldb, lnsacctinfo@to_bztest lns,prd_marketproduct_info pmi   " +
			" where ldb.loan_actno = trim(lns.acctno) and lns.loanstat <> '08'" +
			" and pmi.prdid = ldb.prdcode and pmi.repayplan = 0 ";//只更新未结清的
		try {
		List list = DBUtil.executeQuery(conn, querySQL, LoanTrailDao.class);
		AverageInte av = new AverageInte();
		DataSet ds = new DataSet();
		System.out.println(list.size());
		
		String updateSql2 = "update loan_repay_plan set PLAN_PRCP_AMT = ?,PLAN_INT_AMT = ?,PLAN_SUM_AMT = ?"
				+" where DUE_BILL_ID = ? and LOAN_CONTRACT_ID = ? and PLAN_PERO = ?";
		
		for(int i=0;i<list.size();i++)
		{
			LoanTrailDao tmpDao =  (LoanTrailDao)list.get(i);
			ds = av.calc(tmpDao);//每条记录生成还款计划
			int term = tmpDao.getLoanMonths();//期数
			System.out.println("更新到第  "+i+"  条记录，总数为"+list.size()+"条记录     借据ID为"+tmpDao.getBillID());
			
			List<Object[]> planlist = new ArrayList<Object[]>();
			Object[] values = null;
			
			for (int j=0; j< term;j++)
			{
				Record record = ds.getRecord(j);
				record.set("billID", tmpDao.getBillID());
				record.set("contID", tmpDao.getContID());

				String billID = (String)record.get("billID");
				String contID = (String )record.get("contID");
				int num =  (Integer)record.get("num");//还款期次
				BigDecimal monthlyReback = (BigDecimal)record.get("monthlyReback");
				BigDecimal monthlyInterest = (BigDecimal)record.get("monthlyInterest");
				BigDecimal monthlyPrincipal = (BigDecimal)record.get("monthlyPrincipal");
				BigDecimal leftPrincipal = (BigDecimal)record.get("leftPrincipal");
				
				values = new Object[] { monthlyPrincipal, monthlyInterest,monthlyReback,billID,contID,num };
				planlist.add(values);
				
				/*System.out.println("借据编号："+billID+" 合同ID："+contID+" 期次："+num+" 月供："+monthlyReback+" 月利息："+monthlyInterest+"月本金："+monthlyPrincipal+" 剩余本金："+ leftPrincipal);
				
				String updateSql = "update loan_repay_plan set PLAN_PRCP_AMT = "+monthlyPrincipal +",PLAN_INT_AMT = "+monthlyInterest+",PLAN_SUM_AMT ="+monthlyReback
						+" where DUE_BILL_ID = '"+billID+"' and LOAN_CONTRACT_ID = '"+contID+"' and PLAN_PERO = "+num;
				pst = conn.prepareStatement(updateSql);
				int updateRecordsNum = pst.executeUpdate();
				pst.close();
				if(updateRecordsNum > 0)
				{
					System.out.println("成功更新 "+updateRecordsNum+" 条");
					
				}
				else{
					
					System.out.println("失败 ");
				}*/
			}
			try {
				int[] impactNums= this.batchUpdateExt(updateSql2, planlist);
				System.out.println("成功更新了  借据ID为"+tmpDao.getBillID());
			} catch (TsException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	} catch (SQLException e) {
		e.printStackTrace();
	}

	}
/**
 * 生成半付半贷还款计划
 * @param conn
 */
	public void   generateBFBDRepayPlan(Connection conn)
	{
		List<LoanRepayPlan> planlist = new ArrayList<LoanRepayPlan>();
		String date = GetSysConf.getCurDate("YYYY-MM-DD HH24:MI:SS");
		TsBaseJdbcDao<LoanRepayPlan> planDao = (TsBaseJdbcDao<LoanRepayPlan>)TsDaoFactory.getTsBaseDao(LoanRepayPlan.class);

		String querySQL =
			"select tmp1.due_bill_id  as billID,\n" +
			"       tmp1.grant_date as grantdate,\n" + 
			"       tmp1.cont_id as contID,\n" + 
			"       tmp1.loan_actno,\n" + 
			"       tmp1.totamt as loanTotal,\n" + 
			"       tmp1.term  as loanMonths,\n" + 
			"       tmp1.PRDTYPE2,\n" + 
			"       tmp2.plan_id as  existFlag\n" + 
			"  from (select distinct ldb.due_bill_id,\n" + 
			"                        ldb.grant_date,\n" + 
			"                        ldb.cont_id,\n" + 
			"                        ldb.loan_actno,\n" + 
			"                        ldb.totamt,\n" + 
			"                        ldb.term,\n" + 
			"                        pmi.PRDTYPE2\n" + 
			"          from loan_due_bill          ldb,\n" + 
			"               prd_marketproduct_info pmi,\n" + 
			"               lnsacctinfo@to_bztest  lns\n" + 
			"         where ldb.prdcode = pmi.prdid\n" + 
			"           and ldb.loan_actno = trim(lns.acctno)\n" + 
			"           and lns.loanstat <> '08'\n" + 
			"           and pmi.prdtype2 = '5' --半付半贷\n" + 
			"        ) tmp1\n" + 
			"  left join (select lrp.due_bill_id, lrp.plan_id from loan_repay_plan lrp) tmp2\n" + 
			"    on tmp2.due_bill_id = tmp1.due_bill_id";

		try {
			List list = DBUtil.executeQuery(conn, querySQL, LoanTrailDao.class);
			for(int i=0;i<list.size();i++)
			{
				LoanTrailDao tmpDao =  (LoanTrailDao)list.get(i);
				if(tmpDao.getExistFlag() != null)
				{
					System.out.println("此半付半贷还款计划已经存在! 借据ID："+tmpDao.getBillID());
				}
				else
				{
					 LoanRepayPlan loanRepayPlan = new LoanRepayPlan();
					
					 loanRepayPlan.setDueBillId(tmpDao.getBillID());//借据ID
			         loanRepayPlan.setLoanContractId(tmpDao.getContID());//合同ID
			         loanRepayPlan.setPlanPero(new BigDecimal("1"));//期次,半付半贷只有1期，所以期次都从一开始，而且只有一条
			         loanRepayPlan.setPlanRepayDate(DateUtil.getDateAfterMonths(tmpDao.getGrantdate(),
			                 "yyyyMMdd", "yyyyMMdd", tmpDao.getLoanMonths()));
			         loanRepayPlan.setLatestRepayDate(DateUtil.getDateAfterDays(loanRepayPlan.getPlanRepayDate(),
			                 "yyyyMMdd", "yyyyMMdd",5));//最晚还款日期=计划还款日期+5天宽限期
			         loanRepayPlan.setPlanIntAmt(new BigDecimal("0"));//计划利息
			         loanRepayPlan.setPlanPrcpAmt(new BigDecimal(tmpDao.getLoanTotal()));//计划本金
			         loanRepayPlan.setPlanSumAmt(new BigDecimal(tmpDao.getLoanTotal()));//计划总额
			         planlist.add(loanRepayPlan);
				}
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
			if(planlist != null && !planlist.isEmpty() && planlist.size()>0){
				String planId = null;
				for(LoanRepayPlan plan : planlist){
					//planId = TsMaxValueUtil.nextStringValue("HKJHCODE");
					planId = UUIDGenerator.getUUID();
					plan.setPlanId(planId);
					plan.setGraceDays(new BigDecimal(5));
					plan.setState(1);
					plan.setIslastPero("1");//默认为是最后期次
					plan.setPlanFlag("0");//0 正常借据计划 1 展期计划产生
					plan.setCreateDate(date);
					plan.setLastUpdDate(date);
					plan.setLastUpdOperid("ETL");
					plan.setRcvamt(plan.getPlanSumAmt());
					plan.setRcvcint(new BigDecimal(0));
					plan.setRcvcpint(new BigDecimal(0));
					plan.setRcvint(new BigDecimal(0));
					plan.setRcvsum(plan.getPlanSumAmt());
					plan.setRtnamt(new BigDecimal(0));
					plan.setRtncint(new BigDecimal(0));
					plan.setRtnint(new BigDecimal(0));
					plan.setRtnpint(new BigDecimal(0));
					plan.setRtnsum(new BigDecimal(0));
					plan.setPrncnt(new BigDecimal(0));
					plan.setSubcint(new BigDecimal(0));
					plan.setSubint(new BigDecimal(0));
					plan.setSubpint(new BigDecimal(0));
					//planDao.insert(plan);
				}
				planDao.batchInsert(planlist);
			}else{
				System.out.println("没有半付半贷贷款计划生成,请检查还款计划是否已存在");
			}
		 
	}
	/**
	 * 生成后续还款计划
	 * @param conn
	 */
	public void  FollowUpRepayPlanGenerator(Connection conn)
	{
		List<LoanRepayPlan> planlist = new ArrayList<LoanRepayPlan>();
		String querySQL =
		"select tmp.due_bill_id billID,\n" +
		"       tmp.cont_id contID,\n" + 
		"       tmp.loan_actno,\n" + 
		"       lastrepaydate,\n"+
		"       tmp.totamt - tmp.havepay loanTotal,\n" + 
		"       tmp.currentterm,\n" + 
		"       tmp.term - tmp.currentterm loanMonths,\n" + 
		"       tmp2.intrate monthlyInte\n" + 
		"  from (select distinct ldb.due_bill_id,\n" + 
		"                        ldb.cont_id,\n" + 
		"                        ldb.loan_actno,\n" + 
		"                        max(lrp.plan_repay_date) lastrepaydate,"+		
		"                        ldb.totamt,\n" + 
		"                        sum(lrp.rcvamt) havepay,\n" + 
		"                        ldb.term,\n" + 
		"                        max(lrp.PLAN_PERO) currentterm\n" + 
		"          from loan_due_bill ldb, loan_repay_plan lrp,prd_marketproduct_info pmi\n" + 
		"\n" + 
		"         where ldb.due_bill_id = lrp.due_bill_id\n" + 
		"		  and ldb.prdcode = pmi.prdid\n"+
		"		  and (pmi.prdtype2 = '4'  or pmi.prdtype2 = '0')"+//4为零利率/低利率产品，0为等额本息产品，都可以使用等额本息计算公式
		"\n" + 
		//"          and ldb.due_bill_id = '42012090065200001'\n" + 
		"\n" + 
		"         group by ldb.due_bill_id,\n" + 
		"                  ldb.cont_id,\n" + 
		"                  ldb.loan_actno,\n" + 
		"                  ldb.totamt,\n" + 
		"                  ldb.term) tmp\n" + 
		"  left join (select lns.acctno, lns.intrate,lns.status\n" + //关联账户动态信息表，获取当前的贷款利率
		"               from lnsacctdyninfo@to_bztest lns\n" + 
		"\n" + 
		"              where lns.ACCTSTAT = '01') tmp2\n" + //01代表正常本金
		"    on tmp.loan_actno = trim(tmp2.acctno)\n"+
		"    where tmp2.status = 0 order by due_bill_id";//账户状态为正常的，即未结清的贷款。对于已结清的贷款不必再生成后续还款计划
		
		
		try {
			List list = DBUtil.executeQuery(conn, querySQL, LoanTrailDao.class);
			AverageInte av = new AverageInte();
			DataSet ds = new DataSet();
			String date = GetSysConf.getCurDate("YYYY-MM-DD HH24:MI:SS");
			TsBaseJdbcDao<LoanRepayPlan> planDao = (TsBaseJdbcDao<LoanRepayPlan>)TsDaoFactory.getTsBaseDao(LoanRepayPlan.class);
			System.out.println(list.size());
			
			for(int i=0;i<list.size();i++)
			{
				LoanTrailDao tmpDao =  (LoanTrailDao)list.get(i);
				ds = av.calc(tmpDao);//每条记录生成还款计划
				planlist = getLoanRepayPlanList(ds,tmpDao);
				if(planlist != null && !planlist.isEmpty() && planlist.size()>0)
				{
					String planId = null;
					for(LoanRepayPlan plan : planlist){
						planId = UUIDGenerator.getUUID();
						plan.setPlanId(planId);
						plan.setState(1);
						plan.setPlanFlag("0");//0 正常借据计划 1 展期计划产生
						plan.setCreateDate(date);
						plan.setLastUpdDate(date);
						plan.setLastUpdOperid("ETL");
						//planDao.insert(plan);
					}
					System.out.println("开始插入…… 第"+i+"条借据的还款计划  借据ID："+tmpDao.getBillID());
					planDao.batchInsert(planlist);
				}else{
					System.out.println("没有还款计划需要生成，请检查该借据的还款计划是否已存在！ 借据ID："+tmpDao.getBillID());
				}
				
			}
		}
				 catch (SQLException e) {
						e.printStackTrace();
					}
		
	}
	 public static List<LoanRepayPlan> getLoanRepayPlanList(DataSet ds,LoanTrailDao tmpDao){
	        List<LoanRepayPlan> list = new ArrayList<LoanRepayPlan>();
	        int length = ds.getCount();
	        for (int i = 0;i<length;i++){
	            Record rec = ds.getRecord(i);
	            LoanRepayPlan plan = new LoanRepayPlan(rec,tmpDao);
	            list.add(plan);
	        }
	        return list;
	    }
	 	 
}
