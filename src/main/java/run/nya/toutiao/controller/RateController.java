package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import run.nya.toutiao.model.bean.Rate;
import run.nya.toutiao.model.dao.RateDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;

@RestController
@Api(tags = {"Rate"})
public class RateController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private RateDao rateDao;

    /**
     * @api    getRate
     * @remark 获取主题评分
     * @access ALL
     * @method GET
     * @route  /api/rate/{tid}
     * @param  tid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/rate/{tid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Thread Rate", httpMethod = "GET", notes = "ALL")
    public String getRate(@PathVariable("tid") Integer tid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            Rate rate = rateDao.getRate(tid);
            res.put("code", 1);
            res.put("data", rate);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    addGood
     * @remark 点赞
     * @access User
     * @method PUT
     * @route  /api/rate/{tid}
     * @param  tid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/rate/{tid}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add Good", httpMethod = "PUT", notes = "User")
    public String addGood(@PathVariable("tid") Integer tid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("tid", tid);
        if (CheckerUtils.isUser(session)) {
            try {
                Integer back = rateDao.addGood(tid);
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
     * @api    addBad
     * @remark 点踩
     * @access User
     * @method DELETE
     * @route  /api/rate/{tid}
     * @param  tid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/rate/{tid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Add Bad", httpMethod = "DELETE", notes = "User")
    public String addBad(@PathVariable("tid") Integer tid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("tid", tid);
        if (CheckerUtils.isUser(session)) {
            try {
                Integer back = rateDao.addBad(tid);
                if (back > 0) {
                    res.put("code", 1);
                } else {
                    res.put("code", 0);
                }
            } catch (Exception e) {
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

}
