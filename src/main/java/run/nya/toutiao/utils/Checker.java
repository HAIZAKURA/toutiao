package run.nya.toutiao.utils;

import javax.servlet.http.HttpSession;

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
     * @method isUser
     * @remark 判断用户是否为用户
     * @param  session HttpSession
     * @return boolean
     */
    public static boolean isUser(HttpSession session){
        return true;
    }

}
