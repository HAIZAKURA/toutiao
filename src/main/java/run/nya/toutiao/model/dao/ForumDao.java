package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Forum;

import java.util.List;

@Mapper
public interface ForumDao {

    // 获取所有分区信息
    List<Forum> getAllForum();

    // 获取分区信息 通过fid
    Forum getForumById(@Param("fid") Integer fid);

    // 获取分区信息 通过fname
    Forum getForumByName(@Param("fname") String fname);

    // 添加分区
    Integer addForum(@Param("fname") String fname, @Param("fdesc") String fdesc);

    // 删除分区
    Integer defForum(@Param("fid") Integer fid);

    // 修改分区
    Integer modForum(@Param("fid") Integer fid, @Param("fname") String fname,
                     @Param("fdesc") String fdesc);

}
