package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Comm;
import run.nya.toutiao.model.dao.CommDao;
import run.nya.toutiao.model.dao.RateDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(tags = {"Comm"})
public class CommController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private CommDao commDao;
    private RateDao rateDao;

    /**
     * @api    getThreadComm
     * @remark 获取主题评论
     * @access ALL
     * @method GET
     * @route  /api/tcomm/{tid}
     * @param  tid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/tcomm/{tid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Thread Comments", httpMethod = "GET", notes = "ALL")
    public String getThreadComm(@PathVariable("tid") Integer tid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Comm> commList = commDao.getThreadComm(tid);
            res.put("code", 1);
            res.put("data", commList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getUserComm
     * @remark 获取用户评论
     * @access ALL
     * @method GET
     * @route  /api/ucomm/{uid}
     * @param  uid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/ucomm/{uid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get User Comments", httpMethod = "GET", notes = "ALL")
    public String getUserComm(@PathVariable("uid") Integer uid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Comm> commList = commDao.getUserComm(uid);
            res.put("code", 1);
            res.put("data", commList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getComm
     * @remark 获取评论
     * @access ALL
     * @method GET
     * @route  /api/comm/{cid}
     * @param  cid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/comm/{cid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Comment", httpMethod = "GET", notes = "ALL")
    public String getComm(@PathVariable("cid") Integer cid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            Comm comm = commDao.getComm(cid);
            res.put("code", 1);
            res.put("data", comm);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    addComm
     * @remark 添加评论
     * @access User
     * @method POST
     * @route  /api/comm
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/comm", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create Comment", httpMethod = "POST", notes = "User")
    public String addComm(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Comm comm = JSONObject.parseObject(body.toJSONString(), Comm.class);
        String ccont = comm.getCcont();
        Integer tid = comm.getTid();
        Integer uid;
        uid = Integer.valueOf(session.getAttribute("uid").toString());
        if (CheckerUtils.isUser(session) && (uid.equals(comm.getUid()))) {
            try {
                Integer cid = commDao.addComm(ccont, tid, uid);
                Integer back = rateDao.addCnum(tid);
                if (back > 0) {
                    res.put("code", 1);
                    data.put("cid", cid);
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
     * @api    delComm
     * @remark 删除评论
     * @access Manager Admin
     * @method DELETE
     * @route  /api/comm/{cid}
     * @param  cid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/comm/{cid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Delete Comment", httpMethod = "DELETE", notes = "Manager Admin")
    public String delComm(@PathVariable("cid") Integer cid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("cid", cid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = commDao.delComm(cid);
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
