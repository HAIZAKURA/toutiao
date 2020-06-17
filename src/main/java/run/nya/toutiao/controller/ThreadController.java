package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.bean.Thread;
import run.nya.toutiao.model.dao.RateDao;
import run.nya.toutiao.model.dao.ThreadDao;

import java.util.List;

@RestController
@Api(tags = {"Thread"})
public class ThreadController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private ThreadDao threadDao;
    private RateDao rateDao;

    /**
     * @api    getAllThread
     * @remark 获取所有主题
     * @access ALL
     * @method GET
     * @route  /api/thread
     * @return res JSONString
     */
    @RequestMapping(value = "/api/thread", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get All Thread", httpMethod = "GET", notes = "ALL")
    public String getAllThread() {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Thread> threadList = threadDao.getAllThread();
            res.put("code", 1);
            res.put("data", threadList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getForumThread
     * @remark 获取分区所有主题
     * @access ALL
     * @method GET
     * @route  /api/fthread
     * @param  fid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/fthread/{fid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Forum Thread", httpMethod = "GET", notes = "ALL")
    public String getForumThread(@PathVariable("fid") Integer fid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Thread> threadList = threadDao.getForumThread(fid);
            res.put("code", 1);
            res.put("data", threadList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

}
