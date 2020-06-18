package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import run.nya.toutiao.model.bean.Users;
import run.nya.toutiao.model.dao.UsersDao;
import run.nya.toutiao.utils.CheckerUtils;
import run.nya.toutiao.utils.FastUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@SessionAttributes(value = {"uid", "uname", "aid"}, types = {Integer.class, String.class, Integer.class})
@Api(tags = {"Users"})
public class UsersController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private UsersDao usersDao;

    /**
     * @api    loginCheck
     * @remark 登录状态验证
     * @access ALL
     * @method GET
     * @route  /api/check
     * @param  session JSONObject
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/check", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Login Status Checker", httpMethod = "GET", notes = "ALL")
    public String loginCheck(HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isLogin(session)) {
            res.put("code", 1);
            res.put("data", FastUtils.sessionToJSON(session));
        } else {
            res.put("code", -1);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     *
     * @api    userLogin
     * @remark 用户登录
     * @access ALL
     * @method POST
     * @route  /api/login
     * @param  body JSONObject
     * @return res  JSONString
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "User Login", httpMethod = "POST", notes = "ALL")
    public String userLogin(@RequestBody JSONObject body, ModelMap modelMap, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isLogin(session)) {
            res.put("code", 0);
            res.put("data", FastUtils.sessionToJSON(session));
        } else {
            Users reqUser = JSONObject.parseObject(body.toJSONString(), Users.class);
            String uname = reqUser.getUname();
            String upass = FastUtils.getMD5(uname + "#" + reqUser.getUpass());
            try {
                Users users = usersDao.userLogin(uname, upass);
                if (users.getUstat() != 0) {
                    data.put("uname", uname);
                    res.put("code", -1);
                } else {
                    modelMap.addAttribute("uid", users.getUid());
                    modelMap.addAttribute("uname", users.getUname());
                    modelMap.addAttribute("aid", users.getAid());
                    session.setMaxInactiveInterval(7 * 24 * 3600);
                    data.put("uid", users.getUid());
                    data.put("uname", users.getUname());
                    data.put("aid", users.getAid());
                    res.put("code", 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", -1);
            }
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    adminLogin
     * @remark 管理登录
     * @access ALL
     * @method POST
     * @route  /api/admin/login
     * @param  body JSONObject
     * @return res  JSONString
     */
    @RequestMapping(value = "/api/admin/login", method = RequestMethod.POST, produces = "appliction/json;charset=UTF-8")
    @ApiOperation(value = "Admin or Manager Login", httpMethod = "POST", notes = "ALL")
    public String adminLogin(@RequestBody JSONObject body, ModelMap modelMap, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isLogin(session)) {
            res.put("code", 0);
            res.put("data", FastUtils.sessionToJSON(session));
        } else {
            Users reqUser = JSONObject.parseObject(body.toJSONString(), Users.class);
            String uname = reqUser.getUname();
            String upass = FastUtils.getMD5(uname + "#" + reqUser.getUpass());
            try {
                Users users = usersDao.adminLogin(uname, upass);
                if (users.getUstat() != 0) {
                    data.put("uname", uname);
                    res.put("code", -1);
                } else {
                    modelMap.addAttribute("uid", users.getUid());
                    modelMap.addAttribute("uname", users.getUname());
                    modelMap.addAttribute("aid", users.getAid());
                    session.setMaxInactiveInterval(15 * 60);
                    data.put("uid", users.getUid());
                    data.put("uname", users.getUname());
                    data.put("aid", users.getAid());
                    res.put("code", 1);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", -1);
            }
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    logOut
     * @remark 用户登出
     * @access ALL
     * @method GET
     * @route  /api/logout
     * @param  sessionStatus SessionStatus
     * @return res           JSONString
     */
    @RequestMapping(value = "/api/logout", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "User Logout", httpMethod = "GET", notes = "ALL")
    public String logOut(SessionStatus sessionStatus) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            sessionStatus.setComplete();
            res.put("code", 1);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    userRegister
     * @remark 用户注册
     * @access ALL
     * @method POST
     * @route  /api/register
     * @param  body JSONObject
     * @return res  JSONString
     */
    @RequestMapping(value = "/api/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "User Register", httpMethod = "POST", notes = "ALL")
    public String userRegister(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Users users = JSONObject.parseObject(body.toJSONString(), Users.class);
        if (CheckerUtils.isLogin(session)) {
            res.put("code", 0);
            res.put("data", FastUtils.sessionToJSON(session));
        } else {
            if (StringUtils.isEmpty(users.getUname()) || StringUtils.isEmpty(users.getUpass()) ||
                    StringUtils.isEmpty(users.getUmail())) {
                data.put("uname", users.getUname());
                data.put("umail", users.getUmail());
                res.put("code", 0);
            } else {
                String uname = users.getUname();
                String upass = FastUtils.getMD5(uname + "#" + users.getUpass());
                String umail = users.getUmail();
                data.put("uname", uname);
                data.put("umail", umail);
                try {
                    Integer back = usersDao.regUser(uname, upass, umail);
                    if (back > 0) {
                        res.put("code", 1);
                    } else {
                        res.put("code", 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    addUser
     * @remark 添加用户
     * @access Admin
     * @method POST
     * @route  /api/users
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/users", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create User", httpMethod = "POST", notes = "Admin")
    public String addUser(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Users users = JSONObject.parseObject(body.toJSONString(), Users.class);
        String uname = users.getUname();
        String upass = FastUtils.getMD5(uname + "#" + users.getUpass());
        String umail = users.getUmail();
        Integer aid = users.getAid();
        data.put("uname", uname);
        data.put("umail", umail);
        data.put("aid", aid);
        if (CheckerUtils.isAdmin(session)) {
            try {
                Integer back = usersDao.addUser(uname, upass, umail, aid);
                if (back > 0) {
                    res.put("code", 1);
                } else {
                    res.put("code", 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    getAllUsers
     * @remark 获取所有用户信息
     * @access ALL
     * @method GET
     * @route  /api/users
     * @return res JSONString
     */
    @RequestMapping(value = "/api/users", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get All Users Information", httpMethod = "GET", notes = "ALL")
    public String getAllUsers() {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Users> usersList = usersDao.getAllUsers();
            res.put("code", 1);
            res.put("data", usersList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getUserBy
     * @remark 获取用户信息 通过uid/uname
     * @access ALL
     * @method GET
     * @route  /api/users/{value}
     * @param  value String
     * @return res   JSONString
     */
    @RequestMapping(value = "/api/users/{value}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get User Information By ID or Name", httpMethod = "GET", notes = "ALL")
    public String getUserBy(@PathVariable("value") String value) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isStartNum(value)) {
            data.put("uid", Integer.valueOf(value));
            try {
                Users users = usersDao.getUserById(Integer.valueOf(value));
                res.put("code", 1);
                res.put("data", users);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            data.put("uname", value);
            try {
                Users users = usersDao.getUserByName(value);
                res.put("code", 1);
                res.put("data", users);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        }
        return res.toJSONString();
    }

    /**
     * @api    banUserBy
     * @remark 封禁用户 通过uid/uname
     * @access Admin / Manager
     * @method DELETE
     * @route  /api/users/{value}
     * @param  value   String
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/users/{value}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Ban User By ID or Name", httpMethod = "DELETE", notes = "Admin / Manager")
    public String banUserBy(@PathVariable("value") String value, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isAdmin(session) || CheckerUtils.isManager(session)) {
            if (CheckerUtils.isStartNum(value)) {
                data.put("uid", Integer.valueOf(value));
                try {
                    Integer back = usersDao.banUserById(Integer.valueOf(value));
                    if (back > 0) {
                        res.put("code", 1);
                    } else {
                        res.put("code", 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.put("code", 0);
                }
            } else {
                data.put("uname", value);
                try {
                    Integer back = usersDao.banUserByName(value);
                    if (back > 0) {
                        res.put("code", 1);
                    } else {
                        res.put("code", 0);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.put("code", 0);
                }
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    modUser
     * @remark 修改用户信息 Self
     * @access ALL
     * @method PUT
     * @route  /api/users
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/users", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify User Information By Self", httpMethod = "PUT", notes = "ALL")
    public String modUser(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isLogin(session)) {
            Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
            Users users = JSONObject.parseObject(body.toJSONString(), Users.class);
            String umail = users.getUmail();
            String udesc = users.getUdesc();
            data.put("uid", uid);
            try {
                Integer back = usersDao.modUser(uid, umail, udesc);
                if (back > 0) {
                    res.put("code", 1);
                } else {
                    res.put("code", 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    modUserAdmin
     * @remark 修改用户信息 Admin
     * @access Admin
     * @method PUT
     * @route  /api/users/{uid}
     * @param  uid     Integer
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/users/{uid}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify User Information By Admin", httpMethod = "PUT", notes = "Admin")
    public String modUserAdmin(@PathVariable("uid") Integer uid, @RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isAdmin(session)) {
            Users users = JSONObject.parseObject(body.toJSONString(), Users.class);
            String umail = users.getUmail();
            String udesc = users.getUdesc();
            data.put("uid", uid);
            try {
                Integer back = usersDao.modUser(uid, umail, udesc);
                if (back > 0) {
                    res.put("code", 1);
                } else {
                    res.put("code", 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    updPass
     * @remark 修改用户密码 Self
     * @access ALL
     * @method PUT
     * @route  /api/upass
     * @param  upass     String
     * @param  new_upass String
     * @param  session   HttpSession
     * @return res       JSONString
     */
    @RequestMapping(value = "/api/upass", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify User Password By Self", httpMethod = "PUT", notes = "ALL")
    public String updUserPass(String upass, String new_upass, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isLogin(session)) {
            data.put("data", FastUtils.sessionToJSON(session));
            Integer uid = Integer.valueOf(session.getAttribute("uid").toString());
            String uname = session.getAttribute("uname").toString();
            try {
                Integer back = usersDao.updUserPass(uid, FastUtils.getMD5(uname + "#" + upass),
                        FastUtils.getMD5(uname + "#" + new_upass));
                if (back > 0) {
                    res.put("code", 1);
                } else {
                    res.put("code", 0);
                }
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

}
