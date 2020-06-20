package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.dao.StatusDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;

@RestController
@Api(tags = {"Status"})
public class StatusController {

    @Autowired(required = false)
    private StatusDao statusDao;

    @RequestMapping(value = "/api/status", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Database Status", httpMethod = "GET", notes = "Editor Manager Admin")
    public String getStatus(HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isEditor(session) || CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                res.put("code", 1);
                data.put("users", statusDao.getUsersNum());
                data.put("thread", statusDao.getThreadNum());
                data.put("comm", statusDao.getCommNum());
                data.put("forum", statusDao.getForumNum());
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
