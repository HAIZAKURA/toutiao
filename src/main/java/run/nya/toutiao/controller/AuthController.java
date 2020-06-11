package run.nya.toutiao.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.bean.Auth;
import run.nya.toutiao.model.dao.AuthDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(description = "Auth Operation Interface")
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
     * @return res
     */
    @RequestMapping(value = "/api/auth", method = RequestMethod.GET)
    @ApiOperation(value = "Get All Auth Information", httpMethod = "GET", notes = "ALL")
    public Object getAllAuth() {
        Map<String, Object> res = new HashMap<>();
        try {
            List<Auth> authList = authDao.getAllAuth();
            res.put("code", 1);
            res.put("data", authList);
        } catch (Exception e) {
            res.put("code", 0);
        }
        return res;
    }

    /**
     * @api    getAuthById
     * @remark 获取权限信息 通过aid
     * @access ALL
     * @method GET
     * @route  /api/auth/{aid}
     * @param  aid Integer
     * @return res
     */
    @RequestMapping(value = "/api/auth/{aid}", method = RequestMethod.GET)
    @ApiOperation(value = "Get an Auth Information By ID", httpMethod = "GET", notes = "ALL")
    public Object getAuthById(@PathVariable(value = "aid") Integer aid) {
        Map<String, Object> res = new HashMap<>();
        try {
            Auth auth = authDao.getAuthById(aid);
            res.put("code", 1);
            res.put("data", auth);
        } catch (Exception e) {
            res.put("code", 0);
        }
        return res;
    }

    /**
     * @api    modAuthById
     * @remark 修改权限信息 通过aid
     * @access Admin
     * @method PUT
     * @route  /api/auth/{aid}
     * @param  aid   Integer
     * @param  aname String
     * @param  adesc String
     * @return res
     */
    @RequestMapping(value = "/api/auth/{aid}", method = RequestMethod.PUT)
    @ApiOperation(value = "Modify an Auth Information By ID", httpMethod = "PUT", notes = "Admin")
    public Object modAuthById(@PathVariable(value = "aid") Integer aid, String aname, String adesc) {
        Map<String, Object> res = new HashMap<>();
        return res;
    }

}
