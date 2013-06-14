package com.generalsoft.util;

import org.loushang.next.core.SystemException;
import org.loushang.next.dao.DaoFactory;
import org.loushang.next.mvc.Messages;
import org.loushang.next.utils.ClassUtil;
import org.loushang.persistent.util.domain.BaseJdbcDao;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: wy
 * Date: 13-3-25
 * Time: 下午6:20
 * 该类继承与楼上的DaoFactory，
 *   因楼上的factory不能在生成dao时传递构造参数，
 *    故扩展此类
 */
public class TsDaoFactory extends DaoFactory {
    protected static Map cached = new HashMap();

    /**
     * 获取dao，传递三个参数
     * @param clazz 要获取的dao的class路径
     * @param constructorType 该dao的构造方法中的参数的类型
     * @param objs  该dao的构造方法中的参数
     * @return
     */
    public static BaseJdbcDao getDao(String clazz, Class[] constructorType, Object... objs) {
		/*if (cached.containsKey(clazz)) {
			return (BaseJdbcDao) cached.get(clazz);
		}*/
		try {
            Class cls = ClassUtil.findClass(clazz);
            BaseJdbcDao dao = (BaseJdbcDao) cls.getConstructor(constructorType).newInstance(objs);
			dao.afterPropertiesSet();
			//cached.put(clazz, dao);
			return dao;
		} catch (Exception e) {
			throw new SystemException(
					Messages.getClientBundle().getLocaleMsg("FRAMEWORK.MVC.150","创建Dao失败({0})",new Object[] {clazz }),
			e);
		}
	}

    /**
     * 获取dao，传递两个参数，值得注意的是：
     *    调用该方法生成dao实例时，其构造方法的参数是class类型
     * @param clazz 要获取的dao的class路径
     * @param objs  该dao的构造方法中的参数
     * @return
     */
    public static BaseJdbcDao getDao(String clazz, Object... objs) {
		/*if (cached.containsKey(clazz)) {
			return (BaseJdbcDao) cached.get(clazz);
		}*/
		try {
            Class cls = ClassUtil.findClass(clazz);
            BaseJdbcDao dao = (BaseJdbcDao) cls.getConstructor(new Class[] {Class.class}).newInstance(objs);
			dao.afterPropertiesSet();
			//cached.put(clazz, dao);
			return dao;
		} catch (Exception e) {
			throw new SystemException(
					Messages.getClientBundle().getLocaleMsg("FRAMEWORK.MVC.150","创建Dao失败({0})",new Object[] {clazz }),
			e);
		}
	}

    /**
     * 获取 TsBaseJdbcDao的实例，如果传递实体bean的类型
     * @param javaBeanClass
     * @return
     */
    public static BaseJdbcDao getTsBaseDao(Class javaBeanClass) {
        String clazz = "com.generalsoft.util.TsBaseJdbcDao";
        String key = "TsBaseJdbcDao," + (javaBeanClass == null ? "null" : javaBeanClass.getName());
		if (cached.containsKey(key)) {
			return (BaseJdbcDao) cached.get(key);
		}
		try {
            Class cls = ClassUtil.findClass(clazz);
            BaseJdbcDao dao = null;
            if (javaBeanClass != null) {
                dao = (BaseJdbcDao) cls.getConstructor(new Class[] {Class.class}).newInstance(javaBeanClass);
            } else {
                dao = (BaseJdbcDao) cls.newInstance();
            }
			dao.afterPropertiesSet();
			cached.put(key, dao);
			return dao;
		} catch (Exception e) {
			throw new SystemException(
					Messages.getClientBundle().getLocaleMsg("FRAMEWORK.MVC.150","创建Dao失败({0})",new Object[] {clazz }),
			e);
		}
	}

}
