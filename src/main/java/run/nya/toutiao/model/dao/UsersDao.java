package run.nya.toutiao.model.dao;

import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Users;

import java.util.List;

@Mapper
public interface UsersDao {

    // 用户登录
    Users userLogin(@Param("uname") String uname, @Param("upass") String upass);

    // 管理登录
    Users adminLogin(@Param("uname") String uname, @Param("upass") String upass);

    // 获取所有用户列表
    Page<Users> getAllUsers();

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

    // 封禁用户 通过uid
    Integer banUserById(@Param("uid") Integer uid);

    // 封禁用户 通过uname
    Integer banUserByName(@Param("uname") String uname);

    // 修改用户信息 通过uid
    Integer modUser(@Param("uid") Integer uid, @Param("umail") String umail,
                    @Param("udesc") String udesc);

    // 修改用户密码 Self
    Integer updUserPass(@Param("uid") Integer uid, @Param("upass") String upass,
                        @Param("new_upass") String new_upass);

}
