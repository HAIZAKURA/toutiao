package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Post;

import java.util.List;

@Mapper
public interface PostDao {

    // 获取所有投稿
    List<Post> getAllPost();

    // 获取投稿
    Post getPost(@Param("pid") Integer pid);

    // 添加投稿
    Integer addPost(@Param("pname") String pname, @Param("pcont") String pcont,
                    @Param("fid") Integer fid, @Param("uid") Integer uid);

    // 修改投稿
    Integer modPost(@Param("pid") Integer pid, @Param("pname") String pname,
                    @Param("pcont") String pcont, @Param("fid") Integer fid);

    // 通过投稿
    Integer acceptPost(@Param("pid") Integer pid);

    // 拒绝投稿
    Integer refusePost(@Param("pid") Integer pid);

}
