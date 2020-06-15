package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Auth;
import run.nya.toutiao.model.dao.AuthDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(tags = {"Auth"})
public class AuthController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private AuthDao authDao;

    /**
     * @api    getAllAuth
     * @remark 获取所有权限信息
     * @access ALL
     * @method GET
     * @route  /api/auth
     * @return res JSONString
     */
    @RequestMapping(value = "/api/auth", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get All Auth Information", httpMethod = "GET", notes = "ALL")
    public Object getAllAuth() {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Auth> authList = authDao.getAllAuth();
            res.put("code", 1);
            res.put("data", authList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getAuthBy
     * @remark 获取权限信息 通过aid/aname
     * @access ALL
     * @method GET
     * @route  /api/auth/{value}
     * @param  value String
     * @return res   JSONString
     */
    @RequestMapping(value = "/api/auth/{value}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get an Auth Information By ID or Name", httpMethod = "GET", notes = "ALL")
    public String getAuthBy(@PathVariable("value") String value) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isStartNum(value)) {
            try {
                Auth auth = authDao.getAuthById(Integer.valueOf(value));
                res.put("code", 1);
                res.put("data", auth);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            try {
                Auth auth = authDao.getAuthByName(value);
                res.put("code", 1);
                res.put("data", auth);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        }
        return res.toJSONString();
    }

    /**
     * @api    modAuthById
     * @remark 修改权限信息 通过aid
     * @access Admin
     * @method PUT
     * @route  /api/auth/{aid}
     * @param  aid     Integer
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/auth/{aid}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify an Auth Information By ID", httpMethod = "PUT", notes = "Admin")
    public String modAuthById(@PathVariable("aid") Integer aid, @RequestBody JSONObject body,
                              HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Auth reqAuth = JSON.parseObject(body.toJSONString(), Auth.class);
        data.put("aid", aid);
        if (CheckerUtils.isAdmin(session)) {
            try {
                Integer back = authDao.modAuth(aid, reqAuth.getAname(), reqAuth.getAdesc());
                if (back > 0) {
                    data.put("aname", reqAuth.getAname());
                    data.put("adesc", reqAuth.getAdesc());
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
