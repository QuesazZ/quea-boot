package com.quesa.mybootproject.module.user.dao;

import com.quesa.mybootproject.module.user.entity.Student;
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
public interface StudentMapper extends BaseDao<Student> {


}
