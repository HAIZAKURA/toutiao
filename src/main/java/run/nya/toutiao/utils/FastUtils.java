package run.nya.toutiao.utils;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpSession;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FastUtils {

    /**
     * @method getMD5
     * @remark 计算字符串MD5值
     * @param  plainText String
     * @return res       String
     */
    public static String getMD5(String plainText) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            byte[] procBytes = plainText.getBytes();
            byte[] digest = md5.digest(procBytes);
            char[] chars = new char[] {'0', '1', '2', '3', '4', '5', '6',
                    '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
            StringBuilder procSB = new StringBuilder();
            for (byte eachByte : digest) {
                procSB.append(chars[(eachByte >> 4) & 15]);
                procSB.append(chars[eachByte & 15]);
            }
            return procSB.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @method getNowTime
     * @remark 获取当前时间 (yyyy-MM-dd HH:mm:ss)
     * @return res String
     */
    public static String getNowTime() {
        String res;
        SimpleDateFormat sdf = new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        res = sdf.format(date);
        return res;
    }

    /**
     * @method sessionToJSON
     * @remark session转换成JSON
     * @param  session HttpSession
     * @return data    JSONObject
     */
    public static JSONObject sessionToJSON(HttpSession session) {
        JSONObject data = new JSONObject();
        data.put("uid", Integer.valueOf(session.getAttribute("uid").toString()));
        data.put("uname", session.getAttribute("uname").toString());
        data.put("aid", Integer.valueOf(session.getAttribute("aid").toString()));
        return data;
    }

}
