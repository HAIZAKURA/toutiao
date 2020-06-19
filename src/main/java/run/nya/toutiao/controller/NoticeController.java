package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Notice;
import run.nya.toutiao.model.dao.NoticeDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(tags = {"Notice"})
public class NoticeController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private NoticeDao noticeDao;

    /**
     * @api    getAllNotice
     * @remark 获取所有通知
     * @access User
     * @method GET
     * @route  /api/notice
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/notice", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get All Notice", httpMethod = "GET", notes = "User")
    public String getAllNotice(HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isUser(session)) {
            try {
                List<Notice> noticeList = noticeDao.getAllNotice();
                res.put("code", 1);
                res.put("data", noticeList);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            res.put("code", -1);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getUserNotice
     * @remark 获取用户所有通知
     * @access User
     * @method GET
     * @route  /api/unotice/{uid}
     * @param  uid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/unotice/{uid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get User Notice", httpMethod = "GET", notes = "User")
    public String getUserNotice(@PathVariable("uid") Integer uid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Integer s_uid = Integer.valueOf(session.getAttribute("uid").toString());
        if (CheckerUtils.isUser(session) && uid.equals(s_uid)) {
            try {
                List<Notice> noticeList = noticeDao.getUserNotice(uid);
                res.put("code", 1);
                res.put("data", noticeList);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            res.put("code", -1);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getNotice
     * @remark 获取通知
     * @access User
     * @method GET
     * @route  /api/notice/{nid}
     * @param  nid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/notice/{nid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Notice Information", httpMethod = "GET", notes = "User")
    public String getNotice(@PathVariable("nid") Integer nid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isUser(session)) {
            try {
                Notice notice = noticeDao.getNotice(nid);
                res.put("code", 1);
                res.put("data", notice);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            res.put("code", -1);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    addNotice
     * @remark 添加通知
     * @access Manager Admin
     * @method POST
     * @route  /api/notice
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/notice", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create Notice", httpMethod = "POST", notes = "Manager Admin")
    public String addNotice(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Notice notice = JSONObject.parseObject(body.toJSONString(), Notice.class);
        String ncont = notice.getNcont();
        Integer uid = notice.getUid();
        data.put("ncont", ncont);
        data.put("uid", uid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer nid = noticeDao.addNotice(ncont, uid);
                res.put("code", 1);
                data.put("nid", nid);
            } catch (Exception e) {
                res.put("code", 0);
            }
        } else {
            res.put("code", -1);
        }
        res.put("data", data);
        return res.toJSONString();
    }

    /**
     * @api    delNotice
     * @remark 删除通知
     * @access Manager Admin
     * @method DELETE
     * @route  /api/notice/{nid}
     * @param  nid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/notice/{nid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Delete Notice", httpMethod = "DELETE", notes = "Manager Admin")
    public String delNotice(@PathVariable("nid") Integer nid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("nid", nid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = noticeDao.delNotice(nid);
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
