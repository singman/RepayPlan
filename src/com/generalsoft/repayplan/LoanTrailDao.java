package com.generalsoft.repayplan;
import org.loushang.next.data.StatefulDatabean;
/**
 * Created with IntelliJ IDEA.
 * User: wangss@inspur.com
 * To change this template use File | Settings | File Templates.
 */
/*贷款试算*/
public class LoanTrailDao extends StatefulDatabean {
    /*汽车品牌*/
    private String carBrand;
    /*汽车品牌名*/
    private String carBrandName;
    /*汽车型号*/
    private String carType;
    /*汽车型号名*/
    private String carTypeName;
    /*贷款金额  必选项*/
    private double loanTotal;
    /*车辆价格*/
    private double carPrice;
    /*客户利率  必选项*/
    private double monthlyInte;
    /*营销产品内码*/
    private String prdId;
    /*营销产品代码*/
    private String prdCode;
    /*营销产品*/
    private String productName;
     /*产品类型  必选项*/
    private String prdType;
    /*还款计划*/
    private String repayPlan;
    /*贷款期限  必选项*/
    private int loanMonths;
    /*百龙比例  (百龙时)必选项*/
    private double baiLongRatio;
   /*备注1*/
    private String remark1;
    /*备注2*/
    private String remark2;
     /*备注3*/
    private String remark3;
    private String billID;//借据ID
    private String contID;//合同ID
    private int currentterm;//当前期次，老系统中的最新期次
    private String lastrepaydate;//老系统中最新的计划还款日日期
    private String grantdate;//放款日期
    private String existFlag;//半付半贷的还款计划存在标志，若存在，则不生成，以免重复生成还款计划
    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public double getLoanTotal() {
        return loanTotal;
    }

    public void setLoanTotal(double loanTotal) {
        this.loanTotal = loanTotal;
    }

    public double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(double carPrice) {
        this.carPrice = carPrice;
    }

    public double getMonthlyInte() {
        return monthlyInte;
    }

    public void setMonthlyInte(double monthlyInte) {
        this.monthlyInte = monthlyInte;
    }

    public String getPrdId() {
        return prdId;
    }

    public void setPrdId(String prdId) {
        this.prdId = prdId;
    }

    public String getPrdCode() {
        return prdCode;
    }

    public void setPrdCode(String prdCode) {
        this.prdCode = prdCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    public int getLoanMonths() {
        return loanMonths;
    }

    public void setLoanMonths(int loanMonths) {
        this.loanMonths = loanMonths;
    }

    public double getBaiLongRatio() {
        return baiLongRatio;
    }

    public void setBaiLongRatio(double baiLongRatio) {
        this.baiLongRatio = baiLongRatio;
    }

    public String getRemark1() {
        return remark1;
    }

    public void setRemark1(String remark1) {
        this.remark1 = remark1;
    }

    public String getRemark2() {
        return remark2;
    }

    public void setRemark2(String remark2) {
        this.remark2 = remark2;
    }

    public String getRemark3() {
        return remark3;
    }

    public void setRemark3(String remark3) {
        this.remark3 = remark3;
    }

	public String getCarBrandName() {
		return carBrandName;
	}

	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}

	public String getCarTypeName() {
		return carTypeName;
	}

	public void setCarTypeName(String carTypeName) {
		this.carTypeName = carTypeName;
	}

	public String getBillID() {
		return billID;
	}

	public void setBillID(String billID) {
		this.billID = billID;
	}

	public String getContID() {
		return contID;
	}

	public void setContID(String contID) {
		this.contID = contID;
	}

	public int getCurrentterm() {
		return currentterm;
	}

	public void setCurrentterm(int currentterm) {
		this.currentterm = currentterm;
	}

	public String getLastrepaydate() {
		return lastrepaydate;
	}

	public void setLastrepaydate(String lastrepaydate) {
		this.lastrepaydate = lastrepaydate;
	}

	public String getGrantdate() {
		return grantdate;
	}

	public void setGrantdate(String grantdate) {
		this.grantdate = grantdate;
	}

	public String getExistFlag() {
		return existFlag;
	}

	public void setExistFlag(String existFlag) {
		this.existFlag = existFlag;
	}
    
}
