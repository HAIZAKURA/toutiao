package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Comm;

import java.util.List;

@Mapper
public interface CommDao {

    // 获取主题评论
    List<Comm> getThreadComm(@Param("tid") Integer tid);

    // 获取用户评论
    List<Comm> getUserComm(@Param("uid") Integer uid);

    // 获取单条评论
    Comm getComm(@Param("cid") Integer cid);

    // 添加评论
    Integer addComm(@Param("ccont") String ccont, @Param("tid") Integer tid,
                    @Param("uid") Integer uid);

    // 删除评论
    Integer delComm(@Param("cid") Integer cid);

}
