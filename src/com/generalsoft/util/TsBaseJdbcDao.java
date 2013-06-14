package com.generalsoft.util;

import com.generalsoft.util.TsException;
import com.generalsoft.util.TsUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loushang.next.dao.EntityDao;
import org.loushang.next.dao.SqlCreator;
import org.loushang.next.data.DataSet;
import org.loushang.persistent.jdbc.core.BatchUpdateData;
import org.loushang.persistent.jdbc.datasource.DataSourceFactory;
import org.loushang.persistent.jdbc.object.SqlUpdate;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 12-6-26
 * Time: 下午4:03
 * <p/>
 * 扩展楼上的 EntityDao<T> ，添加一些自定义的方法
 */
public class TsBaseJdbcDao<E> extends EntityDao<E> {
    private Log log = LogFactory.getLog(this.getClass());

    public TsBaseJdbcDao() throws Exception {
        this.setDataSource(DataSourceFactory.defaultFactory.getDataSource("dataSource"));
        this.afterPropertiesSet();
    }

    public TsBaseJdbcDao(Class entityClass) throws Exception {
        this.entityClass = entityClass;
        log.info("对应的JavaBean类是：" + this.entityClass);
        this.setDataSource(DataSourceFactory.defaultFactory.getDataSource("dataSource"));
        this.afterPropertiesSet();
    }

    /**
     * 初始化方法
     */
    protected void initDao() {
        if (this.getEntityClass() != null) {
            super.initDao();
        }
    }

    //该方法 自动提供参数类型
    public List executeQueryExt(String sql, Object... objects) {
        log.info("调用executeQueryExt方法:" + TsUtils.getPreparedSQL(sql, objects));
        List list = null;
        try {
            list = executeQuery(sql, TsUtils.getSqlTypes(objects), objects);
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeQueryExt方法抛出：" + e.getMessage());
        }
        return list;
    }

    //该方法 查询第1条
    public Map executeQueryOne(String sql, Object... objects) {
        log.info("调用executeQueryOne方法:" + TsUtils.getPreparedSQL(sql, objects));
        List list = null;
        Map retMap = new HashMap();
        try {
            list = executeQuery(sql, TsUtils.getSqlTypes(objects), objects);
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeQueryExt方法抛出：" + e.getMessage());
        }
        if (list != null && !list.isEmpty()) {
            retMap = (Map)list.get(0);
        }
        return retMap;
    }


    //该方法 自动提供参数类型
    public Object[] executeDetailExt(String sql, Object... objects) {
        log.info("调用executeDetailExt方法:" + TsUtils.getPreparedSQL(sql, objects));
        Object[] objs = null;
        try {
            objs = executeDetail(sql, TsUtils.getSqlTypes(objects), objects);
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeDetailExt方法抛出：" + e.getMessage());
        }
        return objs;
    }

    /**
     * 返回DataSet，自动提供参数类型
     *
     * @param sql
     * @param isCovetToBean 是否自动转换成JavaBean
     * @param objects       参数数组
     * @return
     */
    public DataSet executeDataset2Bean(String sql, boolean isCovetToBean, Object... objects) {
        log.info("调用executeDatasetExt方法，转换javaBean:" + isCovetToBean + ":" + TsUtils.getPreparedSQL(sql, objects));
        DataSet ds = null;
        try {
            ds = cvtJavaBean(executeDataset(sql, TsUtils.getSqlTypes(objects), objects, isCovetToBean), isCovetToBean);
            ds = TsUtils.psrReadDateOrTime(ds, this.getEntityClass());
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeDataset2Bean方法抛出：" + e.getMessage());
        }
        return ds;
    }

    /**
     * 返回DataSet，自动提供参数类型
     *
     * @param sql
     * @param objects 参数数组
     * @return
     */
    public DataSet executeDatasetExt(String sql, Object... objects) {
        return this.executeDsHasFs(sql, false, objects);
    }

    /**
     * 返回DataSet，自动提供参数类型
     *
     * @param sql
     * @param objects 参数数组
     * @return
     */
    public DataSet executeDsHasFs(String sql, boolean hasFieldSet, Object... objects) {
        log.info("调用executeDatasetExt方法:" + TsUtils.getPreparedSQL(sql, objects));
        DataSet ds = null;
        try {
            ds = cvtJavaBean(executeDataset(sql, TsUtils.getSqlTypes(objects), objects, hasFieldSet), false);
            ds = TsUtils.psrReadDateOrTime(ds, getEntityClass());
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeDatasetExt方法抛出：" + e.getMessage());
        }
        return ds;
    }

    /**
     * 返回DataSet，提供分页功能，自动提供参数类型
     *
     * @param sql
     * @param startPages    开始数
     * @param pageSize      每页长度
     * @param isCovetToBean 是否自动转换成JavaBean
     * @param objects       参数数组
     * @return
     */
    public DataSet executeDatasetPageExt(String sql, int startPages, int pageSize, boolean isCovetToBean, Object... objects) {
        log.info("调用executeDatasetPageExt方法，转换javaBean:" + isCovetToBean + ":" + TsUtils.getPreparedSQL(sql, objects));
        DataSet ds = null;
        try {
            ds = cvtJavaBean(executeDataset(sql, TsUtils.getSqlTypes(objects), objects, startPages, pageSize, isCovetToBean), isCovetToBean);
            ds = TsUtils.psrReadDateOrTime(ds, getEntityClass());
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeDatasetPageExt方法抛出：" + e.getMessage());
        }
        return ds;
    }

    /**
     * 更新
     *
     * @param sql
     * @param objects
     * @return
     */
    public int executeUpdateExt(String sql, Object... objects) {
        log.info("调用executeUpdateExt方法:" + TsUtils.getPreparedSQL(sql, objects));
        int value = 0;
        try {
            value = executeUpdate(sql, TsUtils.getSqlTypes(objects), objects);
        } catch (TsException e) {
            e.printStackTrace();
            log.error("executeUpdateExt方法抛出：" + e.getMessage());
        }
        return value;
    }

    private Class entityClass;

    public Class getEntityClass() {
        return this.entityClass;
    }

    private Map cvtMap(Map map, Class<?> cls) {
        Field[] fls = cls.getDeclaredFields();
        for (Field f : fls) {
            String fldName = f.getName();
            if (map.containsKey(fldName.toUpperCase())) {
                map.put(fldName, map.get(fldName.toUpperCase()));
                map.remove(fldName.toUpperCase());
            }
        }
        return map;
    }

    private DataSet cvtJavaBean(DataSet ds, boolean isCovetToBean) {
        if (!isCovetToBean) {
            return ds;
        }
        if (this.getEntityClass() != null) {
            for (int i = ds.getCount() - 1; i >= 0; i--) {
                Map map = ds.getRecord(i).getData();
                map = TsUtils.rmvUnderscore(map);
                map = cvtMap(map, this.getEntityClass());
                ds.getRecordSet().get(i).getData().clear();
                ds.getRecordSet().get(i).getData().putAll(map);
            }
        }
        return ds;
    }

    @Override
    public E update(E obj) {
        try {
            TsUtils.psrWriteDateOrTime(obj);
        } catch (TsException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return super.update(obj);
    }

    @Override
    public E insert(E obj) {
        try {
            TsUtils.psrWriteDateOrTime(obj);
        } catch (TsException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        return super.insert(obj);
    }

    @Override
    public int delete(Object key) {
        return super.delete(key); 
    }

    @Override
    public void save(List<E> es) {
        try {
            TsUtils.psrWriteDateOrTime(es);
        } catch (TsException e) {
            e.printStackTrace();
            log.info(e.getMessage());
        }
        super.save(es); 
    }

    /**
     * 批量执行一个sql语句的多次更新
     * @param sql
     * @param valuesList sql中？的值，因为是批量的，所以用list保存
     * @return 受影响的记录数
     */
    public int[] batchUpdateExt(String sql, final List<Object[]> valuesList) throws TsException {
        if (valuesList == null || valuesList.isEmpty()) {
            throw new TsException("批量更新接收的参数为空！");
        }
        SqlUpdate update = new SqlUpdate(getDataSource(), sql, TsUtils.getSqlTypes(valuesList.get(0)));
        BatchUpdateData bud = new BatchUpdateData() {
            public Object[] getRow(int index) {
                return valuesList.get(index);
            }
            public int getRowNum() {
                return valuesList.size();
            }
        };
        update.compile();
        for (Object[] objs : valuesList) {
            log.info("批量处理sql：" + TsUtils.getPreparedSQL(sql, objs));
        }
        return update.batchUpdate(bud);
    }


}
