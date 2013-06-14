package com.generalsoft.util;

import com.generalsoft.util.TsException;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 该类封装一些通用的
 *  能从数据库获取的全局参数、码表之类的方法
 *
 * User: wy
 * Date: 13-4-9
 * Time: 下午4:18
 */
public class GetSysConf {

    private static TsBaseJdbcDao dao = (TsBaseJdbcDao) TsDaoFactory.getTsBaseDao(null);
    /**
     * 获取全局参数
     * 从表lssycs中，通过orgId与key获取值；
     * @param orgId
     * @param key
     * @return
     */
    public static String getGlobalParam(String orgId, String key) {
        String returnVal = "";
        String sql = "SELECT F_VALUE FROM LSSYCS WHERE F_JGBH=? AND F_VKEY = ?";
        Object[] objs = dao.executeDetailExt(sql, orgId, key);
        if (objs != null && objs.length == 1) {
            returnVal = (String)objs[0];
        }
        return returnVal;
    }

    /**
     * 根据type从lscode表中获取列表
     
     * @param type
     * @return
     */
    public static List getCodeByType(String type) {
        String sql = "SELECT * FROM LSCODE CD WHERE CD.F_TYPE = ?";
        return dao.executeQueryExt(sql, type);
    }

    /**
     * 根据type和code从lscode表中获取单条记录
     
     * @param type
     * @param code
     * @return
     */
    public static Map getCodeById(String type, String code) {
        String sql = "SELECT * FROM LSCODE CD WHERE CD.F_TYPE = ? AND CD.F_CODE = ?";
        List list = dao.executeQueryExt(sql, type, code);
        Map map = null;
        if (!list.isEmpty()) {
            map = (Map)list.get(0);
        }
        return map;
    }
    /**
     * 根据字典类型和字典值从楼上表PUB_DICT_ITEM中获取单条记录文字值
     * @param type 字典类型
     * @param code
     * @return
     */
    public static String getDictItemValue(String type, String code) {
        String sql = "SELECT CD.* FROM PUB_DICT_ITEM CD WHERE CD.DICT_CODE = ? AND CD.ITEM_CODE = ?";
        List list = dao.executeQueryExt(sql, type, code);
        Map map = null;
        String itemValue = null;
        if (!list.isEmpty()) {
            map = (Map)list.get(0);
            itemValue = (String)map.get("ITEM_VALUE");
        }
        return itemValue;
    }
    /**
     * 根据贷款申请id查询这笔贷款的状态值
     * @param applyId 
     * @return
     */
    public static String getApplyStatusByApplyId(String applyId) {
        String sql = "SELECT CD.* FROM PUB_DICT_ITEM CD,LOAN_APPLY l  WHERE CD.DICT_CODE = 'applyStatus' AND l.APPLY_STATUS = CD.ITEM_CODE AND l.APPLY_ID = ?";
        List list = dao.executeQueryExt(sql, applyId);
        Map map = null;
        String itemValue = null;
        if (!list.isEmpty()) {
            map = (Map)list.get(0);
            itemValue = (String)map.get("ITEM_VALUE");
        }
        return itemValue;
    }
    /**
     * 从任何表中读取数据，只能从单表读取
     
     * @param tableName
     * @param params key=value类型
     * @return
     */
    public static List getAnyTable(String tableName, Map params) throws TsException {
        if (null == tableName || "".equals(tableName)) {
            throw new TsException("tableName不可为空");
        }
        StringBuffer sql = new StringBuffer("SELECT * FROM " + tableName) ;
        List paramsList = new ArrayList();
        if (!params.isEmpty()) {
            sql.append(" WHERE 1 = 1");
            for (String key : (Set<String>)params.keySet()) {
                sql.append(" AND ").append(key).append(" = ?");
                paramsList.add(params.get(key));
            }
        }
        return dao.executeQueryExt(sql.toString(), paramsList.toArray());
    }

    /**
     * 从数据库获取当前系统时间
     *
     * @param format
     * @return
     */
    public static String getCurDate(String format) {
        if (format == null || "".equals(format)) {
            format = "YYYYMMDD";
        }
        String sql = "SELECT TO_CHAR(SYSDATE, ?) AS CUR_DATE FROM DUAL";
        Object[] objs = dao.executeDetailExt(sql, format);
        String date = "";
        if (objs.length > 0) {
            date = (String)objs[0];
        }
        return date;
    }

    /**
     * 从web服务器获取当前系统时间
     * @param format
     * @return
     * @throws TsException
     */
    /*public static String getCurDateByWebServer(String format) throws TsException {
        if (format == null || "".equals(format)) {
            format = "YYYYMMDD";
        }
        Date date = new Date();
        String dateStr = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            dateStr = sdf.format(date);
        } catch (Exception e) {
            e.printStackTrace();
            throw new TsException("日期格式可能有错：" + format + ";\n异常信息为：" + e.getMessage());
        }
        return dateStr;
    }*/

}
