package com.generalsoft.util;

import com.generalsoft.util.TsBaseJdbcDao;
import com.generalsoft.util.TsException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.loushang.bsp.id.data.PubIdtable;
import org.loushang.bsp.id.util.MaxValueUtil;
import org.loushang.bsp.organization.domain.IStruDomain;
import org.loushang.bsp.security.context.GetBspInfo;
import org.loushang.bsp.util.DateUtil;
import org.loushang.sca.ScaComponentFactory;

import java.util.List;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: Think
 * Date: 13-4-10
 * Time: 下午1:48
 */
public class TsMaxValueUtil extends MaxValueUtil {
    private static Log log = LogFactory.getLog(TsMaxValueUtil.class);

    public static String nextStringValue(TsBaseJdbcDao dao, String id) {
        String sql = "SELECT * FROM PUB_IDTABLE A WHERE A.ID_ID = ? FOR UPDATE";
        List list = dao.executeQueryExt(sql, id);
        String nm = null;
        Map map = null;
        if (!list.isEmpty() && list.get(0) != null) {
            map = (Map)list.get(0);
            nm = map.get("ID_VALUE").toString();
        }
        if (nm == null) {
            return "";
        }
        PubIdtable pubIdTable = null;
        try {
            pubIdTable = TsUtils.convertToBean(map, PubIdtable.class);
        } catch (TsException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(nm) + 1;
        String updSql = "UPDATE PUB_IDTABLE SET ID_VALUE = ? WHERE ID_ID = ?";
        dao.executeUpdateExt(updSql, i, id);
        String returnValue = replace(i + "", pubIdTable);
        log.info("产生的新单据编号是：" + returnValue);
        return returnValue;
    }

    public static String nextStringValueByOrganId(TsBaseJdbcDao dao, String id, String orgId) {
        String sql = "SELECT * FROM PUB_IDTABLE A WHERE A.ID_ID = ? AND A.ORGAN_ID = ? FOR UPDATE";
        List list = dao.executeQueryExt(sql, id, orgId);
        String nm = null;
        Map map = null;
        if (!list.isEmpty() && list.get(0) != null) {
            map = (Map)list.get(0);
            nm = map.get("ID_VALUE").toString();
        }
        if (nm == null) {
            return "";
        }
        PubIdtable pubIdTable = null;
        try {
            pubIdTable = TsUtils.convertToBean(map, PubIdtable.class);
        } catch (TsException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(nm) + 1;
        String updSql = "UPDATE PUB_IDTABLE SET ID_VALUE = ? WHERE ID_ID = ? AND ORGAN_ID = ?";
        dao.executeUpdateExt(updSql, i, id, orgId);
        String returnValue = replace(i + "", pubIdTable);
        log.info("产生的新单据编号是：" + returnValue);
        return returnValue;
    }

    /**
     * 前缀和后缀的替换
     * @param s
     * @param p
     * @return
     */
    public static String replace(String s, PubIdtable p) {
        // 既有前缀又有后缀
        if ("1".equals(p.getIsPrefix()) && "1".equals(p.getIsSuffix())) {
            int len = p.getIdLength() -
                    (s.length() + get(p.getIdPrefix()).length() + get(p.getIdSuffix()).length());
            if (len < 0) {
                StringBuffer buff = new StringBuffer();
                buff.append("prefix[")
                        .append(p.getIdPrefix())
                        .append("],stored id[")
                        .append(s)
                        .append("] and suffix[")
                        .append(p.getIdSuffix())
                        .append("],they are longer than id's defined length:")
                        .append(p.getIdLength());
                StringBuffer bu = new StringBuffer();
                bu.append(get(p.getIdPrefix()));
                bu.append(s);
                bu.append(get(p.getIdSuffix()));
                s = bu.toString();
            }
            if (len >= 0) {
                StringBuffer buff = new StringBuffer(p.getIdLength());
                buff.append(get(p.getIdPrefix()));
                for (int i = 0; i < len; i++)
                    buff.append("0");
                buff.append(s);
                buff.append(get(p.getIdSuffix()));
                s = buff.toString();
            }
        }
        // 有前缀没有后缀
        if ("1".equals(p.getIsPrefix()) && "0".equals(p.getIsSuffix())) {
            int len =
                    p.getIdLength()
                            - (s.length() + get(p.getIdPrefix()).length());
            if (len < 0) {
                StringBuffer buff = new StringBuffer();
                buff
                    .append("prefix")
                    .append(p.getIdPrefix())
                    .append("],stored id[")
                    .append(s)
                    .append("],they are longer than id's defined length:")
                    .append(p.getIdLength());
                StringBuffer bu = new StringBuffer();
                bu.append(get(p.getIdPrefix()));
                bu.append(s);
                s = bu.toString();
            }
            if (len >= 0) {
                StringBuffer buff = new StringBuffer(p.getIdLength());
                buff.append(get(p.getIdPrefix()));
                for (int i = 0; i < len; i++)
                    buff.append("0");
                buff.append(s);
                s = buff.toString();
            }
        }
        // 有后缀没有前缀
        if ("0".equals(p.getIsPrefix()) && "1".equals(p.getIsSuffix())) {
            int len =
                    p.getIdLength()
                            - (s.length() + get(p.getIdSuffix()).length());
            if (len < 0) {
                StringBuffer buff = new StringBuffer();
                buff.append("stored id[")
                    .append(s)
                    .append("]and suffix[")
                    .append(p.getIdSuffix())
                    .append("],they are longer than id's defined length:")
                    .append(p.getIdLength());
                StringBuffer bu = new StringBuffer();
                bu.append(s);
                bu.append(get(p.getIdSuffix()));
                s = bu.toString();
            }
            if (len >= 0) {
                StringBuffer buff = new StringBuffer(p.getIdLength());
                for (int i = 0; i < len; i++)
                    buff.append("0");
                buff.append(s);
                buff.append(get(p.getIdSuffix()));
                s = buff.toString();
            }
        }
        // 没有前缀也没有后缀
        if ("0".equals(p.getIsPrefix()) && "0".equals(p.getIsSuffix())) {
            int len = p.getIdLength() - s.length();
            if (len < 0) {
                StringBuffer buff = new StringBuffer();
                buff.append("stored id[").append(s).append(
                        "]'s length is longer than id's defined length:").append(
                        p.getIdLength());
                throw new RuntimeException(buff.toString());
            }
            if (len >= 0) {
                StringBuffer buff = new StringBuffer(p.getIdLength());
                for (int i = 0; i < len; i++)
                    buff.append("0");
                buff.append(s);
                s = buff.toString();
            }
        }
        return s;
    }

    /**
     * 根据指定的参数获得对应的年月日，时间；如果前缀包含CORPID,CORPCODE，
     * 则把CORPID替换为当前登录机构的组织内码，
     * 把CORPCODE替换为当前登录 机构的组织代码。
     *
     * @param str（值可以为YYYY,YY,MM,DD,HH,MI,SS）
     * @return String
     */
    public static String get(String str) {
        if (str != null) {
            String day = DateUtil.GetToday();
            String time = DateUtil.GetCurrentTime();
            StringBuffer sb = new StringBuffer(str.toUpperCase());
            int i = 0;
            while (sb.indexOf("YYYY") != -1) {
                i = sb.indexOf("YYYY");
                sb.replace(i, i + 4, day.substring(0, 4));
            }
            while (sb.indexOf("YY") != -1) {
                i = sb.indexOf("YY");
                sb.replace(i, i + 2, day.substring(2, 4));
            }
            while (sb.indexOf("MM") != -1) {
                i = sb.indexOf("MM");
                sb.replace(i, i + 2, day.substring(4, 6));
            }
            while (sb.indexOf("DD") != -1) {
                i = sb.indexOf("DD");
                sb.replace(i, i + 2, day.substring(6, 8));
            }
            while (sb.indexOf("HH") != -1) {
                i = sb.indexOf("HH");
                sb.replace(i, i + 2, time.substring(0, 2));
            }
            while (sb.indexOf("MI") != -1) {
                i = sb.indexOf("MI");
                sb.replace(i, i + 2, time.substring(3, 5));
            }
            while (sb.indexOf("SS") != -1) {
                i = sb.indexOf("SS");
                sb.replace(i, i + 2, time.substring(6, 8));
            }
            return getForBusiness(sb.toString());
        } else {
            return "";
        }

    }

    // 为最大号表替换组织内码和组织代码服务
    private static String CORPORATIONID = "CORPID";
    private static String CORPORATIONCODE = "CORPCODE";

    private static String getForBusiness(String str) {
        IStruDomain struDomain = ScaComponentFactory.getService(IStruDomain.class, "StruDomain/StruDomain");
        // 最大号表识别组织内码和组织代码
        try {
            String organId = getOrganId();
            String organCode = struDomain.getOrganCodeByOrganId(organId);
            StringBuffer sb = new StringBuffer(str);
            if (str.indexOf(CORPORATIONID) != -1)
                sb = new StringBuffer(str.replaceAll(CORPORATIONID, organId));
            if (sb.indexOf(CORPORATIONCODE) != -1) {
                String s = sb.toString();
                return s.replaceAll(CORPORATIONCODE, organCode);
            }
            return sb.toString();
        } catch (RuntimeException e) {
            return str;
        }
    }

    private static String getOrganId() {
        try {
            //从bspInfo对象获取登录用户所属公司的organId
            String organId = GetBspInfo.getBspInfo().getCorporationOrganId();
            return organId;
        } catch (Exception e) {
            return "";
        }
    }


}
