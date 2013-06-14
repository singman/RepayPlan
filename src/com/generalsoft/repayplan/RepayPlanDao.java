package com.generalsoft.repayplan;

import org.loushang.next.dao.EntityDao;

public class RepayPlanDao extends EntityDao<LoanTrailDao> {

	@Override
	protected Class getEntityClass() {
		// TODO Auto-generated method stub
		return LoanTrailDao.class;
	}

}
