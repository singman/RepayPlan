package com.generalsoft.util;


import com.generalsoft.util.TsDate;
import com.generalsoft.util.TsException;
import com.generalsoft.util.TsRuntimeException;
import org.loushang.next.dao.Column;
import org.loushang.next.dao.Table;
import org.loushang.next.data.DataSet;
import org.loushang.next.data.Record;

import java.lang.reflect.Field;
import java.net.URL;
import java.sql.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 12-6-26
 * Time: 下午4:51
 * <p/>
 * 该类存放各种辅助方法
 */
public class TsUtils {

    /**
     * 将一个JavaBean转换为一个Map，
     * key      字段名称
     * value    该字段的值
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> Map<String, Object> convertToMap(T t) throws TsException {
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = t.getClass().getDeclaredFields();

        for (Field fld : fields) {
            fld.setAccessible(true);
            try {
                map.put(fld.getName(), fld.get(t));
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new TsException("获取" + fld.getName() + "属性值时异常！异常信息：" + e.getMessage());
            }
            fld.setAccessible(false);
        }

        return map;
    }

    /**
     * 将一个Map转换为JavaBean
     *
     * @param map
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T convertToBean(Map<String, Object> map, Class<T> cls) throws TsException {
        T t = null;
        try {
            t = cls.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
            throw new TsException(cls + "对应的类型实例化失败！异常信息：" + e.getMessage());
        }
        Field[] fields = cls.getDeclaredFields();
        Map<String, Object> fieldMap = rmvUnderscore(map);
        Object o = null;
        for (Field fld : fields) {
            fld.setAccessible(true);
            if (fieldMap.containsKey(fld.getName().toUpperCase())) {
                try {
                    o = fieldMap.get(fld.getName().toUpperCase());
                    if (o != null) {
                        if ("int".equals(fld.getType().getSimpleName())) {
                            fld.set(t, Integer.parseInt(o.toString()));
                        } else if ("double".equals(fld.getType().getSimpleName())) {
                            fld.set(t, Double.parseDouble(o.toString()));
                        } else if ("float".equals(fld.getType().getSimpleName())) {
                            fld.set(t, Float.parseFloat(o.toString()));
                        } else if ("long".equals(fld.getType().getSimpleName())) {
                            fld.set(t, Long.parseLong(o.toString()));
                        } else {
                            fld.set(t, o);
                        }
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new TsException("设置" + fld.getName() + "属性值时异常！异常信息：" + e.getMessage());
                }
            }
            fld.setAccessible(false);
        }
        return t;
    }

    /**
     * 将map中的key全部转换（去除下划线）
     *
     * @param map
     * @return
     */
    public static Map<String, Object> rmvUnderscore(Map<String, Object> map) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        Set<String> keySet = map.keySet();
        for (String key : keySet) {
            retMap.put(rmvUnderscore(key).toUpperCase(), map.get(key));
        }
        return retMap;
    }

    /**
     * 去除字段中的下滑线
     * 使用楼上原生sql查询出的字段（F_TYPE）和JavaBean中的属性（fType）转换
     *
     * @param key
     * @return
     */
    public static String rmvUnderscore(String key) {
        StringBuffer buf = new StringBuffer();
        String[] characters = key.split("_");
        int len = characters.length;
        if (len > 0) {
            buf.append(characters[0].toLowerCase());
            for (int index = 1; index < len; index++) {
                buf.append(characters[index].substring(0, 1).toUpperCase()).append(characters[index].substring(1).toLowerCase());
            }
        }
        return buf.toString();

    }

    /**
     * 根据具体参数获取参数Sql类型
     *
     * @param objs
     * @return
     */
    public static int[] getSqlTypes(Object... objs) throws TsException {
        if (objs == null || objs.length == 0) {
            return null;
        }                         // throws TsException
        int[] types = new int[objs.length];
        int i = 0;
        for (Object o : objs) {
            if (null == o) {
                throw new TsException("参数不允许为null，如果要传null请传空字符串！");
            } else if (o instanceof String) {
                types[i] = Types.VARCHAR;
            } else if (o instanceof Integer) {
                types[i] = Types.INTEGER;
            } else if (o instanceof Float) {
                types[i] = Types.FLOAT;
            } else if (o instanceof Double) {
                types[i] = Types.DOUBLE;
            } else if (o instanceof java.math.BigDecimal) {
                types[i] = Types.NUMERIC;
            } else if (o instanceof java.sql.Date) {
                types[i] = Types.DATE;
            } else if (o instanceof Boolean) {
                types[i] = Types.BIT;
            } else if (o instanceof Byte) {
                types[i] = Types.TINYINT;
            } else if (o instanceof Short) {
                types[i] = Types.SMALLINT;
            } else if (o instanceof Long) {
                types[i] = Types.BIGINT;
            } else if (o instanceof Byte[]) {
                types[i] = Types.VARBINARY;
            } else if (o instanceof java.sql.Time) {
                types[i] = Types.TIME;
            } else if (o instanceof Timestamp) {
                types[i] = Types.TIMESTAMP;
            } else if (o instanceof Blob) {
                types[i] = Types.BLOB;
            } else if (o instanceof Clob) {
                types[i] = Types.CLOB;
            } else if (o instanceof Array) {
                types[i] = Types.ARRAY;
            } else if (o instanceof java.sql.Ref) {
                types[i] = Types.REF;
            } else if (o instanceof URL) {
                types[i] = Types.DATALINK;
            } else {
                types[i] = Types.VARCHAR;
            }
            i++;
        }
        return types;
    }

    public static String getPreparedSQL(String sql, Object... params) {
        //1 如果没有参数，说明是不是动态SQL语句
        int paramNum = 0;
        if (null != params) paramNum = params.length;
        if (1 > paramNum) return sql;
        //2 如果有参数，则是动态SQL语句
        StringBuffer returnSQL = new StringBuffer();
        String[] subSQL = sql.split("\\?");
        for (int i = 0; i < paramNum; i++) {
            Object obj = params[i];
            if (obj instanceof Date) {
                returnSQL.append(subSQL[i]).append(" '").append(obj.toString()).append("' ");
            } else {
                returnSQL.append(subSQL[i]).append(" '").append(obj.toString()).append("' ");
            }
        }
        if (subSQL.length > params.length) {
            returnSQL.append(subSQL[subSQL.length - 1]);
        }
        return returnSQL.toString();
    }


    /**
     * 读取日期类型时，按照配置的日期进行处理
     *
     * @param ds
     * @param cls JavaBean类型
     * @return
     */
    public static DataSet psrReadDateOrTime(DataSet ds, Class cls) throws TsException {
        if (ds == null || ds.getCount() == 0 || cls == null) {
            return ds;
        }
        Map<String, Object> tsFieldAnoMap = getAnnoField(cls);
        if (tsFieldAnoMap.isEmpty()) {
            return ds;
        }
        Set<String> keys = tsFieldAnoMap.keySet();
        int len = ds.getCount();
        Record record = null;
        TsDate tsDate = null;
        for (String key : keys) {
            tsDate = (TsDate) tsFieldAnoMap.get(key);
            for (int i = 0; i < len; i++) {
                record = ds.getRecord(i);
                record.set(key, psrDate((String) record.get(key), tsDate, false));
            }
        }
        return ds;
    }

    /**
     * 读取日期类型时，按照配置的日期进行处理
     *
     * @param ds
     * @param dateColumns 需要转换日期的列
     * @param timeColumns 需要转换时间的列
     * @return
     */
    public static DataSet psrReadDateOrTime(DataSet ds, String[] dateColumns, String[] timeColumns) throws TsException {
        if (ds == null || ds.getCount() == 0) {
            return ds;
        }
        int len = ds.getCount();
        Record record = null;
        if (dateColumns != null && dateColumns.length != 0) {
            for (String key : dateColumns) {
                for (int i = 0; i < len; i++) {
                    record = ds.getRecord(i);
                    record.set(key, psrDate((String) record.get(key), true, false));
                }
            }
        }
        if (timeColumns != null && timeColumns.length != 0) {
            for (String key : timeColumns) {
                for (int i = 0; i < len; i++) {
                    record = ds.getRecord(i);
                    record.set(key, psrDate((String) record.get(key), false, false));
                }
            }
        }
        return ds;
    }

    public static List psrReadDateOrTime(List list, String[] dateColumns, String[] timeColumns) throws TsException {
        if (list == null || list.size() == 0) {
            return list;
        }
        int len = list.size();
        Map map = null;
        if (dateColumns != null && dateColumns.length != 0) {
            for (String key : dateColumns) {
                for (int i = 0; i < len; i++) {
                    map = (Map)list.get(i);
                    map.put(key, psrDate((String) map.get(key), true, false));
                }
            }
        }
        if (timeColumns != null && timeColumns.length != 0) {
            for (String key : timeColumns) {
                for (int i = 0; i < len; i++) {
                    map = (Map)list.get(i);
                    map.put(key, psrDate((String) map.get(key), false, false));
                }
            }
        }
        return list;
    }

    /**
     * 写入日期类型时，按照配置的日期进行处理
     *
     * @param list
     * @param <T>
     * @return
     * @throws TsException
     */
    public static <T> List<T> psrWriteDateOrTime(List<T> list) throws TsException {
        if (list == null || list.isEmpty()) {
            return list;
        }
        Map<String, Object> tsFieldAnoMap = getAnnoField(list.get(0).getClass());
        if (tsFieldAnoMap.isEmpty()) {
            return list;
        }
        for (T t : list) {
            psrWriteDateOrTime(t, tsFieldAnoMap);
        }
        return list;
    }

    /**
     * 写入日期类型时，按照配置的日期进行处理
     *
     * @param t
     * @param <T>
     * @return
     */
    public static <T> T psrWriteDateOrTime(T t) throws TsException {
        if (t == null) {
            return t;
        }
        return psrWriteDateOrTime(t, getAnnoField(t.getClass()));
    }

    private static <T> T psrWriteDateOrTime(T t, Map<String, Object> tsFieldAnoMap) throws TsException {
        if (t == null) {
            return t;
        }
        if (tsFieldAnoMap.isEmpty()) {
            return t;
        }
        Class cls = t.getClass();
        Set<String> keys = tsFieldAnoMap.keySet();
        Field field = null;
        TsDate tsDate = null;
        Object value = null;
        for (String key : keys) {
            try {
                field = cls.getDeclaredField(key);
                field.setAccessible(true);
                value = field.get(t);
                if (tsFieldAnoMap.get(key) instanceof TsDate) {
                    tsDate = (TsDate) tsFieldAnoMap.get(key);
                    value = psrDate((String) value, tsDate, true);
                    field.set(t, value);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new TsException(e);
            } finally {
                field.setAccessible(false);
            }
        }
        return t;
    }

    /**
     * 通过javaBean类型获取该类有哪些Ts配置字段
     *
     * @param cls
     * @return
     */
    private static Map<String, Object> getAnnoField(Class cls) {
        /*获取javaBean上的ts注解字段：TsDate*/
        Map<String, Object> tsFieldAnoMap = new HashMap<String, Object>();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(TsDate.class)) {
                tsFieldAnoMap.put(f.getName(), f.getAnnotation(TsDate.class));
            }
        }
        return tsFieldAnoMap;
    }

    private static String psrDate(String date, TsDate format, boolean isWrite) throws TsException {
        if (date == null || "".equals(date.trim())) {
            return "";
        }
        String inFmt = null;
        String outFmt = null;
        if (isWrite) {
            inFmt = format.readFormat();
            outFmt = format.writeFormat();
        } else {
            inFmt = format.writeFormat();
            outFmt = format.readFormat();
        }
        SimpleDateFormat inSdf = new SimpleDateFormat(inFmt);
        SimpleDateFormat outSdf = new SimpleDateFormat(outFmt);
        try {
            date = outSdf.format(inSdf.parse(date));
        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new TsException(e);
        }
        return date;
    }

    /**
     * @param date
     * @param type    true date, false timestamp
     * @param isWrite
     * @return
     * @throws TsException
     */
    private static String psrDate(String date, boolean type, boolean isWrite) throws TsException {
        if (date == null || "".equals(date.trim())) {
            return "";
        }
        String inFmt = null;
        String outFmt = null;
        if (isWrite) {
            inFmt = type ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";//format.readFormat();
            outFmt = type ? "yyyyMMdd" : "yyyyMMddHHmmss";//format.writeFormat();
        } else {
            inFmt = type ? "yyyyMMdd" : "yyyyMMddHHmmss";
            outFmt = type ? "yyyy-MM-dd" : "yyyy-MM-dd HH:mm:ss";//format.readFormat();
        }
        SimpleDateFormat inSdf = new SimpleDateFormat(inFmt);
        SimpleDateFormat outSdf = new SimpleDateFormat(outFmt);
        try {
            date = outSdf.format(inSdf.parse(date));
        } catch (ParseException e) {
//            e.printStackTrace();
//            throw new TsException(e);
        }
        return date;
    }

    /**
     * 获取类的主键
     *
     * @param cls
     * @return
     */
    public static String[] getKeys(Class cls) {
        String[] keys = null;
        if (cls.isAnnotationPresent(Table.class)) {
            Table tb = (Table) cls.getAnnotation(Table.class);
            keys = tb.keyFields();
            /*for (String key : keys) {
                Field f = null;
                try {
                    f = cls.getDeclaredField(key);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
                if (f != null && f.isAnnotationPresent(Column.class)) {
                    Column c = f.getAnnotation(Column.class);
                    list.add(c.name());
                }
            }*/
        }
        return keys;
    }

    public static String getAnnoColumnName(String pk, Class cls) {
        String name = null;
        Field f = null;
        try {
            f = cls.getDeclaredField(pk);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        if (f != null && f.isAnnotationPresent(Column.class)) {
            Column c = f.getAnnotation(Column.class);
            name = c.name();
        }
        return name;
    }

    /**
     * 根据反射，获取字段对应的值
     * @param t
     * @param key
     * @param <T>
     * @return
     */
    public static <T> String getFieldValue(T t, String key) {
        Object o = null;
        try {
            Field f = t.getClass().getDeclaredField(key);
            f.setAccessible(true);
            o = f.get(t);
            f.setAccessible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o == null ? "" : o.toString();
    }

    /**
     *  通过字典替换相应的值
     *
     * @param ds         要替换的dataset
     * @param dic        字典
     * @param dsColName    dataset中要置换的列
     * @param newColName   置换后的列的新字段名，如果传null则会覆盖 置换列的内容
     * @param dicColKey     字典中的对应置换列的列名
     * @param dicColValue   字典中的对应置换列的值的列名
     * @return
     */
    public static DataSet replaceByDic(DataSet ds, List dic, String dsColName, String newColName,
                                       String dicColKey, String dicColValue) {
        if (ds == null || ds.getCount() == 0 || dic == null || dic.isEmpty()) {
            return ds;
        }
        if (dsColName == null || "".equals(dsColName.trim())) {
            throw new TsRuntimeException("请传递要替换的列！");
        }
        if (dicColKey == null || "".equals(dicColKey.trim()) || dicColValue == null || "".equals(dicColValue.trim())) {
            throw new TsRuntimeException("请传递字典对应的Key和Value！");
        }

        int size = ds.getCount();
        Set<String> keySet = ((Map)dic.get(0)).keySet();
        int count = 0;
        int mapLen = dic.size();
        for (String key : keySet) {
            if (!key.equals(dicColKey)) {
                continue;
            }
            for (int i = 0; i < size; i++) {
                Record record = ds.getRecord(i);
                count = 0;
                for (Map map : (List<Map>) dic) {
                    count++;
                    if (map.get(key).equals(record.get(dsColName))) {
                        if (newColName == null || "".equals(newColName.trim())) {
                            record.set(dsColName, map.get(dicColValue));
                        } else {
                            record.set(newColName, map.get(dicColValue));
                        }
                        break;
                    }
                    if (count == mapLen) {
                        if (newColName == null || "".equals(newColName.trim())) {
                            record.set(dsColName, record.get(dsColName));
                        } else {
                            record.set(newColName, record.get(dsColName));
                        }
                    }
                }
            }
        }
        return ds;
        /*
        示例
        DataSet ds = wyDao.executeDataset2Bean("SELECT * FROM WY_TEST", true);
        List dic = wyDao.executeQueryExt("SELECT C_ID, C_NAME FROM WY_CODE");
        ds = replaceByDic(ds, dic, "tCode", "T_CODE_NAME", "C_ID", "C_NAME");
        */
    }

}
