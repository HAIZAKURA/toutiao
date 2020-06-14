package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Users;
import run.nya.toutiao.model.dao.UsersDao;
import run.nya.toutiao.utils.Checker;

import java.util.List;

@RestController
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
    public String userLogin(@RequestBody JSONObject body) {
        JSONObject res = new JSONObject();
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
    public String userRegister(@RequestBody JSONObject body) {
        JSONObject res = new JSONObject();
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
        try {
            List<Users> usersList = usersDao.getAllUsers();
            res.put("code", 1);
            res.put("data", usersList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
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
        if (Checker.isStartNum(value)) {
            try {
                Users users = usersDao.getUserById(Integer.valueOf(value));
                res.put("code", 1);
                res.put("data", users);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        } else {
            try {
                Users users = usersDao.getUserByName(value);
                res.put("code", 1);
                res.put("data", users);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
            }
        }
        return res.toJSONString();
    }

}
