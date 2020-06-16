package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Thread;

import java.util.List;

@Mapper
public interface ThreadDao {

    // 获取所有主题
    List<Thread> getAllThread();

    // 获取分区所有主题
    List<Thread> getForumThread(@Param("fid") Integer fid);

    // 获取所有置顶主题
    List<Thread> getTopThread();

    // 获取用户所有主题
    // List<Thread> getUserThread(@Param("uid") Integer uid);

    // 获取主题
    Thread getThread(@Param("tid") Integer tid);

    // 添加主题
    Integer addThread(@Param("tname") String tname, @Param("tcont") String tcont,
                      @Param("fid") Integer fid, @Param("uid") Integer uid);

    // 删除主题
    Integer delThread(@Param("tid") Integer tid);

    // 修改主题
    Integer modThread(@Param("tid") Integer tid, @Param("tname") String tname,
                      @Param("tcont") String tcont);

    // 置顶主题
    Integer topThread(@Param("tid") Integer tid);

    // 取消置顶主题
    Integer detopThread(@Param("tid") Integer tid);

}
