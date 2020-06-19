package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Thread;
import run.nya.toutiao.model.bean.Users;

import java.util.List;

@Mapper
public interface SearchDao {

    // 搜索主题
    List<Thread> searchThread(@Param("key") String key);

    // 搜索用户
    List<Users> searchUsers(@Param("key") String key);

}
