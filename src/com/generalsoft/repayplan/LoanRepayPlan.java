package com.generalsoft.repayplan;

import java.math.*;
import java.util.Map;

import com.generalsoft.util.DateUtil;
import org.loushang.next.dao.*;
import org.loushang.next.data.*;

/**
 * @title:还款计划表
 * @description:
 * @author:卢
 * @since:2013-05-09
 * @version:1.0
*/
 @Table(tableName = "LOAN_REPAY_PLAN" , keyFields = {"planPero","dueBillId","loanContractId"})
public class LoanRepayPlan extends StatefulDatabean {
   //还款计划Id
   	@Rule(value="require")
	@Column(name = "PLAN_ID")
   private String planId;
   //期次
   	@Rule(value="require|number")
	@Column(name = "PLAN_PERO")
   private BigDecimal planPero;
   //借据ID
   	@Rule(value="require")
	@Column(name = "DUE_BILL_ID")
   private String dueBillId;
   //合同ID
	@Column(name = "LOAN_CONTRACT_ID")
   private String loanContractId;
   //计划还款日期
	@Column(name = "PLAN_REPAY_DATE")
   private String planRepayDate;
   //宽限天数
	@Column(name = "GRACE_DAYS")
   private BigDecimal graceDays;
   //最晚应还款日期
	@Column(name = "LATEST_REPAY_DATE")
   private String latestRepayDate;
   //批扣或提前还款日期
	@Column(name = "FACT_REPAY_DATE")
   private String factRepayDate;
   //实际还款记账日期
	@Column(name = "FACT_ACCOUNT_DATE")
   private String factAccountDate;
   //计划还款本金金额
	@Column(name = "PLAN_PRCP_AMT")
   private BigDecimal planPrcpAmt;
   //计划还款利息金额
	@Column(name = "PLAN_INT_AMT")
   private BigDecimal planIntAmt;
   //计划还款合计金额
	@Column(name = "PLAN_SUM_AMT")
   private BigDecimal planSumAmt;
   //是否最后一期
	@Column(name = "ISLAST_PERO")
   private String islastPero;
   //历史逾期标志
	@Column(name = "HIS_OVERDUE_STAT")
   private String hisOverdueStat;
   //最新状态
	@Column(name = "STAT")
   private String stat;
   //扣款指令状态
	@Column(name = "CMD_STAT")
   private String cmdStat;
   //还款标志
	@Column(name = "PREPAY_FLAG")
   private String prepayFlag;
   //提前还款申请单编号
	@Column(name = "PREPAY_APP_NO")
   private String prepayAppNo;
   //计划标志
	@Column(name = "PLAN_FLAG")
   private String planFlag;
   //展期合同编号
	@Column(name = "EXTD_CONTRACT_ID")
   private String extdContractId;
   //应收本金
   	@Rule(value="require")
	@Column(name = "RCVAMT")
   private BigDecimal rcvamt;
   //应收利息
   	@Rule(value="require")
	@Column(name = "RCVINT")
   private BigDecimal rcvint;
   //应收罚息
   	@Rule(value="require")
	@Column(name = "RCVCINT")
   private BigDecimal rcvcint;
   //应收复利
   	@Rule(value="require")
	@Column(name = "RCVCPINT")
   private BigDecimal rcvcpint;
   //减免利息
   	@Rule(value="require")
	@Column(name = "SUBINT")
   private BigDecimal subint;
   //减免罚息
   	@Rule(value="require")
	@Column(name = "SUBCINT")
   private BigDecimal subcint;
   //减免复利
   	@Rule(value="require|number")
	@Column(name = "SUBPINT")
   private BigDecimal subpint;
   //应收合计
   	@Rule(value="number")
	@Column(name = "RCVSUM")
   private BigDecimal rcvsum;
   //已还本金
   	@Rule(value="require|number")
	@Column(name = "RTNAMT")
   private BigDecimal rtnamt;
   //已还利息
   	@Rule(value="require|number")
	@Column(name = "RTNINT")
   private BigDecimal rtnint;
   //已还罚息
   	@Rule(value="require|number")
	@Column(name = "RTNPINT")
   private BigDecimal rtnpint;
   //已还复利
   	@Rule(value="require|number")
	@Column(name = "RTNCINT")
   private BigDecimal rtncint;
   //已还合计
   	@Rule(value="number")
	@Column(name = "RTNSUM")
   private BigDecimal rtnsum;
   //打印次数
   	@Rule(value="require|number")
	@Column(name = "PRNCNT")
   private BigDecimal prncnt;
   //创建时间
	@Column(name = "CREATE_DATE")
   private String createDate;
   //上次更新人
	@Column(name = "LAST_UPD_OPERID")
   private String lastUpdOperid;
   //上次更新时间
	@Column(name = "LAST_UPD_DATE")
   private String lastUpdDate;
	 //逾期还款申请单编号
	@Column(name = "DUE_APP_NO")
    private String dueAppNo;
    public LoanRepayPlan(){}

    /**
     * 还款方式为等额本息 等额本金的营销产品的还款计划的生成
     * @param rec
     * @param dueBill
     */
    public LoanRepayPlan(Record rec, LoanDueBill dueBill){

         this.dueBillId = dueBill.getDueBillId(); //借据id
         this.loanContractId = dueBill.getContId(); //合同id
         this.planPero =  new BigDecimal((Integer)rec.get("num"));//期数
         //计划还款日期
         this.planRepayDate =
                 DateUtil.getDateAfterMonths(dueBill.getGrantDate(),"yyyyMMdd","yyyyMMdd",(Integer)rec.get("num"));
         this.graceDays = dueBill.getGracePeriod(); //宽限期天数
          //最晚还款日期
         this.latestRepayDate =
                 DateUtil.getDateAfterDays(planRepayDate,"yyyyMMdd","yyyyMMdd",dueBill.getGracePeriod().intValue());
         this.planIntAmt = (BigDecimal)rec.get("monthlyInterest");//月利息
         this.planPrcpAmt = (BigDecimal)rec.get("monthlyPrincipal"); //月本金
         this.planSumAmt  = (BigDecimal)rec.get("monthlyReback");//月供总额
         this.rcvamt = new BigDecimal(0);
         this.rcvcint = new BigDecimal(0);
         this.rcvcpint = new BigDecimal(0);
         this.rcvint = new BigDecimal(0);
         this.rcvsum = new BigDecimal(0);
         this.rtnamt = new BigDecimal(0);
         this.rtncint = new BigDecimal(0);
         this.rtnint = new BigDecimal(0);
         this.rtnpint = new BigDecimal(0);
         this.rtnsum = new BigDecimal(0);
         this.prncnt = new BigDecimal(0);
         this.subcint = new BigDecimal(0);
         this.subint = new BigDecimal(0);
         this.subpint = new BigDecimal(0);
    }
    public LoanRepayPlan(Record rec, LoanTrailDao tmpDao){

        this.dueBillId = tmpDao.getBillID();//借据id
        this.loanContractId = tmpDao.getContID(); //合同id
        this.planPero =  new BigDecimal((Integer)rec.get("num")+tmpDao.getCurrentterm());//期数
        //计划还款日期=老系统中最近一次还款日期+（总期数-当前期数）个月
        this.planRepayDate =
                DateUtil.getDateAfterMonths(tmpDao.getLastrepaydate(),"yyyyMMdd","yyyyMMdd",(Integer)rec.get("num"));
        this.graceDays = new BigDecimal(5); //宽限期天数
         //最晚还款日期=计划还款日期+5天（不考虑节假日）
        this.latestRepayDate =
                DateUtil.getDateAfterDays(planRepayDate,"yyyyMMdd","yyyyMMdd",5);
        
        this.islastPero = (String)rec.get("isLastTerm");//是否最后一期标志

        this.planIntAmt = new BigDecimal(0);//计划月利息
        this.planPrcpAmt = new BigDecimal(0);//计划月本金
        this.planSumAmt  = new BigDecimal(0);//计划月供总额
        
        
        this.rcvamt = (BigDecimal)rec.get("monthlyPrincipal"); //应收本金
        this.rcvcint = new BigDecimal(0);
        this.rcvcpint = new BigDecimal(0);
        this.rcvint = (BigDecimal)rec.get("monthlyInterest");//应收利息
        this.rcvsum = (BigDecimal)rec.get("monthlyReback");//应收月供总额
        this.rtnamt = new BigDecimal(0);
        this.rtncint = new BigDecimal(0);
        this.rtnint = new BigDecimal(0);
        this.rtnpint = new BigDecimal(0);
        this.rtnsum = new BigDecimal(0);
        this.prncnt = new BigDecimal(0);
        this.subcint = new BigDecimal(0);
        this.subint = new BigDecimal(0);
        this.subpint = new BigDecimal(0);
   }
   
    /**
     * 营销产品为特殊产品 还款方式自定义的贷款计划的生成
     * @param totalInt
     * @param map
     * @param dueBill
     */
    @SuppressWarnings("rawtypes")
	public LoanRepayPlan(BigDecimal totalInt,BigDecimal loanTotal,Map map, LoanDueBill dueBill){

        this.dueBillId = dueBill.getDueBillId(); //借据id
        this.loanContractId = dueBill.getContId(); //合同id
        this.planPero = (BigDecimal)map.get("TERM");//期数
        //计划还款日期
        this.planRepayDate =
                DateUtil.getDateAfterMonths(dueBill.getGrantDate(),"yyyyMMdd","yyyyMMdd",planPero.intValue());
        this.graceDays = dueBill.getGracePeriod(); //宽限期天数
        //最晚还款日期
        this.latestRepayDate =
                DateUtil.getDateAfterDays(planRepayDate,"yyyyMMdd","yyyyMMdd",dueBill.getGracePeriod().intValue());
        this.planIntAmt = new BigDecimal((String)map.get("INTRATIO"))
                .divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP).multiply(totalInt);//月利息
        this.planPrcpAmt = new BigDecimal((String)map.get("REPLAYRATIO"))
                .divide(new BigDecimal(100),6,BigDecimal.ROUND_HALF_UP).multiply(loanTotal); //月本金
        this.planSumAmt  = planIntAmt.add(planPrcpAmt);//月供总额
        this.rcvamt = new BigDecimal(0);
        this.rcvcint = new BigDecimal(0);
        this.rcvcpint = new BigDecimal(0);
        this.rcvint = new BigDecimal(0);
        this.rcvsum = new BigDecimal(0);
        this.rtnamt = new BigDecimal(0);
        this.rtncint = new BigDecimal(0);
        this.rtnint = new BigDecimal(0);
        this.rtnpint = new BigDecimal(0);
        this.rtnsum = new BigDecimal(0);
        this.prncnt = new BigDecimal(0);
        this.subcint = new BigDecimal(0);
        this.subint = new BigDecimal(0);
        this.subpint = new BigDecimal(0);
    }
   /**
    * getter for 还款计划Id
    * @generated
    */
   public String getPlanId(){
      return this.planId;
   }
   /**
    * setter for 还款计划Id
    * @generated
    */
   public void setPlanId(String planId){
      this.planId = planId;
   }

   /**
    * getter for 期次
    * @generated
    */
   public BigDecimal getPlanPero(){
      return this.planPero;
   }
   /**
    * setter for 期次
    * @generated
    */
   public void setPlanPero(BigDecimal planPero){
      this.planPero = planPero;
   }

   /**
    * getter for 借据ID
    * @generated
    */
   public String getDueBillId(){
      return this.dueBillId;
   }
   /**
    * setter for 借据ID
    * @generated
    */
   public void setDueBillId(String dueBillId){
      this.dueBillId = dueBillId;
   }

   /**
    * getter for 合同ID
    * @generated
    */
   public String getLoanContractId(){
      return this.loanContractId;
   }
   /**
    * setter for 合同ID
    * @generated
    */
   public void setLoanContractId(String loanContractId){
      this.loanContractId = loanContractId;
   }

   /**
    * getter for 计划还款日期
    * @generated
    */
   public String getPlanRepayDate(){
      return this.planRepayDate;
   }
   /**
    * setter for 计划还款日期
    * @generated
    */
   public void setPlanRepayDate(String planRepayDate){
      this.planRepayDate = planRepayDate;
   }

   /**
    * getter for 宽限天数
    * @generated
    */
   public BigDecimal getGraceDays(){
      return this.graceDays;
   }
   /**
    * setter for 宽限天数
    * @generated
    */
   public void setGraceDays(BigDecimal graceDays){
      this.graceDays = graceDays;
   }

   /**
    * getter for 最晚应还款日期
    * @generated
    */
   public String getLatestRepayDate(){
      return this.latestRepayDate;
   }
   /**
    * setter for 最晚应还款日期
    * @generated
    */
   public void setLatestRepayDate(String latestRepayDate){
      this.latestRepayDate = latestRepayDate;
   }

   /**
    * getter for 批扣或提前还款日期
    * @generated
    */
   public String getFactRepayDate(){
      return this.factRepayDate;
   }
   /**
    * setter for 批扣或提前还款日期
    * @generated
    */
   public void setFactRepayDate(String factRepayDate){
      this.factRepayDate = factRepayDate;
   }

   /**
    * getter for 实际还款记账日期
    * @generated
    */
   public String getFactAccountDate(){
      return this.factAccountDate;
   }
   /**
    * setter for 实际还款记账日期
    * @generated
    */
   public void setFactAccountDate(String factAccountDate){
      this.factAccountDate = factAccountDate;
   }

   /**
    * getter for 计划还款本金金额
    * @generated
    */
   public BigDecimal getPlanPrcpAmt(){
      return this.planPrcpAmt;
   }
   /**
    * setter for 计划还款本金金额
    * @generated
    */
   public void setPlanPrcpAmt(BigDecimal planPrcpAmt){
      this.planPrcpAmt = planPrcpAmt;
   }

   /**
    * getter for 计划还款利息金额
    * @generated
    */
   public BigDecimal getPlanIntAmt(){
      return this.planIntAmt;
   }
   /**
    * setter for 计划还款利息金额
    * @generated
    */
   public void setPlanIntAmt(BigDecimal planIntAmt){
      this.planIntAmt = planIntAmt;
   }

   /**
    * getter for 计划还款合计金额
    * @generated
    */
   public BigDecimal getPlanSumAmt(){
      return this.planSumAmt;
   }
   /**
    * setter for 计划还款合计金额
    * @generated
    */
   public void setPlanSumAmt(BigDecimal planSumAmt){
      this.planSumAmt = planSumAmt;
   }

   /**
    * getter for 是否最后一期
    * @generated
    */
   public String getIslastPero(){
      return this.islastPero;
   }
   /**
    * setter for 是否最后一期
    * @generated
    */
   public void setIslastPero(String islastPero){
      this.islastPero = islastPero;
   }

   /**
    * getter for 历史逾期标志
    * @generated
    */
   public String getHisOverdueStat(){
      return this.hisOverdueStat;
   }
   /**
    * setter for 历史逾期标志
    * @generated
    */
   public void setHisOverdueStat(String hisOverdueStat){
      this.hisOverdueStat = hisOverdueStat;
   }

   /**
    * getter for 最新状态
    * @generated
    */
   public String getStat(){
      return this.stat;
   }
   /**
    * setter for 最新状态
    * @generated
    */
   public void setStat(String stat){
      this.stat = stat;
   }

   /**
    * getter for 扣款指令状态
    * @generated
    */
   public String getCmdStat(){
      return this.cmdStat;
   }
   /**
    * setter for 扣款指令状态
    * @generated
    */
   public void setCmdStat(String cmdStat){
      this.cmdStat = cmdStat;
   }

   /**
    * getter for 还款标志
    * @generated
    */
   public String getPrepayFlag(){
      return this.prepayFlag;
   }
   /**
    * setter for 还款标志
    * @generated
    */
   public void setPrepayFlag(String prepayFlag){
      this.prepayFlag = prepayFlag;
   }

   /**
    * getter for 提前还款申请单编号
    * @generated
    */
   public String getPrepayAppNo(){
      return this.prepayAppNo;
   }
   /**
    * setter for 提前还款申请单编号
    * @generated
    */
   public void setPrepayAppNo(String prepayAppNo){
      this.prepayAppNo = prepayAppNo;
   }

   /**
    * getter for 计划标志
    * @generated
    */
   public String getPlanFlag(){
      return this.planFlag;
   }
   /**
    * setter for 计划标志
    * @generated
    */
   public void setPlanFlag(String planFlag){
      this.planFlag = planFlag;
   }

   /**
    * getter for 展期合同编号
    * @generated
    */
   public String getExtdContractId(){
      return this.extdContractId;
   }
   /**
    * setter for 展期合同编号
    * @generated
    */
   public void setExtdContractId(String extdContractId){
      this.extdContractId = extdContractId;
   }

   /**
    * getter for 应收本金
    * @generated
    */
   public BigDecimal getRcvamt(){
      return this.rcvamt;
   }
   /**
    * setter for 应收本金
    * @generated
    */
   public void setRcvamt(BigDecimal rcvamt){
      this.rcvamt = rcvamt;
   }

   /**
    * getter for 应收利息
    * @generated
    */
   public BigDecimal getRcvint(){
      return this.rcvint;
   }
   /**
    * setter for 应收利息
    * @generated
    */
   public void setRcvint(BigDecimal rcvint){
      this.rcvint = rcvint;
   }

   /**
    * getter for 应收罚息
    * @generated
    */
   public BigDecimal getRcvcint(){
      return this.rcvcint;
   }
   /**
    * setter for 应收罚息
    * @generated
    */
   public void setRcvcint(BigDecimal rcvcint){
      this.rcvcint = rcvcint;
   }

   /**
    * getter for 应收复利
    * @generated
    */
   public BigDecimal getRcvcpint(){
      return this.rcvcpint;
   }
   /**
    * setter for 应收复利
    * @generated
    */
   public void setRcvcpint(BigDecimal rcvcpint){
      this.rcvcpint = rcvcpint;
   }

   /**
    * getter for 减免利息
    * @generated
    */
   public BigDecimal getSubint(){
      return this.subint;
   }
   /**
    * setter for 减免利息
    * @generated
    */
   public void setSubint(BigDecimal subint){
      this.subint = subint;
   }

   /**
    * getter for 减免罚息
    * @generated
    */
   public BigDecimal getSubcint(){
      return this.subcint;
   }
   /**
    * setter for 减免罚息
    * @generated
    */
   public void setSubcint(BigDecimal subcint){
      this.subcint = subcint;
   }

   /**
    * getter for 减免复利
    * @generated
    */
   public BigDecimal getSubpint(){
      return this.subpint;
   }
   /**
    * setter for 减免复利
    * @generated
    */
   public void setSubpint(BigDecimal subpint){
      this.subpint = subpint;
   }

   /**
    * getter for 应收合计
    * @generated
    */
   public BigDecimal getRcvsum(){
      return this.rcvsum;
   }
   /**
    * setter for 应收合计
    * @generated
    */
   public void setRcvsum(BigDecimal rcvsum){
      this.rcvsum = rcvsum;
   }

   /**
    * getter for 已还本金
    * @generated
    */
   public BigDecimal getRtnamt(){
      return this.rtnamt;
   }
   /**
    * setter for 已还本金
    * @generated
    */
   public void setRtnamt(BigDecimal rtnamt){
      this.rtnamt = rtnamt;
   }

   /**
    * getter for 已还利息
    * @generated
    */
   public BigDecimal getRtnint(){
      return this.rtnint;
   }
   /**
    * setter for 已还利息
    * @generated
    */
   public void setRtnint(BigDecimal rtnint){
      this.rtnint = rtnint;
   }

   /**
    * getter for 已还罚息
    * @generated
    */
   public BigDecimal getRtnpint(){
      return this.rtnpint;
   }
   /**
    * setter for 已还罚息
    * @generated
    */
   public void setRtnpint(BigDecimal rtnpint){
      this.rtnpint = rtnpint;
   }

   /**
    * getter for 已还复利
    * @generated
    */
   public BigDecimal getRtncint(){
      return this.rtncint;
   }
   /**
    * setter for 已还复利
    * @generated
    */
   public void setRtncint(BigDecimal rtncint){
      this.rtncint = rtncint;
   }

   /**
    * getter for 已还合计
    * @generated
    */
   public BigDecimal getRtnsum(){
      return this.rtnsum;
   }
   /**
    * setter for 已还合计
    * @generated
    */
   public void setRtnsum(BigDecimal rtnsum){
      this.rtnsum = rtnsum;
   }

   /**
    * getter for 打印次数
    * @generated
    */
   public BigDecimal getPrncnt(){
      return this.prncnt;
   }
   /**
    * setter for 打印次数
    * @generated
    */
   public void setPrncnt(BigDecimal prncnt){
      this.prncnt = prncnt;
   }

   /**
    * getter for 创建时间
    * @generated
    */
   public String getCreateDate(){
      return this.createDate;
   }
   /**
    * setter for 创建时间
    * @generated
    */
   public void setCreateDate(String createDate){
      this.createDate = createDate;
   }

   /**
    * getter for 上次更新人
    * @generated
    */
   public String getLastUpdOperid(){
      return this.lastUpdOperid;
   }
   /**
    * setter for 上次更新人
    * @generated
    */
   public void setLastUpdOperid(String lastUpdOperid){
      this.lastUpdOperid = lastUpdOperid;
   }

   /**
    * getter for 上次更新时间
    * @generated
    */
   public String getLastUpdDate(){
      return this.lastUpdDate;
   }
   /**
    * setter for 上次更新时间
    * @generated
    */
   public void setLastUpdDate(String lastUpdDate){
      this.lastUpdDate = lastUpdDate;
   }

	public String getDueAppNo() {
		return dueAppNo;
	}
	
	public void setDueAppNo(String dueAppNo) {
		this.dueAppNo = dueAppNo;
	}
   

}