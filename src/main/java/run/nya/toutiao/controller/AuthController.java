package run.nya.toutiao.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.bean.Auth;
import run.nya.toutiao.model.dao.AuthDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
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
    @ApiOperation(value = "获取所有权限信息", httpMethod = "GET")
    public Object getAllAuth() {
        Map<String, Object> res = new HashMap<>();
        try {
            List<Auth> authList = authDao.getAllAuth();
            List<Map<String, Object>> data = new ArrayList<>();
            for (Auth auth : authList) {
                Map<String, Object> item = new HashMap<>();
                item.put("aid", auth.getAid());
                item.put("aname", auth.getAname());
                item.put("adesc", auth.getAdesc());
                data.add(item);
            }
            res.put("code", 1);
            res.put("data", data);
        } catch (Exception e) {
            res.put("code", 0);
        }
        return res;
    }

}
