package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Auth;
import run.nya.toutiao.model.dao.AuthDao;
import run.nya.toutiao.utils.Checker;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        try {
            List<Auth> authList = authDao.getAllAuth();
            res.put("code", 1);
            res.put("data", authList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
        }
        return res.toJSONString();
    }

    /**
     * @api    getAuthById
     * @remark 获取权限信息 通过aid
     * @access ALL
     * @method GET
     * @route  /api/auth/{aid}
     * @param  aid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/auth/{aid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get an Auth Information By ID", httpMethod = "GET", notes = "ALL")
    public String getAuthById(@PathVariable(value = "aid") Integer aid) {
        JSONObject res = new JSONObject();
        try {
            Auth auth = authDao.getAuthById(aid);
            res.put("code", 1);
            res.put("data", auth);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
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
    public String modAuthById(@PathVariable(value = "aid") Integer aid, @RequestBody JSONObject body,
                              HttpSession session) {
        JSONObject res = new JSONObject();
        Auth reqAuth = JSON.parseObject(body.toJSONString(), Auth.class);
        if (Checker.isAdmin(session)) {
            try {
                Integer back = authDao.modAuth(aid, reqAuth.getAname(), reqAuth.getAdesc());
                if (back > 0) {
                    Map<String, Object> data = new HashMap<>();
                    data.put("aid", aid);
                    data.put("aname", reqAuth.getAname());
                    data.put("adesc", reqAuth.getAdesc());
                    res.put("code", 1);
                    res.put("data", data);
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
        return res.toJSONString();
    }

}
