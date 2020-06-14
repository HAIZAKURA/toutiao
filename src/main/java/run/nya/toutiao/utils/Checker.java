package run.nya.toutiao.utils;

import javax.servlet.http.HttpSession;

public class Checker {

    /**
     * @method isLogin
     * @remark 判断是否登录
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isLogin(HttpSession session) {
        return (session.getAttribute("uid") != null && session.getAttribute("uname") != null &&
                session.getAttribute("aid") != null);
    }

    /**
     * @method isAdmin
     * @remark 判断用户是否为管理员
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isAdmin(HttpSession session){
        return (isLogin(session) && session.getAttribute("aid").toString().equals("4"));
    }

    /**
     * @method isManager
     * @remark 判断用户是否为内容管理员
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isManager(HttpSession session) {
        return (isLogin(session) && session.getAttribute("aid").toString().equals("3"));
    }

    /**
     * @method isEditor
     * @remark 判断用户是否为编辑
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isEditor(HttpSession session) {
        return (isLogin(session) && session.getAttribute("aid").toString().equals("2"));
    }

    /**
     * @method isUser
     * @remark 判断用户是否为用户
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isUser(HttpSession session){
        return isLogin(session);
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
