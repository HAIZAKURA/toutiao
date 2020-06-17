package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Rate;

@Mapper
public interface RateDao {

    // 获取评分
    Rate getRate(@Param("tid") Integer tid);

    // 增加评分
    Integer addRate(@Param("tid") Integer tid);

    // 点赞
    Integer addGood(@Param("tid") Integer tid);

    // 点踩
    Integer addBad(@Param("tid") Integer tid);

}
