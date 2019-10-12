package com.quesa.mybootproject.module.score.dao;

import com.quesa.mybootproject.module.score.entity.Score;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import com.quesa.mybootproject.common.persistence.BaseDao;

/**
 * 
 * -数据库操作接口层
 * 
 **/
@Mapper
@Repository
public interface ScoreMapper extends BaseDao<Score> {


}
