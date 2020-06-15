package run.nya.toutiao.utils;

import javax.servlet.http.HttpSession;

public class CheckerUtils {

    /**
     * @method isLogin
     * @remark 判断是否登录
     * @param  session HttpSession
     * @return         Boolean
     */
    public static Boolean isLogin(HttpSession session) {
        return (session.getAttribute("uid") != null && session.getAttribute("uname") != null &&
                session.getAttribute("aid") != null);
    }

    /**
     * @method isAdmin
     * @remark 判断用户是否为管理员
     * @param  session HttpSession
     * @return         Boolean
     */
    public static Boolean isAdmin(HttpSession session){
        return (isLogin(session) && session.getAttribute("aid").toString().equals("4"));
    }

    /**
     * @method isManager
     * @remark 判断用户是否为内容管理员
     * @param  session HttpSession
     * @return         Boolean
     */
    public static Boolean isManager(HttpSession session) {
        return (isLogin(session) && session.getAttribute("aid").toString().equals("3"));
    }

    /**
     * @method isEditor
     * @remark 判断用户是否为编辑
     * @param  session HttpSession
     * @return         Boolean
     */
    public static Boolean isEditor(HttpSession session) {
        return (isLogin(session) && session.getAttribute("aid").toString().equals("2"));
    }

    /**
     * @method isUser
     * @remark 判断用户是否为用户
     * @param  session HttpSession
     * @return         Boolean
     */
    public static Boolean isUser(HttpSession session){
        return isLogin(session);
    }

    /**
     * @method isStartNum
     * @remark 判断是否数字开头
     * @param  str String
     * @return     Boolean
     */
    public static Boolean isStartNum(String str) {
        if (str == null || str.trim().length() <= 0) {
            return false;
        } else {
            return Character.isDigit(str.charAt(0));
        }
    }

}
