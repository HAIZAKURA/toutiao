package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Thread;
import run.nya.toutiao.model.dao.RateDao;
import run.nya.toutiao.model.dao.ThreadDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;
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
    public String getAllThread(@RequestParam(defaultValue = "1") Integer page) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            PageHelper.startPage(page, 20);
//            List<Thread> threadList = threadDao.getAllThread();
            PageInfo<Thread> threadList = new PageInfo<>(threadDao.getAllThread());
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
     * @route  /api/fthread/{fid}
     * @param  fid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/fthread/{fid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Forum Thread", httpMethod = "GET", notes = "ALL")
    public String getForumThread(@PathVariable("fid") Integer fid, @RequestParam(defaultValue = "1") Integer page) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            PageHelper.startPage(page, 20);
//            List<Thread> threadList = threadDao.getForumThread(fid);
            PageInfo<Thread> threadList = new PageInfo<>(threadDao.getForumThread(fid));
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
     * @api    getTopThread
     * @remark 获取置顶主题
     * @access ALL
     * @method GET
     * @route  /api/top
     * @return res JSONString
     */
    @RequestMapping(value = "/api/top", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Top Thread", httpMethod = "GET", notes = "ALL")
    public String getTopThread() {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Thread> threadList = threadDao.getTopThread();
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
     * @api    topThread
     * @remark 置顶主题
     * @access Manager Admin
     * @method PUT
     * @route  /api/top/{tid}
     * @param  tid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/top/{tid}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Set Top Thread", httpMethod = "PUT", notes = "Manager Admin")
    public String topThread(@PathVariable("tid") Integer tid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("tid", tid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = threadDao.topThread(tid);
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
     * @api    detopThread
     * @remark 取消置顶主题
     * @access Manager Admin
     * @method DELETE
     * @route  /api/top/{tid}
     * @param  tid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/top/{tid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Set Detop Thread", httpMethod = "DELETE", notes = "Manager Admin")
    public String detopThread(@PathVariable("tid") Integer tid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("tid", tid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = threadDao.detopThread(tid);
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
     * @api    getThread
     * @remark 获取主题
     * @access ALL
     * @method GET
     * @route  /api/thread/{tid}
     * @param  tid Integer
     * @return res JSONString
     */
    @RequestMapping(value = "/api/thread/{tid}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Thread Information", httpMethod = "GET", notes = "ALL")
    public String getThread(@PathVariable("tid") Integer tid) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            Thread thread = threadDao.getThread(tid);
            res.put("code", 1);
            res.put("data", thread);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    addThread
     * @remark 添加主题
     * @access Editor Manager Admin
     * @method POST
     * @route  /api/thread
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/thread", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create Thread", httpMethod = "POST", notes = "Editor Manager Admin")
    public String addThread(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Thread thread = JSONObject.parseObject(body.toJSONString(), Thread.class);
        String tname = thread.getTname();
        String tcont = thread.getTcont();
        Integer fid = thread.getFid();
        Integer uid;
        uid = Integer.valueOf(session.getAttribute("uid").toString());
        if ((CheckerUtils.isEditor(session) || CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session))
                && (uid.equals(thread.getUid()))) {
            try {
                Integer tid = threadDao.addThread(tname, tcont, fid, uid);
                Integer back = rateDao.addRate(tid);
                if (back > 0) {
                    res.put("code", 1);
                    data.put("tid", tid);
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
     * @api    modThread
     * @remark 修改主题
     * @access Editor Manager Admin
     * @method PUT
     * @route  /api/thread
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/thread", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify Thread", httpMethod = "PUT", notes = "Editor Manager Admin")
    public String modThread(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Thread thread = JSONObject.parseObject(body.toJSONString(), Thread.class);
        Integer tid = thread.getTid();
        String tname = thread.getTname();
        String tcont = thread.getTcont();
        data.put("tid", tid);
        if (CheckerUtils.isEditor(session) || CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = threadDao.modThread(tid, tname, tcont);
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
     * @api    delThread
     * @remark 删除主题
     * @access Manager Admin
     * @method DELETE
     * @route  /api/thread/{tid}
     * @param  tid     Integer
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/thread/{tid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Delete Thread", httpMethod = "DELETE", notes = "Manager Admin")
    public String delThread(@PathVariable("tid") Integer tid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("tid", tid);
        if (CheckerUtils.isManager(session) || CheckerUtils.isAdmin(session)) {
            try {
                Integer back = threadDao.delThread(tid);
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
