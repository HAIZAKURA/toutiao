package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Notice;

import java.util.List;

@Mapper
public interface NoticeDao {

    // 获取所有通知
    List<Notice> getAllNotice();

    // 获取用户所有通知
    List<Notice> getUserNotice(@Param("uid") Integer uid);

    // 获取通知
    Notice getNotice(@Param("nid") Integer nid);

    // 添加通知
    Integer addNotice(@Param("ncont") String ncont, @Param("uid") Integer uid);

    // 修改通知
    // Integer modNotice(@Param("nid") Integer nid, @Param("ncont") String ncont);

    // 删除通知
    Integer delNotice(@Param("nid") Integer nid);

}
