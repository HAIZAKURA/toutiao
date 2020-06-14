package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Users;

import java.util.List;

@Mapper
public interface UsersDao {

    // 用户登录
    Users userFind(@Param("uname") String uname, @Param("upass") String upass);

    // 管理登录
    Users adminFind(@Param("uname") String uname, @Param("upass") String upass);

    // 获取所有用户列表
    List<Users> getAllUsers();

    // 获取用户信息 通过uid
    Users getUserById(@Param("uid") Integer uid);

    // 获取用户信息 通过uname
    Users getUserByName(@Param("uname") String uname);

    // 用户注册
    Integer regUser(@Param("uname") String uname, @Param("upass") String upass,
                    @Param("umail") String umail);

    // 添加用户
    Integer addUser(@Param("uname") String uname, @Param("upass") String upass,
                    @Param("umail") String umail, @Param("aid") Integer aid);

    // 删除用户
    Integer delUser(@Param("uid") Integer uid);

    // 封禁用户
    Integer banUser(@Param("uid") Integer uid);

}
