package run.nya.toutiao.utils;

import javax.servlet.http.HttpSession;
import java.util.Objects;

public class Checker {

    /**
     * @method isAdmin
     * @remark 判断用户是否为管理员
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isAdmin(HttpSession session){
        return true;
    }

    /**
     * @method isManager
     * @remark 判断用户是否为内容管理员
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isManager(HttpSession session) {
        return true;
    }

    /**
     * @method isEditor
     * @remark 判断用户是否为编辑
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isEditor(HttpSession session) {
        return true;
    }

    /**
     * @method isUser
     * @remark 判断用户是否为用户
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isUser(HttpSession session){
        return true;
    }

    /**
     * @method isStartNum
     * @remark 判断是否数字开头
     * @param  str String
     * @return boolean
     */
    public static boolean isStartNum(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        } else {
            return Character.isDigit(str.charAt(0));
        }
    }

}
