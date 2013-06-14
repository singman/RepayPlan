package com.generalsoft.repayplan;
import org.loushang.next.data.DataSet;
public interface IRebackCommand {
    /*传入前台的LoanTrail Bean*/
    public abstract DataSet calc(LoanTrailDao para);
}