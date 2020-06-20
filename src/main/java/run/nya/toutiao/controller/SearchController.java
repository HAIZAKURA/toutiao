package run.nya.toutiao.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import run.nya.toutiao.model.bean.Thread;
import run.nya.toutiao.model.bean.Users;
import run.nya.toutiao.model.dao.SearchDao;

import java.util.List;

@RestController
@Api(tags = {"Search"})
public class SearchController {

    /**
     * code  1  操作成功
     * code  0  操作失败
     * code -1  权限不足
     */

    @Autowired(required = false)
    private SearchDao searchDao;

    /**
     * @api    searchThread
     * @remark 搜索主题
     * @access ALL
     * @method GET
     * @route  /api/search/t/{key}
     * @param  key String
     * @return res JSONString
     */
    @RequestMapping(value = "/api/search/t/{key}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Search Thread", httpMethod = "GET", notes = "ALL")
    public String searchThread(@PathVariable("key") String key, @RequestParam(defaultValue = "1") Integer page) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            PageHelper.startPage(page, 10);
//            List<Thread> threadList = searchDao.searchThread(key);
            PageInfo<Thread> threadList = new PageInfo<>(searchDao.searchThread(key));
            res.put("code", 1);
            res.put("data", threadList);
        } catch (Exception e) {
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

    /**
     * @api    searchUsers
     * @remark 搜索用户
     * @access ALL
     * @method GET
     * @route  /api/search/u/{key}
     * @param  key String
     * @return res JSONString
     */
    @RequestMapping(value = "/api/search/u/{key}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ApiOperation(value = "Search Users", httpMethod = "GET", notes = "ALL")
    public String searchUsers(@PathVariable("key") String key, @RequestParam(defaultValue = "1") Integer page) {
        JSONObject res = new JSONObject();
        JSONObject data = new JSONObject();
        try {
            PageHelper.startPage(page, 10);
//            List<Users> usersList = searchDao.searchUsers(key);
            PageInfo<Users> usersList = new PageInfo<>(searchDao.searchUsers(key));
            res.put("code", 1);
            res.put("data", usersList);
        } catch (Exception e) {
            res.put("code", 0);
            res.put("data", data);
        }
        return res.toJSONString();
    }

}
