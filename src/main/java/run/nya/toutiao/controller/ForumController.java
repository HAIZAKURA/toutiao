package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Forum;
import run.nya.toutiao.model.dao.ForumDao;
import run.nya.toutiao.utils.CheckerUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@Api(tags = {"Forum"})
public class ForumController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private ForumDao forumDao;

    /**
     * @api    getAllForum
     * @remark 获取所有分区信息
     * @access ALL
     * @method GET
     * @route  /api/forum
     * @return res JSONString
     */
    @RequestMapping(value = "/api/forum", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get All Forum Information", httpMethod = "GET", notes = "ALL")
    public String getAllForum() {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            List<Forum> forumList = forumDao.getAllForum();
            res.put("code", 1);
            res.put("data", forumList);
        } catch (Exception e) {
            e.printStackTrace();
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    getForumBy
     * @remark 获取分区信息 通过fid/fname
     * @access ALL
     * @method GET
     * @route  /api/forum/{value}
     * @param  value String
     * @return res   JSONObject
     */
    @RequestMapping(value = "/api/forum/{value}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Get Forum Information By ID or Name", httpMethod = "GET", notes = "ALL")
    public String getForumBy(@PathVariable("value") String value) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        if (CheckerUtils.isStartNum(value)) {
            try {
                Forum forum = forumDao.getForumById(Integer.valueOf(value));
                res.put("code", 1);
                res.put("data", forum);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        } else {
            try {
                Forum forum = forumDao.getForumByName(value);
                res.put("code", 1);
                res.put("data", forum);
            } catch (Exception e) {
                e.printStackTrace();
                res.put("code", 0);
                res.put("data", data);
            }
        }
        return res.toJSONString();
    }

    /**
     * @api    addForum
     * @remark 添加分区
     * @access Admin
     * @method POST
     * @route  /api/forum
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/forum", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Create Forum", httpMethod = "POST", notes = "Admin")
    public String addForum(@RequestBody JSONObject body, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Forum forum = JSONObject.parseObject(body.toJSONString(), Forum.class);
        String fname = forum.getFname();
        String fdesc = forum.getFdesc();
        data.put("fname", fname);
        data.put("fdesc", fdesc);
        if (CheckerUtils.isAdmin(session)) {
            try {
                Integer back = forumDao.addForum(fname, fdesc);
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
     * @api    delForum
     * @remark 删除分区
     * @access Admin
     * @method DELETE
     * @route  /api/forum/{fid}
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/forum/{fid}", method = RequestMethod.DELETE, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Delete Forum By ID", httpMethod = "DELETE", notes = "Admin")
    public String delForum(@PathVariable("fid") Integer fid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        data.put("fid", fid);
        if (CheckerUtils.isAdmin(session)) {
            try {
                Integer back = forumDao.defForum(fid);
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
     * @api    modForum
     * @remark 修改分区信息
     * @access Admin
     * @method PUT
     * @route  /api/forum/{fid}
     * @param  body    JSONObject
     * @param  session HttpSession
     * @return res     JSONString
     */
    @RequestMapping(value = "/api/forum/{fid}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Modify Forum By ID", httpMethod = "PUT", notes = "Admin")
    public String modForum(@RequestBody JSONObject body, @PathVariable("fid") Integer fid, HttpSession session) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        Forum forum = JSONObject.parseObject(body.toJSONString(), Forum.class);
        String fname = forum.getFname();
        String fdesc = forum.getFdesc();
        data.put("fid", fid);
        data.put("fname", fname);
        data.put("fdesc", fdesc);
        if (CheckerUtils.isAdmin(session)) {
            try {
                Integer back = forumDao.modForum(fid, fname, fdesc);
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
