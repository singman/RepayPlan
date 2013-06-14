package com.generalsoft.repayplan;

import java.math.BigDecimal;

import org.loushang.next.dao.Column;
import org.loushang.next.dao.Table;
import org.loushang.next.data.Rule;
import org.loushang.next.data.StatefulDatabean;

import com.generalsoft.repayplan.LoanApply;;;

/**
 * @title:LoanDueBill
 * @description:
 * @author:
 * @since:2013-04-25
 * @version:1.0
*/
 @Table(tableName = "LOAN_DUE_BILL" , keyFields = "dueBillId")
public class LoanDueBill extends StatefulDatabean {
   //借据ID
   	@Rule(value="require")
	@Column(name = "DUE_BILL_ID")
   private String dueBillId;
   //合同ID
   	@Rule(value="require")
	@Column(name = "CONT_ID")
   private String contId;
   //营销产品
	@Column(name = "PRDCODE")
   private String prdcode;
   //经销商编号
	@Column(name = "DEALER_CODE")
   private String dealerCode;
   //贷款申请ID
	@Column(name = "APPLY_ID")
   private String applyId;
   //放款申请ID
	@Column(name = "GRANT_ID")
   private String grantId;
   //借据编号
	@Column(name = "DUE_BILL_CODE")
   private String dueBillCode;
   //客户编号
	@Column(name = "CUST_CODE")
   private String custCode;
	 //经销商账户内码
	@Column(name = "DEALER_ACT_ID")
   private String dealerActId;
	 //客户账户内码
	@Column(name = "CUST_ACT_ID")
   private String custActId;
   //所属分支机构
	@Column(name = "BRANCH_CODE")
   private String branchCode;
   //贷款分类
	@Column(name = "LOAN_TYPE")
   private String loanType;
   //数据来源
	@Column(name = "DATA_SOURCE")
   private String dataSource;
   //借据状态  0表示制单、1、信贷复核、2、已放款入账、3已撤销、4、已展期、5、已终止
	@Column(name = "BILL_STAT")
   private String billStat;
   //期限周期方式
	@Column(name = "TERM_TYPE")
   private String termType;
   //放款日期
	@Column(name = "GRANT_DATE")
   private String grantDate;
   //到期日期
	@Column(name = "END_DATE")
   private String endDate;
   //实际到期日期
	@Column(name = "FACT_END_DATE")
   private String factEndDate;
   //贷款期限
	@Column(name = "TERM")
   private Integer term;
   //实际期限
	@Column(name = "CURRENT_TERM")
   private String currentTerm;
   //宽限方式
	@Column(name = "GRACE_TYPE")
   private String graceType;
   //宽限期天数
   	@Rule(value="number")
	@Column(name = "GRACE_PERIOD")
   private BigDecimal gracePeriod;
   //币种编号
	@Column(name = "CURCD")
   private String curcd;
   //贷款总金额
   	@Rule(value="number")
	@Column(name = "TOTAMT")
   private BigDecimal totamt;
   //还款方式
	@Column(name = "REPAY_TYPE")
   private String repayType;
   //还款周期
   	@Rule(value="number")
	@Column(name = "REPAY_PERIOD")
   private BigDecimal repayPeriod;
   //还款内部卡号
	@Column(name = "REPAY_CARDNO")
   private String repayCardno;
   //还款内部账号
	@Column(name = "REPAY_ACTNO")
   private String repayActno;
   //还款日确定方式
	@Column(name = "REPAY_DATE_TYPE")
   private String repayDateType;
   //还款日
	@Column(name = "REPAY_DATE")
   private String repayDate;
   //利率调整方式
	@Column(name = "INTRATE_ADJ")
   private String intrateAdj;
   //利率生效日期
	@Column(name = "RATEDATE")
   private String ratedate;
   //浮动类型
	@Column(name = "FLOAT_TYPE")
   private String floatType;
   //浮动值
   	@Rule(value="number")
	@Column(name = "FLOAT_VALUE")
   private BigDecimal floatValue;
   //客户执行利率
   	@Rule(value="number")
	@Column(name = "INTRATE")
   private BigDecimal intrate;
   //逾期利率
   	@Rule(value="number")
	@Column(name = "OVERDUE_RATE")
   private BigDecimal overdueRate;
   //贴息率
   	@Rule(value="number")
	@Column(name = "DISOUNT_RATE")
   private BigDecimal disountRate;
   //佣金金额
   	@Rule(value="number")
	@Column(name = "COMAMT")
   private BigDecimal comamt;
   //手续费金额
   	@Rule(value="number")
	@Column(name = "FEE_AMT")
   private BigDecimal feeAmt;
   //收益率
   	@Rule(value="number")
	@Column(name = "YIELD")
   private BigDecimal yield;
   //放款方向
	@Column(name = "PAY_DIRECT")
   private String payDirect;
   //放款账号/卡号
	@Column(name = "PAY_ACTNO")
   private String payActno;
   //生效标志
	@Column(name = "STATUS")
   private String status;
   //清收状态
	@Column(name = "CLP_STAT")
   private String clpStat;
   //核销状态
	@Column(name = "WRITEOFF_STAT")
   private String writeoffStat;
   //贷款形态
	@Column(name = "TRM_CLASS")
   private String trmClass;
   //结清方式
	@Column(name = "CLP_FLAG")
   private String clpFlag;
   //结清完成日期
	@Column(name = "CLP_DATE")
   private String clpDate;
   //摊销规则
	@Column(name = "APP_TYPE")
   private String appType;
   //厂商贴息金额
   	@Rule(value="number")
	@Column(name = "MANUFACT_SUBSD_INTST")
   private BigDecimal manufactSubsdIntst;
   //经销商贴息金额
   	@Rule(value="number")
	@Column(name = "DEALER_SUBSD_INTST")
   private BigDecimal dealerSubsdIntst;
   //贴息总额
   	@Rule(value="number")
	@Column(name = "SUBSD_INTST_SUM")
   private BigDecimal subsdIntstSum;
   //已摊销金额
   	@Rule(value="number")
	@Column(name = "AMORTISED_AMT")
   private BigDecimal amortisedAmt;
   //贷款用途代码
	@Column(name = "USAGENO")
   private String usageno;
   //贷款用途描述
	@Column(name = "USAGE")
   private String usage;
   //备注
	@Column(name = "REMARK")
   private String remark;
   //贷款余额
   	@Rule(value="number")
	@Column(name = "LOAN_BAL")
   private BigDecimal loanBal;
   //五级分类状态
	@Column(name = "CLASS5STAT")
   private String class5stat;
   //贷款内部账号
	@Column(name = "LOAN_ACTNO")
   private String loanActno;
   //逾期内部账号
	@Column(name = "OVERDUE_ACTNO")
   private String overdueActno;
   //非应计内部账号
	@Column(name = "NOACCURAL_ACTNO")
   private String noaccuralActno;
   //累计违约次数
   	@Rule(value="number")
	@Column(name = "TERMSPASTDUE")
   private BigDecimal termspastdue;
   //当前最早逾期日期
	@Column(name = "LONG_OWE_DATE")
   private String longOweDate;
   //上次还款日期
	@Column(name = "RECENTPAYDATE")
   private String recentpaydate;
   //上次更新操作员
	@Column(name = "LAST_UPD_OPERID")
   private String lastUpdOperid;
   //上次更新日期
	@Column(name = "LAST_UPD_DATE")
   private String lastUpdDate;
   //待调整客户利率
   	@Rule(value="number")
	@Column(name = "ADJUSTING_INTRATE")
   private BigDecimal adjustingIntrate;
   //待调整逾期利率
   	@Rule(value="number")
	@Column(name = "ADJUSTING_OVD_RATE")
   private BigDecimal adjustingOvdRate;
   //待调整贷款收益率
   	@Rule(value="number")
	@Column(name = "ADJUSTING_YIELD")
   private BigDecimal adjustingYield;
   //利率待调整标志
	@Column(name = "ADJUSTING_FLAG")
   private String adjustingFlag;
	
	public LoanDueBill(){}
	public LoanDueBill(LoanApply apply){
		this.prdcode = apply.getPrdCode();
		this.dealerCode = apply.getDealerCode();
		this.applyId = apply.getApplyId();
		this.custCode = apply.getCustCode();
		this.grantId = apply.getGrantId();
		this.grantDate = apply.getGrantDate();
		this.term = apply.getLoanMonth();
		this.totamt = apply.getLoanSumAmt();
		this.repayDate = apply.getPayday();
		this.intrate = apply.getInterestRate();
		this.overdueRate = apply.getOverdueRate();
		this.manufactSubsdIntst = apply.getFactDiscount();
		this.dealerSubsdIntst = apply.getCropDiscount();
	}
	public static LoanDueBill getLoanDueBill(LoanApply apply,LoanDueBill loanDue){
		loanDue.prdcode = apply.getPrdCode();
		loanDue.dealerCode = apply.getDealerCode();
		loanDue.applyId = apply.getApplyId();
		loanDue.custCode = apply.getCustCode();
		loanDue.grantId = apply.getGrantId();
		loanDue.grantDate = apply.getGrantDate();
		loanDue.term = apply.getLoanMonth();
		loanDue.totamt = apply.getLoanSumAmt();
		loanDue.repayDate = apply.getPayday();
		loanDue.intrate = apply.getInterestRate();
		loanDue.overdueRate = apply.getOverdueRate();
		loanDue.manufactSubsdIntst = apply.getFactDiscount();
		loanDue.dealerSubsdIntst = apply.getCropDiscount();
		return loanDue;
	}
	
   public Integer getTerm() {
		return term;
	}
	public void setTerm(Integer term) {
		this.term = term;
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
   public String getContId(){
      return this.contId;
   }
   /**
    * setter for 合同ID
    * @generated
    */
   public void setContId(String contId){
      this.contId = contId;
   }

   /**
    * getter for 营销产品
    * @generated
    */
   public String getPrdcode(){
      return this.prdcode;
   }
   /**
    * setter for 营销产品
    * @generated
    */
   public void setPrdcode(String prdcode){
      this.prdcode = prdcode;
   }

   /**
    * getter for 经销商编号
    * @generated
    */
   public String getDealerCode(){
      return this.dealerCode;
   }
   /**
    * setter for 经销商编号
    * @generated
    */
   public void setDealerCode(String dealerCode){
      this.dealerCode = dealerCode;
   }

   /**
    * getter for 贷款申请ID
    * @generated
    */
   public String getApplyId(){
      return this.applyId;
   }
   /**
    * setter for 贷款申请ID
    * @generated
    */
   public void setApplyId(String applyId){
      this.applyId = applyId;
   }

   /**
    * getter for 放款申请ID
    * @generated
    */
   public String getGrantId(){
      return this.grantId;
   }
   /**
    * setter for 放款申请ID
    * @generated
    */
   public void setGrantId(String grantId){
      this.grantId = grantId;
   }

   /**
    * getter for 借据编号
    * @generated
    */
   public String getDueBillCode(){
      return this.dueBillCode;
   }
   /**
    * setter for 借据编号
    * @generated
    */
   public void setDueBillCode(String dueBillCode){
      this.dueBillCode = dueBillCode;
   }

   /**
    * getter for 客户编号
    * @generated
    */
   public String getCustCode(){
      return this.custCode;
   }
   /**
    * setter for 客户编号
    * @generated
    */
   public void setCustCode(String custCode){
      this.custCode = custCode;
   }

   /**
    * getter for 所属分支机构
    * @generated
    */
   public String getBranchCode(){
      return this.branchCode;
   }
   /**
    * setter for 所属分支机构
    * @generated
    */
   public void setBranchCode(String branchCode){
      this.branchCode = branchCode;
   }

   /**
    * getter for 贷款分类
    * @generated
    */
   public String getLoanType(){
      return this.loanType;
   }
   /**
    * setter for 贷款分类
    * @generated
    */
   public void setLoanType(String loanType){
      this.loanType = loanType;
   }

   /**
    * getter for 数据来源
    * @generated
    */
   public String getDataSource(){
      return this.dataSource;
   }
   /**
    * setter for 数据来源
    * @generated
    */
   public void setDataSource(String dataSource){
      this.dataSource = dataSource;
   }

   /**
    * getter for 借据状态
    * @generated
    */
   public String getBillStat(){
      return this.billStat;
   }
   /**
    * setter for 借据状态
    * @generated
    */
   public void setBillStat(String billStat){
      this.billStat = billStat;
   }

   /**
    * getter for 期限周期方式
    * @generated
    */
   public String getTermType(){
      return this.termType;
   }
   /**
    * setter for 期限周期方式
    * @generated
    */
   public void setTermType(String termType){
      this.termType = termType;
   }

   /**
    * getter for 放款日期
    * @generated
    */
   public String getGrantDate(){
      return this.grantDate;
   }
   /**
    * setter for 放款日期
    * @generated
    */
   public void setGrantDate(String grantDate){
      this.grantDate = grantDate;
   }

   /**
    * getter for 到期日期
    * @generated
    */
   public String getEndDate(){
      return this.endDate;
   }
   /**
    * setter for 到期日期
    * @generated
    */
   public void setEndDate(String endDate){
      this.endDate = endDate;
   }

   /**
    * getter for 实际到期日期
    * @generated
    */
   public String getFactEndDate(){
      return this.factEndDate;
   }
   /**
    * setter for 实际到期日期
    * @generated
    */
   public void setFactEndDate(String factEndDate){
      this.factEndDate = factEndDate;
   }


   /**
    * getter for 实际期限
    * @generated
    */
   public String getCurrentTerm(){
      return this.currentTerm;
   }
   /**
    * setter for 实际期限
    * @generated
    */
   public void setCurrentTerm(String currentTerm){
      this.currentTerm = currentTerm;
   }

   /**
    * getter for 宽限方式
    * @generated
    */
   public String getGraceType(){
      return this.graceType;
   }
   /**
    * setter for 宽限方式
    * @generated
    */
   public void setGraceType(String graceType){
      this.graceType = graceType;
   }

   /**
    * getter for 宽限期天数
    * @generated
    */
   public BigDecimal getGracePeriod(){
      return this.gracePeriod;
   }
   /**
    * setter for 宽限期天数
    * @generated
    */
   public void setGracePeriod(BigDecimal gracePeriod){
      this.gracePeriod = gracePeriod;
   }

   /**
    * getter for 币种编号
    * @generated
    */
   public String getCurcd(){
      return this.curcd;
   }
   /**
    * setter for 币种编号
    * @generated
    */
   public void setCurcd(String curcd){
      this.curcd = curcd;
   }

   /**
    * getter for 贷款总金额
    * @generated
    */
   public BigDecimal getTotamt(){
      return this.totamt;
   }
   /**
    * setter for 贷款总金额
    * @generated
    */
   public void setTotamt(BigDecimal totamt){
      this.totamt = totamt;
   }

   /**
    * getter for 还款方式
    * @generated
    */
   public String getRepayType(){
      return this.repayType;
   }
   /**
    * setter for 还款方式
    * @generated
    */
   public void setRepayType(String repayType){
      this.repayType = repayType;
   }

   /**
    * getter for 还款周期
    * @generated
    */
   public BigDecimal getRepayPeriod(){
      return this.repayPeriod;
   }
   /**
    * setter for 还款周期
    * @generated
    */
   public void setRepayPeriod(BigDecimal repayPeriod){
      this.repayPeriod = repayPeriod;
   }

   /**
    * getter for 还款内部卡号
    * @generated
    */
   public String getRepayCardno(){
      return this.repayCardno;
   }
   /**
    * setter for 还款内部卡号
    * @generated
    */
   public void setRepayCardno(String repayCardno){
      this.repayCardno = repayCardno;
   }

   /**
    * getter for 还款内部账号
    * @generated
    */
   public String getRepayActno(){
      return this.repayActno;
   }
   /**
    * setter for 还款内部账号
    * @generated
    */
   public void setRepayActno(String repayActno){
      this.repayActno = repayActno;
   }

   /**
    * getter for 还款日确定方式
    * @generated
    */
   public String getRepayDateType(){
      return this.repayDateType;
   }
   /**
    * setter for 还款日确定方式
    * @generated
    */
   public void setRepayDateType(String repayDateType){
      this.repayDateType = repayDateType;
   }

   /**
    * getter for 还款日
    * @generated
    */
   public String getRepayDate(){
      return this.repayDate;
   }
   /**
    * setter for 还款日
    * @generated
    */
   public void setRepayDate(String repayDate){
      this.repayDate = repayDate;
   }

   /**
    * getter for 利率调整方式
    * @generated
    */
   public String getIntrateAdj(){
      return this.intrateAdj;
   }
   /**
    * setter for 利率调整方式
    * @generated
    */
   public void setIntrateAdj(String intrateAdj){
      this.intrateAdj = intrateAdj;
   }

   /**
    * getter for 利率生效日期
    * @generated
    */
   public String getRatedate(){
      return this.ratedate;
   }
   /**
    * setter for 利率生效日期
    * @generated
    */
   public void setRatedate(String ratedate){
      this.ratedate = ratedate;
   }

   /**
    * getter for 浮动类型
    * @generated
    */
   public String getFloatType(){
      return this.floatType;
   }
   /**
    * setter for 浮动类型
    * @generated
    */
   public void setFloatType(String floatType){
      this.floatType = floatType;
   }

   /**
    * getter for 浮动值
    * @generated
    */
   public BigDecimal getFloatValue(){
      return this.floatValue;
   }
   /**
    * setter for 浮动值
    * @generated
    */
   public void setFloatValue(BigDecimal floatValue){
      this.floatValue = floatValue;
   }

   /**
    * getter for 客户执行利率
    * @generated
    */
   public BigDecimal getIntrate(){
      return this.intrate;
   }
   /**
    * setter for 客户执行利率
    * @generated
    */
   public void setIntrate(BigDecimal intrate){
      this.intrate = intrate;
   }

   /**
    * getter for 逾期利率
    * @generated
    */
   public BigDecimal getOverdueRate(){
      return this.overdueRate;
   }
   /**
    * setter for 逾期利率
    * @generated
    */
   public void setOverdueRate(BigDecimal overdueRate){
      this.overdueRate = overdueRate;
   }

   /**
    * getter for 贴息率
    * @generated
    */
   public BigDecimal getDisountRate(){
      return this.disountRate;
   }
   /**
    * setter for 贴息率
    * @generated
    */
   public void setDisountRate(BigDecimal disountRate){
      this.disountRate = disountRate;
   }

   /**
    * getter for 佣金金额
    * @generated
    */
   public BigDecimal getComamt(){
      return this.comamt;
   }
   /**
    * setter for 佣金金额
    * @generated
    */
   public void setComamt(BigDecimal comamt){
      this.comamt = comamt;
   }

   /**
    * getter for 手续费金额
    * @generated
    */
   public BigDecimal getFeeAmt(){
      return this.feeAmt;
   }
   /**
    * setter for 手续费金额
    * @generated
    */
   public void setFeeAmt(BigDecimal feeAmt){
      this.feeAmt = feeAmt;
   }

   /**
    * getter for 收益率
    * @generated
    */
   public BigDecimal getYield(){
      return this.yield;
   }
   /**
    * setter for 收益率
    * @generated
    */
   public void setYield(BigDecimal yield){
      this.yield = yield;
   }

   /**
    * getter for 放款方向
    * @generated
    */
   public String getPayDirect(){
      return this.payDirect;
   }
   /**
    * setter for 放款方向
    * @generated
    */
   public void setPayDirect(String payDirect){
      this.payDirect = payDirect;
   }

   /**
    * getter for 放款账号/卡号
    * @generated
    */
   public String getPayActno(){
      return this.payActno;
   }
   /**
    * setter for 放款账号/卡号
    * @generated
    */
   public void setPayActno(String payActno){
      this.payActno = payActno;
   }

   /**
    * getter for 生效标志
    * @generated
    */
   public String getStatus(){
      return this.status;
   }
   /**
    * setter for 生效标志
    * @generated
    */
   public void setStatus(String status){
      this.status = status;
   }

   /**
    * getter for 清收状态
    * @generated
    */
   public String getClpStat(){
      return this.clpStat;
   }
   /**
    * setter for 清收状态
    * @generated
    */
   public void setClpStat(String clpStat){
      this.clpStat = clpStat;
   }

   /**
    * getter for 核销状态
    * @generated
    */
   public String getWriteoffStat(){
      return this.writeoffStat;
   }
   /**
    * setter for 核销状态
    * @generated
    */
   public void setWriteoffStat(String writeoffStat){
      this.writeoffStat = writeoffStat;
   }

   /**
    * getter for 贷款形态
    * @generated
    */
   public String getTrmClass(){
      return this.trmClass;
   }
   /**
    * setter for 贷款形态
    * @generated
    */
   public void setTrmClass(String trmClass){
      this.trmClass = trmClass;
   }

   /**
    * getter for 结清方式
    * @generated
    */
   public String getClpFlag(){
      return this.clpFlag;
   }
   /**
    * setter for 结清方式
    * @generated
    */
   public void setClpFlag(String clpFlag){
      this.clpFlag = clpFlag;
   }

   /**
    * getter for 结清完成日期
    * @generated
    */
   public String getClpDate(){
      return this.clpDate;
   }
   /**
    * setter for 结清完成日期
    * @generated
    */
   public void setClpDate(String clpDate){
      this.clpDate = clpDate;
   }

   /**
    * getter for 摊销规则
    * @generated
    */
   public String getAppType(){
      return this.appType;
   }
   /**
    * setter for 摊销规则
    * @generated
    */
   public void setAppType(String appType){
      this.appType = appType;
   }

   /**
    * getter for 厂商贴息金额
    * @generated
    */
   public BigDecimal getManufactSubsdIntst(){
      return this.manufactSubsdIntst;
   }
   /**
    * setter for 厂商贴息金额
    * @generated
    */
   public void setManufactSubsdIntst(BigDecimal manufactSubsdIntst){
      this.manufactSubsdIntst = manufactSubsdIntst;
   }

   /**
    * getter for 经销商贴息金额
    * @generated
    */
   public BigDecimal getDealerSubsdIntst(){
      return this.dealerSubsdIntst;
   }
   /**
    * setter for 经销商贴息金额
    * @generated
    */
   public void setDealerSubsdIntst(BigDecimal dealerSubsdIntst){
      this.dealerSubsdIntst = dealerSubsdIntst;
   }

   /**
    * getter for 贴息总额
    * @generated
    */
   public BigDecimal getSubsdIntstSum(){
      return this.subsdIntstSum;
   }
   /**
    * setter for 贴息总额
    * @generated
    */
   public void setSubsdIntstSum(BigDecimal subsdIntstSum){
      this.subsdIntstSum = subsdIntstSum;
   }

   /**
    * getter for 已摊销金额
    * @generated
    */
   public BigDecimal getAmortisedAmt(){
      return this.amortisedAmt;
   }
   /**
    * setter for 已摊销金额
    * @generated
    */
   public void setAmortisedAmt(BigDecimal amortisedAmt){
      this.amortisedAmt = amortisedAmt;
   }


   /**
    * getter for 贷款用途代码
    * @generated
    */
   public String getUsageno(){
      return this.usageno;
   }
   /**
    * setter for 贷款用途代码
    * @generated
    */
   public void setUsageno(String usageno){
      this.usageno = usageno;
   }

   /**
    * getter for 贷款用途描述
    * @generated
    */
   public String getUsage(){
      return this.usage;
   }
   /**
    * setter for 贷款用途描述
    * @generated
    */
   public void setUsage(String usage){
      this.usage = usage;
   }

   /**
    * getter for 备注
    * @generated
    */
   public String getRemark(){
      return this.remark;
   }
   /**
    * setter for 备注
    * @generated
    */
   public void setRemark(String remark){
      this.remark = remark;
   }


   /**
    * getter for 贷款余额
    * @generated
    */
   public BigDecimal getLoanBal(){
      return this.loanBal;
   }
   /**
    * setter for 贷款余额
    * @generated
    */
   public void setLoanBal(BigDecimal loanBal){
      this.loanBal = loanBal;
   }

   /**
    * getter for 五级分类状态
    * @generated
    */
   public String getClass5stat(){
      return this.class5stat;
   }
   /**
    * setter for 五级分类状态
    * @generated
    */
   public void setClass5stat(String class5stat){
      this.class5stat = class5stat;
   }

   /**
    * getter for 贷款内部账号
    * @generated
    */
   public String getLoanActno(){
      return this.loanActno;
   }
   /**
    * setter for 贷款内部账号
    * @generated
    */
   public void setLoanActno(String loanActno){
      this.loanActno = loanActno;
   }

   /**
    * getter for 逾期内部账号
    * @generated
    */
   public String getOverdueActno(){
      return this.overdueActno;
   }
   /**
    * setter for 逾期内部账号
    * @generated
    */
   public void setOverdueActno(String overdueActno){
      this.overdueActno = overdueActno;
   }

   /**
    * getter for 非应计内部账号
    * @generated
    */
   public String getNoaccuralActno(){
      return this.noaccuralActno;
   }
   /**
    * setter for 非应计内部账号
    * @generated
    */
   public void setNoaccuralActno(String noaccuralActno){
      this.noaccuralActno = noaccuralActno;
   }

   /**
    * getter for 累计违约次数
    * @generated
    */
   public BigDecimal getTermspastdue(){
      return this.termspastdue;
   }
   /**
    * setter for 累计违约次数
    * @generated
    */
   public void setTermspastdue(BigDecimal termspastdue){
      this.termspastdue = termspastdue;
   }

   /**
    * getter for 当前最早逾期日期
    * @generated
    */
   public String getLongOweDate(){
      return this.longOweDate;
   }
   /**
    * setter for 当前最早逾期日期
    * @generated
    */
   public void setLongOweDate(String longOweDate){
      this.longOweDate = longOweDate;
   }

   /**
    * getter for 上次还款日期
    * @generated
    */
   public String getRecentpaydate(){
      return this.recentpaydate;
   }
   /**
    * setter for 上次还款日期
    * @generated
    */
   public void setRecentpaydate(String recentpaydate){
      this.recentpaydate = recentpaydate;
   }

   /**
    * getter for 上次更新操作员
    * @generated
    */
   public String getLastUpdOperid(){
      return this.lastUpdOperid;
   }
   /**
    * setter for 上次更新操作员
    * @generated
    */
   public void setLastUpdOperid(String lastUpdOperid){
      this.lastUpdOperid = lastUpdOperid;
   }

   /**
    * getter for 上次更新日期
    * @generated
    */
   public String getLastUpdDate(){
      return this.lastUpdDate;
   }
   /**
    * setter for 上次更新日期
    * @generated
    */
   public void setLastUpdDate(String lastUpdDate){
      this.lastUpdDate = lastUpdDate;
   }

   /**
    * getter for 待调整客户利率
    * @generated
    */
   public BigDecimal getAdjustingIntrate(){
      return this.adjustingIntrate;
   }
   /**
    * setter for 待调整客户利率
    * @generated
    */
   public void setAdjustingIntrate(BigDecimal adjustingIntrate){
      this.adjustingIntrate = adjustingIntrate;
   }

   /**
    * getter for 待调整逾期利率
    * @generated
    */
   public BigDecimal getAdjustingOvdRate(){
      return this.adjustingOvdRate;
   }
   /**
    * setter for 待调整逾期利率
    * @generated
    */
   public void setAdjustingOvdRate(BigDecimal adjustingOvdRate){
      this.adjustingOvdRate = adjustingOvdRate;
   }

   /**
    * getter for 待调整贷款收益率
    * @generated
    */
   public BigDecimal getAdjustingYield(){
      return this.adjustingYield;
   }
   /**
    * setter for 待调整贷款收益率
    * @generated
    */
   public void setAdjustingYield(BigDecimal adjustingYield){
      this.adjustingYield = adjustingYield;
   }

   /**
    * getter for 利率待调整标志
    * @generated
    */
   public String getAdjustingFlag(){
      return this.adjustingFlag;
   }
   /**
    * setter for 利率待调整标志
    * @generated
    */
   public void setAdjustingFlag(String adjustingFlag){
      this.adjustingFlag = adjustingFlag;
   }
	public String getDealerActId() {
		return dealerActId;
	}
	public void setDealerActId(String dealerActId) {
		this.dealerActId = dealerActId;
	}
	public String getCustActId() {
		return custActId;
	}
	public void setCustActId(String custActId) {
		this.custActId = custActId;
	}
   

}