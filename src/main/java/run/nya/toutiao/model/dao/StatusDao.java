package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface StatusDao {

        // 获取用户数量
        Integer getUsersNum();

        // 获取主题数量
        Integer getThreadNum();

        // 获取评论数量
        Integer getCommNum();

        // 获取分区数量
        Integer getForumNum();

}
