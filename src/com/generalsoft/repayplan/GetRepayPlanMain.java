package com.generalsoft.repayplan;

import java.sql.Connection;
import java.util.List;

import com.generalsoft.util.DBHelper;
import com.generalsoft.util.GetSysConf;
import com.generalsoft.util.TsBaseJdbcDao;
import com.generalsoft.util.TsDaoFactory;


public class GetRepayPlanMain {

	public static void main(String[] args) throws Exception {
		Connection conn;
		conn = DBHelper.getCon();
		RepayPlanGenerator rpg = new RepayPlanGenerator();
		List<LoanRepayPlan> planlist;
		TsBaseJdbcDao<LoanRepayPlan> planDao = (TsBaseJdbcDao<LoanRepayPlan>)TsDaoFactory.getTsBaseDao(LoanRepayPlan.class);
		String date = GetSysConf.getCurDate("YYYY-MM-DD HH24:MI:SS");
		System.out.println("参数格式：\n hxdebx  生成等额本息后续还款计划\n jhdebx  更新等额本息计划还款计划\n bfbd    生成半付半贷还款计划\n all     生成全部还款计划     ");
		if(args.length !=1)
		{
			System.out.println("参数个数不符合要求，还款计划未执行");
		}
		else
		{
			if(args[0].equals( "hxdebx"))
			{
				System.out.println("开始生成等额本息还款计划！");
				long start = System.currentTimeMillis();
				rpg.FollowUpRepayPlanGenerator(conn);
				System.out.println("等额本息还款计划完成！"+"当前系统时间-------"+System.currentTimeMillis());
				long end = System.currentTimeMillis();
				System.out.println("程序运行时间： "+(end-start)+"ms");

			}
			else if(args[0].equals("jhdebx"))
			{
				System.out.println("开始更新等额本息计划还款计划！");
				long start = System.currentTimeMillis();
				rpg.InitExpectedRepayPlan(conn);//生成放款时还款计划
				System.out.println("等额本息计划还款计划更新完成！");
				long end = System.currentTimeMillis();
				System.out.println("程序运行时间： "+(end-start)+"ms");
			}
			else if(args[0].equals("bfbd"))
			{
				System.out.println("开始生成半付半贷还款计划！");
				long start = System.currentTimeMillis();
				rpg.generateBFBDRepayPlan(conn);
				System.out.println("生成半付半贷还款计划完成！");
				long end = System.currentTimeMillis();
				System.out.println("程序运行时间： "+(end-start)+"ms");
			}
			else if(args[0].equals("all"))
			{
				System.out.println("开始生成所有还款计划！");
				long start = System.currentTimeMillis();
				rpg.FollowUpRepayPlanGenerator(conn);//生成后续还款计划
				rpg.InitExpectedRepayPlan(conn);//生成放款时还款计划
				rpg.generateBFBDRepayPlan(conn);//生成半付半贷还款计划
				System.out.println("生成所有还款计划完成！");
				long end = System.currentTimeMillis();
				System.out.println("程序运行时间： "+(end-start)+"ms");
			}
			else
			{
				System.out.println("参数有误，还款计划未执行");
			}
		}
		
	}
}
