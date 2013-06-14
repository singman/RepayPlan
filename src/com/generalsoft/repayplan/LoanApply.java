package com.generalsoft.repayplan;

import java.math.BigDecimal;
import java.util.Map;

import org.loushang.next.dao.Column;
import org.loushang.next.dao.Table;
import org.loushang.next.dao.Transient;
import org.loushang.next.data.Rule;
import org.loushang.next.data.StatefulDatabean;

/**
 * @title:LoanApply
 * @description:
 * @author:卢
 * @since:2013-03-11
 * @version:1.0
*/
 @Table(tableName = "LOAN_APPLY" , keyFields = "applyId")
public class LoanApply extends StatefulDatabean {
   //申请ID
	@Column(name = "APPLY_ID")
   private String applyId;
   //放款申请ID
	@Column(name = "GRANT_ID")
   private String grantId;
   //经销商账户内码
    @Column(name = "DEALER_ACT_ID")
    private String dealerActId;
	 //客户账户内码
	@Column(name = "CUST_ACT_ID")
   private String custActId;
   //客户编号
	@Column(name = "CUST_CODE")
   private String custCode;
   //经销商编号
	@Column(name = "DEALER_CODE")
   private String dealerCode;
   //客户类别
	@Column(name = "CUST_TYPE")
   private String custType;
   //贷款申请流程实例ID
	@Column(name = "APPLY_PROCESS_ID")
   private String applyProcessId;
   //放款申请流程实例ID
	@Column(name = "GRANT_PROCESS_ID")
   private String grantProcessId;
   //贷审会决议号
	@Column(name = "LRC_RESULT_CODE")
   private String lrcResultCode;
   //申请书编号
	@Column(name = "APPLY_CODE")
   private String applyCode;
   //贷款申请时间
	@Column(name = "APPLY_DATE")
   private String applyDate;
   //放款申请时间
	@Column(name = "GRANT_DATE")
   private String grantDate;
   //经销商销售人员名称
	@Column(name = "CUST_SALENAME")
   private String custSalename;
   //经销商销售人员联系电话
	@Rule(value="mobile")
	@Column(name = "CUST_SALETEL")
   private String custSaletel;
   //金融产品代码
	@Rule(value="require")
	@Column(name = "PRD_CODE")
   private String prdCode;
   //汽车品牌
	@Rule(value="require")
	@Column(name = "CAR_TRADEMARK")
   private String carTrademark;
	//车型代码
	@Rule(value="require")
	@Column(name = "MODEL_CODE")
   private String modelCode;
	//生产商
	@Column(name = "FACTORY_CODE")
   private String factoryCode;
   //申请用途类别
	@Column(name = "APPLY_USE")
   private String applyUse;
   //还款来源
	@Column(name = "REPAY_SOURCE")
   private String repaySource;
   //发票价格
   	@Rule(value="require")
	@Column(name = "CAR_PRICE")
   private BigDecimal carPrice;
    //实际发票价格
	@Column(name = "FACT_CAR_PRICE")
   private BigDecimal factCarPrice;
  //首付款金额
	@Column(name = "ADVANCE_PAYMENT")
   private BigDecimal advancePayment;
	 //实际首付款金额
	@Column(name = "FACT_ADVANCE_PAYMENT")
   private BigDecimal factAdvancePayment;
   //贷款总金额
   	@Rule(value="require|number")
	@Column(name = "LOAN_SUM_AMT")
   private BigDecimal loanSumAmt;
    //实际贷款总金额
	@Column(name = "FACT_LOAN_SUM_AMT")
   private BigDecimal factLoanSumAmt;
   //贷款批复金额
	@Column(name = "LOAN_APPROVE_AMT")
   private BigDecimal loanApproveAmt;
   //贷款期数（月）
   	@Rule(value="require|number")
	@Column(name = "LOAN_MONTH")
   private Integer loanMonth;
   //首付比例
	@Column(name = "LOAN_FIRSTRATE")
   private BigDecimal loanFirstrate;
   //实际首付比例
	@Column(name = "FACT_LOAN_FIRSTRATE")
  private BigDecimal factLoanFirstrate;
   //每期应付本金
	@Column(name = "PER_REPAY_CORPUS")
   private BigDecimal perRepayCorpus;
   //年利率
    @Rule(value="require")
	@Column(name = "INTEREST_RATE")
   private BigDecimal interestRate;
   //百龙贷款比例
	@Column(name = "BAILONG_RATE")
   private BigDecimal bailongRate;
   //百龙贷款金额
	@Column(name = "BAILONG_TYPE_VALUE")
   private BigDecimal bailongTypeValue;
   //操作员ID
	@Column(name = "OPER_ID")
   private String operId;
   //操作日期
	@Column(name = "OPER_DATE")
   private String operDate;
    //申请状态
	@Column(name = "APPLY_STATUS")
   private String applyStatus;
   //放款确认类型
	@Column(name = "GRANT_CONFIRM_TYPE")
   private String grantConfirmType;
   //放款确认意见
	@Column(name = "GRANT_CONFIRM_OPINION")
   private String grantConfirmOpinion;
   //放款申请状态
	@Column(name = "GRANT_STATUS")
   private String grantStatus;
   //个人信用等级修改原因
	@Column(name = "MODI_REASON")
   private String modiReason;
   //个人信用等级修改后分数
	@Column(name = "MODI_SCORE")
   private BigDecimal modiScore;
   //金融专员
	@Column(name = "JRZY_NAME")
   private String jrzyName;
   //金融专员手机号
	@Rule(value ="mobile")
	@Column(name = "JRZY_PHONE")
   private String jrzyPhone;
   //受理时间
	@Column(name = "SL_DATE")
   private String slDate;
   //受理人
	@Column(name = "SL_OPER")
   private String slOper;
   //每期还款日
	@Column(name = "PAYDAY")
   private String payday;
   //是否有担保
	@Column(name = "IFGART")
   private String ifgart;
   //是否有共同申请人(还款人)
	@Column(name = "IFJOIN")
   private String ifjoin;
   //核准通知书打印次数
   	@Rule(value="number")
	@Column(name = "VERIFY_NOTICE_PRINT_NUM")
   private BigDecimal  verifyNoticePrintNum;
    //创建时间
	@Column(name = "CREATE_DATE")
   	private String creatDate;  
    //最后更新人
	@Column(name = "LAST_UPD_OPERID")
   	private String lastUpdateOperId;
    //最后更新时间
	@Column(name = "LAST_UPD_DATE")
   	private String lastUpDate;
	 //放款经办人
	@Column(name = "GRANT_OPER_ID")
   	private String grantOperId;
    //经销商贴息额
    @Column(name = "DEALER_SUBSD_INTST")
    private BigDecimal cropDiscount;
    //厂商贴息额
    @Column(name = "MANUFACT_SUBSD_INTST")
    private BigDecimal factDiscount;
    //流程最后审批意见
    @Column(name = "PROCESS_OPINION")
    private String processOpinion;
    //操作员名称
    @Transient
    private String operName;
    //经销商账号
    @Transient
    private String dealerActNo;
    //客户账号
    @Transient
    private String actNo;
    //账号名称
    @Transient
    private String actName;
    //账号类型
    @Transient
    private String bankType;
    //开户省份
    @Transient
    private String actProv;
    //开户城市
    @Transient
    private String actCity;
    //开户行编号
    @Transient
    private String actBankNo;
    //开户行名称
    @Transient
    private String actBankName;
    //开户行行号
    @Transient
    private String actBankNum;
    //协议编号
    @Transient
    private String contractNo;
    //缴费编号
    @Transient
    private String portNo;
    //合同编号
    @Transient
    private String contCode;
    //客户证件类型
    @Transient
    private String certType;
    //客户证件号码
    @Transient
    private String certNo;
    //借据编号
    @Transient
    private String billCode;
    //合同签订日期
    @Transient
    private String contApplyDate;
    //合同生效日期
    @Transient
    private String startDate;
    //到期日期
    @Transient
    private String endDate;
	//客户名称
	@Transient
	private String custName;
	//经销商名称
	@Transient
	private String dealerName;
	//营销产品名
	@Transient
	private String prdName;
	//宽限期
    @Transient
    private String repayDate;
    //逾期率
    @Transient
    private BigDecimal overdueRate;
  //营销产品类型
	@Transient
	private String prdType;
    //收益率
    @Transient
    private BigDecimal yieLd;
    //利率调整时间
	@Transient
    private String ratedate;
	/*利率是否浮动*/
	@Transient
    private String isFloatRate;
	/*浮动类型*/
	@Transient
    private String floatType;
	/*浮动值*/
	@Transient
    private String floatValue;
	//还款方式
	@Transient
	private String repayPlan;
	//品牌名字
	@Transient
	private String carTrademarkName;
	//车型名字
	@Transient
	private String modelCodeName;
	 //车架号
	@Transient
    private String carVin;
   //车辆颜色
	@Transient
    private String carColor;
   //汽车发动机号
	@Transient
    private String carEnginecard;
	 //厂商名
	@Transient
    private String factCodeName;
	

	public LoanApply(){};
	public LoanApply(Map<String,Object> map){
		this.custActId = (String)map.get("CUST_ACT_ID");//客户账号内码
		this.dealerActId = (String)map.get("DEALER_ACT_ID");//经销商账号内码
		this.factAdvancePayment = (BigDecimal)map.get("FACT_ADVANCE_PAYMENT");//实际首付款
		this.factCarPrice = (BigDecimal)map.get("FACT_CAR_PRICE");//实际价格
		this.factLoanFirstrate = (BigDecimal)map.get("FACT_LOAN_FIRSTRATE");//实际首付比例
		this.factLoanSumAmt = (BigDecimal)map.get("FACT_LOAN_SUM_AMT");//实际贷款总额
		this.carColor = (String)map.get("CAR_COLOR");//车颜色
		this.carEnginecard = (String)map.get("CAR_ENGINECARD");//发动机号
		this.carVin = (String)map.get("CAR_VIN");//车架号
		this.custName = (String)map.get("CUST_NAME");//客户名称
		this.certNo = (String)map.get("CERT_NO");//证件号码
		this.applyId = (String)map.get("APPLY_ID");//贷款申请id
		this.grantId = (String)map.get("GRANT_ID");//放款申请id
		this.custCode = (String)map.get("CUST_CODE");//客户编号
		this.dealerCode = (String)map.get("DEALER_CODE");//经销商编号
		this.dealerName = (String)map.get("DEALER_NAME");//经销商名
		this.custType = (String)map.get("CUST_TYPE");//客户类别
		this.certType = (String)map.get("CERT_TYPE");
		this.applyProcessId = (String)map.get("APPLY_PROCESS_ID");//申请流程实例
		this.grantProcessId =(String)map.get("GRANT_PROCESS_ID");//放款流程实例
		this.lrcResultCode = (String)map.get("LRC_RESULT_CODE");//贷审会决议号
		this.applyCode = (String)map.get("APPLY_CODE");//申请书编号
		this.applyDate = (String)map.get("APPLY_DATE");//申请日期
		this.grantDate = (String)map.get("GRANT_DATE");//放款日期
		this.custSaletel = (String)map.get("CUST_SALETEL");//经销商销售人员联系电话
		this.custSalename = (String)map.get("CUST_SALENAME");//经销商销售人员
		this.prdCode = (String)map.get("PRD_CODE");//营销产品id
		this.grantStatus = (String)map.get("GRANT_STATUS");//放款状态
		this.processOpinion = (String)map.get("PROCESS_OPINION");
		this.prdName = (String)map.get("PRD_NAME");//营销产品名
		this.prdType = (String)map.get("PRDTYPE");//营销产品类型
		this.repayPlan = (String)map.get("REPAYPLAN");//还款方式
		this.overdueRate = (BigDecimal)map.get("OVERDUEDATE");//逾期利率
		this.yieLd = (BigDecimal)map.get("YIELD");//收益率
		this.carTrademark = (String)map.get("CAR_TRADEMARK");//品牌
		this.carTrademarkName = (String)map.get("CAR_TRADEMARKNAME");//品牌名
		this.modelCode = (String)map.get("MODEL_CODE");//车型
		this.modelCodeName = (String)map.get("MODEL_NAME");//车型名
		this.factoryCode = (String)map.get("FACTORY_CODE");//厂商编号
		this.applyUse = (String)map.get("APPLY_USE");//贷款用途
		this.repaySource = (String)map.get("REPAY_SOURCE");//还款来源
		this.factCodeName = (String)map.get("FACT_NAME");//厂商名
		this.carPrice = (BigDecimal)(map.get("CAR_PRICE"));//车辆价格
		this.advancePayment = (BigDecimal)(map.get("ADVANCE_PAYMENT"));//首付款金额
		this.loanSumAmt = (BigDecimal)(map.get("LOAN_SUM_AMT"));//贷款总额
		this.loanApproveAmt = (BigDecimal)(map.get("LOAN_APPROVE_AMT"));//贷款批复金额
		if(map.get("LOAN_MONTH") != null){
			this.loanMonth =Integer.valueOf(((BigDecimal)map.get("LOAN_MONTH")).toString());//贷款期限
		}
		this.loanFirstrate = (BigDecimal)(map.get("LOAN_FIRSTRATE")); //首付比例
		this.perRepayCorpus = (BigDecimal)(map.get("PER_REPAY_CORPUS"));  //每期应付本金
		this.interestRate = (BigDecimal)(map.get("INTEREST_RATE")); //执行年利率
		this.bailongRate = (BigDecimal)(map.get("BAILONG_RATE")); //百龙贷款比例
		this.bailongTypeValue = (BigDecimal)(map.get("BAILONG_TYPE_VALUE")); //百龙贷款金额
		this.operId = (String)map.get("OPER_ID");  //操作员ID
		this.operDate = (String)map.get("OPER_DATE");//操作日期
		this.applyStatus = (String)map.get("APPLY_STATUS");//申请状态
		this.grantConfirmType = (String)map.get("GRANT_CONFIRM_TYPE"); //放款确认类型
		this.grantConfirmOpinion = (String)map.get("GRANT_CONFIRM_OPINION");//放款确认意见
		this.modiReason = (String)map.get("MODI_REASON");  //个人信用等级修改原因
		this.modiScore = (BigDecimal)(map.get("MODI_SCORE")); //个人信用等级修改后分数
		this.jrzyName = (String)map.get("JRZY_NAME");//金融专员
		this.slDate = (String)map.get("SL_DATE");//受理时间
		this.slOper = (String)map.get("SL_OPER"); //受理人
		this.payday = (String)map.get("PAYDAY");//申请还款日
		this.ifgart = (String)map.get("IFGART");//是否有担保
		this.ifjoin = (String)map.get("IFJOIN");//是否有共同申请人(还款人)
		this.verifyNoticePrintNum = (BigDecimal)(map.get("VERIFY_NOTICE_PRINT_NUM"));//核准通知书打印次数
		this.creatDate = (String)map.get("CREATE_DATE");//创建时间
		this.lastUpdateOperId = (String)map.get("LAST_UPD_OPERID");
		this.lastUpDate = (String)map.get("LAST_UPD_DATE");
		this.grantOperId = (String)map.get("GRANT_OPER_ID"); //放款经办人
	}

    public String getRepayDate() {
        return repayDate;
    }
    public void setRepayDate(String repayDate) {
        this.repayDate = repayDate;
    }
    public String getDealerActNo() {
        return dealerActNo;
    }
    public void setDealerActNo(String dealerActNo) {
        this.dealerActNo = dealerActNo;
    }
    public String getContCode() {
        return contCode;
    }
    public void setContCode(String contCode) {
        this.contCode = contCode;
    }
    public String getCertType() {
        return certType;
    }
    public void setCertType(String certType) {
        this.certType = certType;
    }
    public String getBillCode() {
        return billCode;
    }
    public void setBillCode(String billCode) {
        this.billCode = billCode;
    }
    public String getContApplyDate() {
        return contApplyDate;
    }
    public void setContApplyDate(String contApplyDate) {
        this.contApplyDate = contApplyDate;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    public BigDecimal getYieLd() {
        return yieLd;
    }
    public void setYieLd(BigDecimal yieLd) {
        this.yieLd = yieLd;
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
	public BigDecimal getFactCarPrice() {
		return factCarPrice;
	}
	public void setFactCarPrice(BigDecimal factCarPrice) {
		this.factCarPrice = factCarPrice;
	}
	public BigDecimal getFactAdvancePayment() {
		return factAdvancePayment;
	}
	public void setFactAdvancePayment(BigDecimal factAdvancePayment) {
		this.factAdvancePayment = factAdvancePayment;
	}

	public BigDecimal getFactLoanSumAmt() {
		return factLoanSumAmt;
	}
	public void setFactLoanSumAmt(BigDecimal factLoanSumAmt) {
		this.factLoanSumAmt = factLoanSumAmt;
	}
	public Integer getLoanMonth() {
		return loanMonth;
	}
	public void setLoanMonth(Integer loanMonth) {
		this.loanMonth = loanMonth;
	}
	public BigDecimal getFactLoanFirstrate() {
		return factLoanFirstrate;
	}
	public void setFactLoanFirstrate(BigDecimal factLoanFirstrate) {
		this.factLoanFirstrate = factLoanFirstrate;
	}
	public String getIsFloatRate() {
		return isFloatRate;
	}
	public void setIsFloatRate(String isFloatRate) {
		this.isFloatRate = isFloatRate;
	}
	public String getFloatType() {
		return floatType;
	}
	public void setFloatType(String floatType) {
		this.floatType = floatType;
	}
	public String getFloatValue() {
		return floatValue;
	}
	public void setFloatValue(String floatValue) {
		this.floatValue = floatValue;
	}
	public String getRatedate() {
		return ratedate;
	}
	public void setRatedate(String ratedate) {
		this.ratedate = ratedate;
	}
	public String getFactCodeName() {
		return factCodeName;
	}
	public void setFactCodeName(String factCodeName) {
		this.factCodeName = factCodeName;
	}
    public String getCarVin() {
		return carVin;
	}
	public void setCarVin(String carVin) {
		this.carVin = carVin;
	}
	public String getCarColor() {
		return carColor;
	}
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	public String getCarEnginecard() {
		return carEnginecard;
	}
	public void setCarEnginecard(String carEnginecard) {
		this.carEnginecard = carEnginecard;
	}
	public String getCarTrademarkName() {
		return carTrademarkName;
	}
	public void setCarTrademarkName(String carTrademarkName) {
		this.carTrademarkName = carTrademarkName;
	}
	public String getModelCodeName() {
		return modelCodeName;
	}
	public void setModelCodeName(String modelCodeName) {
		this.modelCodeName = modelCodeName;
	}
	public String getPrdName() {
		return prdName;
	}
	public void setPrdName(String prdName) {
		this.prdName = prdName;
	}
	public BigDecimal getCropDiscount() {
		return cropDiscount;
	}
	public void setCropDiscount(BigDecimal cropDiscount) {
		this.cropDiscount = cropDiscount;
	}
	
	public BigDecimal getFactDiscount() {
		return factDiscount;
	}
	public void setFactDiscount(BigDecimal factDiscount) {
		this.factDiscount = factDiscount;
	}
	public String getCreatDate() {
		return creatDate;
	}
	public void setCreatDate(String creatDate) {
		this.creatDate = creatDate;
	}
	public String getLastUpdateOperId() {
		return lastUpdateOperId;
	}
	public void setLastUpdateOperId(String lastUpdateOperId) {
		this.lastUpdateOperId = lastUpdateOperId;
	}
	public String getLastUpDate() {
		return lastUpDate;
	}
	public void setLastUpDate(String lastUpDate) {
		this.lastUpDate = lastUpDate;
	}
	public String getGrantOperId() {
		return grantOperId;
	}
	public void setGrantOperId(String grantOperId) {
		this.grantOperId = grantOperId;
	}
	
	public String getCertNo() {
		return certNo;
	}
	public void setCertNo(String certNo) {
		this.certNo = certNo;
	}
/**
    * getter for 申请ID
    * @generated
    */
   public String getApplyId(){
      return this.applyId;
   }
   /**
    * setter for 申请ID
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
    * getter for 经销商ID
    * @generated
    */
   public String getDealerCode(){
      return this.dealerCode;
   }
   /**
    * setter for 经销商ID
    * @generated
    */
   public void setDealerCode(String dealerCode){
      this.dealerCode = dealerCode;
   }

   /**
    * getter for 客户类别
    * @generated
    */
   public String getCustType(){
      return this.custType;
   }
   /**
    * setter for 客户类别
    * @generated
    */
   public void setCustType(String custType){
      this.custType = custType;
   }

   /**
    * getter for 贷款申请流程实例ID
    * @generated
    */
   public String getApplyProcessId(){
      return this.applyProcessId;
   }
   /**
    * setter for 贷款申请流程实例ID
    * @generated
    */
   public void setApplyProcessId(String applyProcessId){
      this.applyProcessId = applyProcessId;
   }

   /**
    * getter for 放款申请流程实例ID
    * @generated
    */
   public String getGrantProcessId(){
      return this.grantProcessId;
   }
   /**
    * setter for 放款申请流程实例ID
    * @generated
    */
   public void setGrantProcessId(String grantProcessId){
      this.grantProcessId = grantProcessId;
   }


   /**
    * getter for 贷审会决议号
    * @generated
    */
   public String getLrcResultCode(){
      return this.lrcResultCode;
   }
   /**
    * setter for 贷审会决议号
    * @generated
    */
   public void setLrcResultCode(String lrcResultCode){
      this.lrcResultCode = lrcResultCode;
   }


/**
    * getter for 申请书编号
    * @generated
    */
   public String getApplyCode(){
      return this.applyCode;
   }
   /**
    * setter for 申请书编号
    * @generated
    */
   public void setApplyCode(String applyCode){
      this.applyCode = applyCode;
   }

   /**
    * getter for 申请时间
    * @generated
    */
   public String getApplyDate(){
      return this.applyDate;
   }
   /**
    * setter for 申请时间
    * @generated
    */
   public void setApplyDate(String applyDate){
      this.applyDate = applyDate;
   }

   /**
    * getter for 放款申请时间
    * @generated
    */
   public String getGrantDate(){
      return this.grantDate;
   }
   /**
    * setter for 放款申请时间
    * @generated
    */
   public void setGrantDate(String grantDate){
      this.grantDate = grantDate;
   }

   /**
    * getter for 经销商销售人员名称
    * @generated
    */
   public String getCustSalename(){
      return this.custSalename;
   }
   /**
    * setter for 经销商销售人员名称
    * @generated
    */
   public void setCustSalename(String custSalename){
      this.custSalename = custSalename;
   }

   /**
    * getter for 经销商销售人员联系电话
    * @generated
    */
   public String getCustSaletel(){
      return this.custSaletel;
   }
   /**
    * setter for 经销商销售人员联系电话
    * @generated
    */
   public void setCustSaletel(String custSaletel){
      this.custSaletel = custSaletel;
   }

   /**
    * getter for 金融产品代码
    * @generated
    */
   public String getPrdCode(){
      return this.prdCode;
   }
   /**
    * setter for 金融产品代码
    * @generated
    */
   public void setPrdCode(String prdCode){
      this.prdCode = prdCode;
   }

   /**
    * getter for 汽车品牌
    * @generated
    */
   public String getCarTrademark(){
      return this.carTrademark;
   }
   /**
    * setter for 汽车品牌
    * @generated
    */
   public void setCarTrademark(String carTrademark){
      this.carTrademark = carTrademark;
   }


   /**
    * getter for 申请用途类别
    * @generated
    */
   public String getApplyUse(){
      return this.applyUse;
   }
   /**
    * setter for 申请用途类别
    * @generated
    */
   public void setApplyUse(String applyUse){
      this.applyUse = applyUse;
   }

   /**
    * getter for 还款来源
    * @generated
    */
   public String getRepaySource(){
      return this.repaySource;
   }
   /**
    * setter for 还款来源
    * @generated
    */
   public void setRepaySource(String repaySource){
      this.repaySource = repaySource;
   }

   /**
    * getter for 发票价格
    * @generated
    */
   public BigDecimal getCarPrice(){
      return this.carPrice;
   }
   /**
    * setter for 发票价格
    * @generated
    */
   public void setCarPrice(BigDecimal carPrice){
      this.carPrice = carPrice;
   }

   /**
    * getter for 贷款总金额
    * @generated
    */
   public BigDecimal getLoanSumAmt(){
      return this.loanSumAmt;
   }
   /**
    * setter for 贷款总金额
    * @generated
    */
   public void setLoanSumAmt(BigDecimal loanSumAmt){
      this.loanSumAmt = loanSumAmt;
   }

   /**
    * getter for 贷款批复金额
    * @generated
    */
   public BigDecimal getLoanApproveAmt(){
      return this.loanApproveAmt;
   }
   /**
    * setter for 贷款批复金额
    * @generated
    */
   public void setLoanApproveAmt(BigDecimal loanApproveAmt){
      this.loanApproveAmt = loanApproveAmt;
   }


   /**
    * getter for 首付比例
    * @generated
    */
   public BigDecimal getLoanFirstrate(){
      return this.loanFirstrate;
   }
   /**
    * setter for 首付比例
    * @generated
    */
   public void setLoanFirstrate(BigDecimal loanFirstrate){
      this.loanFirstrate = loanFirstrate;
   }

   /**
    * getter for 首付款金额
    * @generated
    */
   public BigDecimal getAdvancePayment(){
      return this.advancePayment;
   }
   /**
    * setter for 首付款金额
    * @generated
    */
   public void setAdvancePayment(BigDecimal advancePayment){
      this.advancePayment = advancePayment;
   }

   /**
    * getter for 每期应付本金
    * @generated
    */
   public BigDecimal getPerRepayCorpus(){
      return this.perRepayCorpus;
   }
   /**
    * setter for 每期应付本金
    * @generated
    */
   public void setPerRepayCorpus(BigDecimal perRepayCorpus){
      this.perRepayCorpus = perRepayCorpus;
   }

   /**
    * getter for 执行年利率
    * @generated
    */
   public BigDecimal getInterestRate(){
      return this.interestRate;
   }
   /**
    * setter for 执行年利率
    * @generated
    */
   public void setInterestRate(BigDecimal interestRate){
      this.interestRate = interestRate;
   }


   /**
    * getter for 百龙贷款比例
    * @generated
    */
   public BigDecimal getBailongRate(){
      return this.bailongRate;
   }
   /**
    * setter for 百龙贷款比例
    * @generated
    */
   public void setBailongRate(BigDecimal bailongRate){
      this.bailongRate = bailongRate;
   }


   /**
    * getter for 百龙贷款金额
    * @generated
    */
   public BigDecimal getBailongTypeValue(){
      return this.bailongTypeValue;
   }
   /**
    * setter for 百龙贷款金额
    * @generated
    */
   public void setBailongTypeValue(BigDecimal bailongTypeValue){
      this.bailongTypeValue = bailongTypeValue;
   }

    public String getFloatRate() {
        return isFloatRate;
    }

    public void setFloatRate(String floatRate) {
        isFloatRate = floatRate;
    }

    public String getProcessOpinion() {
        return processOpinion;
    }

    public void setProcessOpinion(String processOpinion) {
        this.processOpinion = processOpinion;
    }

    /**
    * getter for 操作员ID
    * @generated
    */
   public String getOperId(){
      return this.operId;
   }
   /**
    * setter for 操作员ID
    * @generated
    */
   public void setOperId(String operId){
      this.operId = operId;
   }

   /**
    * getter for 操作日期
    * @generated
    */
   public String getOperDate(){
      return this.operDate;
   }
   /**
    * setter for 操作日期
    * @generated
    */
   public void setOperDate(String operDate){
      this.operDate = operDate;
   }


   /**
    * getter for 申请状态
    * @generated
    */
   public String getApplyStatus(){
      return this.applyStatus;
   }
   /**
    * setter for 申请状态
    * @generated
    */
   public void setApplyStatus(String applyStatus){
      this.applyStatus = applyStatus;
   }

   /**
    * getter for 放款确认类型
    * @generated
    */
   public String getGrantConfirmType(){
      return this.grantConfirmType;
   }
   /**
    * setter for 放款确认类型
    * @generated
    */
   public void setGrantConfirmType(String grantConfirmType){
      this.grantConfirmType = grantConfirmType;
   }

   /**
    * getter for 放款确认意见
    * @generated
    */
   public String getGrantConfirmOpinion(){
      return this.grantConfirmOpinion;
   }
   /**
    * setter for 放款确认意见
    * @generated
    */
   public void setGrantConfirmOpinion(String grantConfirmOpinion){
      this.grantConfirmOpinion = grantConfirmOpinion;
   }

   /**
    * getter for 放款申请状态
    * @generated
    */
   public String getGrantStatus(){
      return this.grantStatus;
   }
   /**
    * setter for 放款申请状态
    * @generated
    */
   public void setGrantStatus(String grantStatus){
      this.grantStatus = grantStatus;
   }


   /**
    * getter for 个人信用等级修改原因
    * @generated
    */
   public String getModiReason(){
      return this.modiReason;
   }
   /**
    * setter for 个人信用等级修改原因
    * @generated
    */
   public void setModiReason(String modiReason){
      this.modiReason = modiReason;
   }

   /**
    * getter for 个人信用等级修改后分数
    * @generated
    */
   public BigDecimal getModiScore(){
      return this.modiScore;
   }
   /**
    * setter for 个人信用等级修改后分数
    * @generated
    */
   public void setModiScore(BigDecimal modiScore){
      this.modiScore = modiScore;
   }


   /**
    * getter for 车型代码
    * @generated
    */
   public String getModelCode(){
      return this.modelCode;
   }
   /**
    * setter for 车型代码
    * @generated
    */
   public void setModelCode(String modelCode){
      this.modelCode = modelCode;
   }

   /**
    * getter for 金融专员
    * @generated
    */
   public String getJrzyName(){
      return this.jrzyName;
   }
   /**
    * setter for 金融专员
    * @generated
    */
   public void setJrzyName(String jrzyName){
      this.jrzyName = jrzyName;
   }

   /**
    * getter for 金融专员手机号
    * @generated
    */
   public String getJrzyPhone(){
      return this.jrzyPhone;
   }
   /**
    * setter for 金融专员手机号
    * @generated
    */
   public void setJrzyPhone(String jrzyPhone){
      this.jrzyPhone = jrzyPhone;
   }

   /**
    * getter for 受理时间
    * @generated
    */
   public String getSlDate(){
      return this.slDate;
   }
   /**
    * setter for 受理时间
    * @generated
    */
   public void setSlDate(String slDate){
      this.slDate = slDate;
   }

   /**
    * getter for 受理人
    * @generated
    */
   public String getSlOper(){
      return this.slOper;
   }
   /**
    * setter for 受理人
    * @generated
    */
   public void setSlOper(String slOper){
      this.slOper = slOper;
   }

/**
    * getter for 申请还款日
    * @generated
    */
   public String getPayday(){
      return this.payday;
   }
   /**
    * setter for 申请还款日
    * @generated
    */
   public void setPayday(String payday){
      this.payday = payday;
   }

   /**
    * getter for 是否有担保
    * @generated
    */
   public String getIfgart(){
      return this.ifgart;
   }
   /**
    * setter for 是否有担保
    * @generated
    */
   public void setIfgart(String ifgart){
      this.ifgart = ifgart;
   }

   /**
    * getter for 是否有共同申请人(还款人)
    * @generated
    */
   public String getIfjoin(){
      return this.ifjoin;
   }
   /**
    * setter for 是否有共同申请人(还款人)
    * @generated
    */
   public void setIfjoin(String ifjoin){
      this.ifjoin = ifjoin;
   }

	public BigDecimal getVerifyNoticePrintNum() {
		return verifyNoticePrintNum;
	}
	public void setVerifyNoticePrintNum(BigDecimal verifyNoticePrintNum) {
		this.verifyNoticePrintNum = verifyNoticePrintNum;
	}
	public String getCustName() {
		return custName;
	}
	public void setCustName(String custName) {
		this.custName = custName;
	}
	public String getDealerName() {
		return dealerName;
	}
	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}
	public String getFactoryCode() {
		return factoryCode;
	}
	public void setFactoryCode(String factoryCode) {
		this.factoryCode = factoryCode;
	}
	public BigDecimal getOverdueRate() {
		return overdueRate;
	}
	public void setOverdueRate(BigDecimal overdueRate) {
		this.overdueRate = overdueRate;
	}
	public String getPrdType() {
		return prdType;
	}
	public void setPrdType(String prdType) {
		this.prdType = prdType;
	}
	public String getRepayPlan() {
		return repayPlan;
	}
	public void setRepayPlan(String repayPlan) {
		this.repayPlan = repayPlan;
	}
	public String getActNo() {
		return actNo;
	}
	public void setActNo(String actNo) {
		this.actNo = actNo;
	}
	public String getActName() {
		return actName;
	}
	public void setActName(String actName) {
		this.actName = actName;
	}

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getActProv() {
		return actProv;
	}
	public void setActProv(String actProv) {
		this.actProv = actProv;
	}
	public String getActCity() {
		return actCity;
	}
	public void setActCity(String actCity) {
		this.actCity = actCity;
	}
	public String getActBankNo() {
		return actBankNo;
	}
	public void setActBankNo(String actBankNo) {
		this.actBankNo = actBankNo;
	}
	public String getActBankName() {
		return actBankName;
	}
	public void setActBankName(String actBankName) {
		this.actBankName = actBankName;
	}
	public String getActBankNum() {
		return actBankNum;
	}
	public void setActBankNum(String actBankNum) {
		this.actBankNum = actBankNum;
	}
	public String getContractNo() {
		return contractNo;
	}
	public void setContractNo(String contractNo) {
		this.contractNo = contractNo;
	}
	public String getPortNo() {
		return portNo;
	}
	public void setPortNo(String portNo) {
		this.portNo = portNo;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
}