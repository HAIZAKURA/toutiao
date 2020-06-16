package run.nya.toutiao.model.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import run.nya.toutiao.model.bean.Rate;

@Mapper
public interface RateDao {

    // 获取评分
    Rate getRate(@Param("tid") Integer tid);

}
