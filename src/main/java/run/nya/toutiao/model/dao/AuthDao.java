package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Auth;

import java.util.List;

@Mapper
public interface AuthDao {

    // 获取所有权限信息
    List<Auth> getAllAuth();

    // 获取权限信息 通过aid
    Auth getAuthById(@Param("aid") Integer aid);

    // 获取权限信息 通过aname
    Auth getAutnByName(@Param("aname") String aname);

    // 修改权限信息
    Integer modAuth(@Param("aid") Integer aid, @Param("aname") String aname,
                    @Param("adesc") String adesc);

}
