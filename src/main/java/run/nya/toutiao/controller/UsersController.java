package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.dao.UsersDao;

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

}
